package com.game;
 
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.game.dto.Game;
import com.google.gson.Gson;
 
@Component
public class WSClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WSClient.class);
    WebSocketSession webSocketSession =null;
    WebSocketClient webSocketClient= null;
    
     @Value("${app.wsurl}")
	 private String wsurl;
     
     @Value("${app.adminemail}")
	 private String adminEmail;
   
    public void connect() {
        try {
        	if(wsurl!=null){
        		
        	}else{
        		wsurl="realkeyip.in:8085";
        	}
             webSocketClient = new StandardWebSocketClient();
             System.out.println(wsurl);
             System.out.println(adminEmail);
             webSocketSession = webSocketClient.doHandshake(new TextWebSocketHandler() {
                @Override
                public void handleTextMessage(WebSocketSession session, TextMessage message) {
                    LOGGER.info("received message - " + message.getPayload());
                    Game game = new Gson().fromJson(message.getPayload(), Game.class);
                   if (game.getState() != null && game.getState().equals("STARTGAME")
                		   && game.getIsDeclared()!=null  && game.getIsDeclared().equals("N")){
                	   //GameUtil gameUtil= new GameUtil();
                	   //.sendGameUpdate(game);
                   }
                }
 
                @Override
                public void afterConnectionEstablished(WebSocketSession session) {
                    LOGGER.info("established connection - " + session);
                }
            }, new WebSocketHttpHeaders(), URI.create("ws://"+wsurl+"/name")).get();
 
       
        } catch (Exception e) {
            LOGGER.error("Exception while accessing websockets", e);
        }
    }
    
    public void sendMessage(String messagetoSend){    
		// newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
		        try {
		            TextMessage message = new TextMessage(messagetoSend);
		            webSocketSession.sendMessage(message);
		            LOGGER.info("sent message - " + message.getPayload());
		        } catch (Exception e) {
		            LOGGER.error("Exception while sending a message", e);
		        }
		   // }, 1, 15, TimeUnit.SECONDS);
    }
}