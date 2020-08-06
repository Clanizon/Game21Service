package com.game.util.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;
@Component
public class HashCal {
	
	
	public  String calculateHash(String type, String hashString) {
		StringBuilder hash = new StringBuilder();
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(type);
			messageDigest.update(hashString.getBytes());
			byte[] mdbytes = messageDigest.digest();
			for (byte hashByte : mdbytes) {
				 hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash.toString();
	}
	
	  public String hashCal(String type, String str) {
	        byte[] hashseq = str.getBytes();
	        StringBuffer hexString = new StringBuffer();
	        try {
	            MessageDigest algorithm = MessageDigest.getInstance(type);
	            algorithm.reset();
	            algorithm.update(hashseq);
	            byte messageDigest[] = algorithm.digest();
	            for (int i = 0; i < messageDigest.length; i++) {
	                String hex = Integer.toHexString(0xFF & messageDigest[i]);
	                if (hex.length() == 1) {
	                    hexString.append("0");
	                }
	                hexString.append(hex);
	            }

	        } catch (NoSuchAlgorithmException nsae) {
	        }
	        return hexString.toString();
	    }

}
