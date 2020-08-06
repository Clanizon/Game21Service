package com.game.util.security;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.model.payment.PaymentAudit;
import com.game.util.security.HashCal;

@Component
public class HashService {
	@Autowired
	private HashCal hashCal;

	public String createPaymentHash(PaymentAudit paymentAudit) {
		String hashvalue;
		String salt = "B6ILu2BsXn";
		paymentAudit.setKey("SwEwP57z");
		String hashString = "";
		Field[] fieldInfo = paymentAudit.getClass().getDeclaredFields();
		String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		String[] hashVarSeq = hashSequence.split("\\|");
		try {
			for (int i = 0; i < fieldInfo.length; i++) {
				fieldInfo[i].setAccessible(true);
				for (String part : hashVarSeq) {					
					if (fieldInfo[i].getName().equals(part)) {
						hashString = (empty(fieldInfo[i].get(paymentAudit))) ? hashString.concat("")
								: hashString.concat((fieldInfo[i].get(paymentAudit).toString()).trim());
						hashString = hashString.concat("|");
					}
					
				}
			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hashString= hashString.concat(salt);
		
		System.out.println(hashString);
		hashvalue = hashCal.calculateHash("SHA-512", hashString);
		System.out.println(hashvalue);
		return hashvalue;
	}
	
	public boolean empty(Object object) {
		if (object == null || object.toString().trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
}
