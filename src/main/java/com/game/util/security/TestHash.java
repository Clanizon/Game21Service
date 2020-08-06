package com.game.util.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestHash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hashString=  "SwEwP57z|100_120_9_1_41pvdrdeena@gmail.com|1.0|GAME21|Deena|pvdrdeena@gmail.com|||||||||||B6ILu2BsXn";
		//./String hashString1= "SwEwP57z|100_120_9_1_41pvdrdeena@gmail.com|1.0|GAME21|Deena|pvdrdeena@gmail.com|||||||||||B6ILu2BsXn";
		String hashString1=           "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		System.out.println(calculateHash("SHA-512", hashString1));
		System.out.println(hashCal("SHA-512", hashString));

	}


		
		
		public static  String calculateHash(String type, String hashString) {
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
		
		  public static String hashCal(String type, String str) {
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


