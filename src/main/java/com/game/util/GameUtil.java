package com.game.util;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.game.WSClient;
import com.game.dto.Admin;
import com.game.dto.Game;
import com.game.dto.Player;
import com.google.gson.Gson;

@Component
@EnableAsync
public class GameUtil {

	Map<String, Queue<Game>> gameQMap = new HashMap<String, Queue<Game>>();
	String gametype[] = { "game_100", "game_25" ,"game_50"};
	int INITIAL_GAME_SIZE = 200;
	WSClient ws = new WSClient();
	//public enum GameState { STARTGAME,PUSHCARD1, PUSHCARD2, DECLARE }; 

	@PostConstruct
	public void initializeBucket() {
		initializeGameQueueMap(gameQMap, gametype);
		ws.connect();
	}

	private void initializeGameQueueMap(Map<String, Queue<Game>> gameMap, String[] gametype) {
		for (int i = 0; i < gametype.length; i++) {
			Queue<Game> gameQueue = new LinkedList<>();
			createGameBucket(gameQueue, gametype[i], INITIAL_GAME_SIZE);
			gameMap.put(gametype[i], gameQueue);
		}
	}

	private void createGameBucket(Queue<Game> gameQ, String gametype, int size) {
		for (int i = 1; i <= size; i++) {
			Game game = new Game();
			game.setGameId(gametype + "_" + i);
			gameQ.add(game);
		}
	}

	public String assignGameId(String gametype,int gameAmount) {
		Queue<Game> gameQueue = gameQMap.get(gametype);
		processGameQueue(gametype, gameAmount, gameQueue);
		return gameQueue.peek().getGameId();
	}

	private void processGameQueue(String gametype, int gameAmount, Queue<Game> gameQueue) {
		if (gameQueue.peek().getCount() == 0) {// 0
			incrementGameId(gameQueue);
			processStartGame(gameQueue,gametype,gameAmount);
		} else if (gameQueue.peek().getCount() < 5) {// 1,2
			incrementGameId(gameQueue);
		} else if (gameQueue.peek().getCount() == 5) {
			Game startgame= gameQueue.poll();
			startgame.setState("STARTED");
			sendGameUpdate(startgame);
			incrementGameId(gameQueue);
			processStartGame(gameQueue,gametype,gameAmount);
		}
	}
	
	private void incrementGameId(Queue<Game> gameQueue) {
		gameQueue.peek().setCount(gameQueue.peek().getCount() + 1);
	}
	
	private void processStartGame(Queue<Game> gameQueue, String gameType,int gameAmount) {
		Game startgame = gameQueue.peek();
		startgame.setGameType(gameType);
		startgame.setGameAmount(gameAmount);
		startgame.setState("ADMIN");
	    startgame.setAdmin(addAdmin());
	    sendGameUpdate(startgame);
		startgame.setState("STARTGAME");
		delayGame(startgame, 20);
	}
	
	private Admin addAdmin(){
		Admin admin= new Admin();
		admin.setName("Admin");
		admin.setActive("Y");
		return admin;
    }
	
	private void sendGameUpdate(Game startgame) {		
		    String messagetoSend = new Gson().toJson(startgame);
		    processNextGameUpdate(startgame);
			ws.sendMessage(messagetoSend);			
	}

	public void processNextGameUpdate(Game game) {
		if (game.getState().equals("STARTGAME")) {
			removeGameFromQueue(game);
		} 
	}

	@Async
	void delayGame(Game startgame,int delay) {
		newSingleThreadScheduledExecutor().schedule(() -> {
			processGameUpdateAfterDelay(startgame);
		}, delay, TimeUnit.SECONDS);

	}

	private void processGameUpdateAfterDelay(Game game) {
		if(game.getState().equals("STARTGAME") ){
			Queue<Game> gamequeue = gameQMap.get(game.getGameType());
			if(gamequeue!=null && !gamequeue.isEmpty() && 
					gamequeue.peek().getGameId().equals(game.getGameId())){//shows game Not Started Yet 
				          if(gamequeue.peek().getCount()>=2){				        	  
				        	  processGameStart(game);
				          }else{
				        	  processGameAbort(game);
				          }				         
				}
		}
		else{
			sendGameUpdate(game);
		}
		
	}
	
	void processGameStart(Game game){
		sendGameUpdate(game);		
	}
	
    void processGameAbort(Game game){
    	removeGameFromQueue(game);
    	game.setState("ABORT");    	
    	sendGameUpdate(game);
	}

	void removeGameFromQueue(Game startgame) {
		gameQMap.get(startgame.getGameType()).remove(startgame);
	}

	
	@SuppressWarnings("deprecation")
	public String getpaymentTransId(String email) {
		Date date = new Date();
		return date.getDay()+'_'+date.getMonth()+"_"+date.getYear()+"_"+date.getHours()+"_"+date.getMinutes()+"_"+date.getSeconds()+email;
	}

}
