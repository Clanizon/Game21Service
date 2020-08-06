package com.game.util;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtility  {

	    @Autowired
	    private org.springframework.mail.javamail.JavaMailSender javaMailSender;
	    
	    
	    
    public String sendEmail(String subject,String message,String ToAddress) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(ToAddress);
        System.out.println(message);
        System.out.println(ToAddress);
        msg.setSubject(subject);
        msg.setText(message);
        javaMailSender.send(msg);
        return "success";
    }

    public void sendEmailWithAttachment(String userId) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setTo(userId);

        helper.setSubject("welcome to GaME21");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Welcome to GaME21!</h1>", true);

        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        helper.addAttachment("Game21.jpg", new ClassPathResource("Game21.jpg"));

        javaMailSender.send(msg);

    }
}
