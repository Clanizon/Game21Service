    package com.game.contoller;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.game.WSClient;
import com.game.dto.Game;
import com.google.gson.Gson;
import java.math.*;

public class Test1 {
	 private static final double GAME_COMMISION = 10;
	public static void main(String[] args) {
		getWinAmount(100,1);
		WSClient ws = new WSClient();
		Game game=new Game();
		game.setGameId("game_100_1");
		game.setState("STARTGAME");
		ws.connect();
		//ScheduledFuture  f= newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
			//ws.sendMessage(new Gson().toJson( game));
	
				//}, 15, 15, TimeUnit.SECONDS);
				
				//f.cancel(true);
			
		}
	
	
	private static double getWinAmount(double betAmount,int winnerCount) {	
		double winAmount= 0;
		if(winnerCount!=0){
			
			 
		winAmount= ((betAmount/winnerCount) * ((100-GAME_COMMISION)/100));
		}
		System.out.println(winAmount);
		return winAmount;
	}
}
