package com.game.GameService;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.game.model.user.ClaimDetail;
import com.game.util.MailUtility;

@Component
public class MailService {
	@Autowired
	private MailUtility mailUtility;
	 @Value("${app.adminemail}")
	 private String adminEmail;
	
 
	@Async
	public void sendEmailDelay(String userId) throws InterruptedException {
		newSingleThreadScheduledExecutor().schedule(() -> {
			try {
				
				mailUtility.sendEmailWithAttachment(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}, 0, TimeUnit.SECONDS);

	}
	
	
	public void sendClaimEmail(ClaimDetail claim){  
		System.out.println(adminEmail);
		String adminMessage="Claim  Amount:"+claim.getClaimAmount()+"\nUser Email :"+claim.getUserId()+"\n Mobile :"+claim.getMobile();
		String claimSubject="Redeem Request:GaME21 Wallet";
		String userMessage="Your Redeem request is in progress and money :"+claim.getClaimAmount()+":  will be credited in 24 HRS ";
		try {
			sendEmail(claimSubject,adminMessage,adminEmail);
			sendEmail(claimSubject,userMessage,claim.getUserId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Async
	public void sendEmail(String subject,String message,String ToAddress) throws InterruptedException {
		newSingleThreadScheduledExecutor().schedule(() -> {
			try {
				
				mailUtility.sendEmail(subject,message,ToAddress);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}, 0, TimeUnit.SECONDS);

	}
	
	
}
