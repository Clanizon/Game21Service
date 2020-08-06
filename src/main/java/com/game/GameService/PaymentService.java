package com.game.GameService;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.model.payment.PayUResponse;

import com.game.model.payment.PaymentAudit;
import com.game.model.user.ClaimDetail;
import com.game.model.user.Users;
import com.game.repository.ClaimDetailRepository;
import com.game.repository.PaymentAuditRepository;
import com.game.util.GameUtil;
import com.game.util.security.HashService;

@Component
public class PaymentService {
	@Autowired
	private PaymentAuditRepository paymentAuditRepository;

	@Autowired
	private WalletService walletService;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private HashService hashService;

	@Autowired
	private GameUtil gameUtil;
	
	@Autowired
	ClaimDetailRepository claimrepo;

	public PaymentAudit createPaymentAudit(PaymentAudit paymentAudit) {

		return paymentAuditRepository.save(paymentAudit);

	}

	public Map<String, Object> createPayment(PaymentAudit paymentAudit) {
		Map<String, Object> outputMap = new HashMap<String, Object>();
		String hashvalue = "";
		String transId = "";
		transId = gameUtil.getpaymentTransId(paymentAudit.getEmail());
		paymentAudit.setTxnid(transId);
		hashvalue = hashService.createPaymentHash(paymentAudit);		
		outputMap.put("paymentaudit", createPaymentAudit(paymentAudit));
		outputMap.put("transId", transId);
		outputMap.put("hashvalue", hashvalue);
		return outputMap;
	}

	private void buildRequest(PaymentAudit paymentAudit, String transId) {
		paymentAudit.setTxnid(transId);
	}

	public Map<String, Object> getPaymentById(String transId) {
		Map<String, Object> outputMap = new HashMap<String, Object>();
		PaymentAudit paymentAudit = paymentAuditRepository.findByTxnid(transId);
		if (paymentAudit != null && paymentAudit.getTxnid() != null) {
			handlePaymentAuditStatus(outputMap, paymentAudit);
		} else {
			outputMap.put("paymentStatus", "Record Not Found in DB");
		}
		return outputMap;
	}

	private void handlePaymentAuditStatus(Map<String, Object> outputMap, PaymentAudit paymentAudit) {
		if (paymentAudit.getStatus().getStatusId().equals(1)) {
			outputMap.put("paymentStatus", "Inprogress");
		} else if (paymentAudit.getStatus().getStatusId().equals(2)) {
			outputMap.put("paymentStatus", "Success");
			outputMap.put("userWallet", walletService.getWalletByUser(paymentAudit.getEmail()));
			outputMap.put("paymentAudit", paymentAudit);
		} else if (paymentAudit.getStatus().getStatusId().equals(3)) {
			outputMap.put("paymentStatus", "Failure");
			outputMap.put("paymentAudit", paymentAudit);
		}
	}

	

	public Map<String, Object> handlePaymentSuccess(@Valid PayUResponse response) {
		if (response.getStatus() != null && response.getStatus().equals("Success")) {
			updatepaymentSuccess(response);

		}
		return null;
	}

	private void updatepaymentSuccess(@Valid PayUResponse response) {
		// TODO Auto-generated method stub
		System.out.println("in Success");
		paymentAuditRepository.updatePaymentStatus(2, response.getMerchantTransactionId());
		walletService.handleWalletPayment(response);
	}

	public Map<String, Object> handlePaymentFailure(@Valid PayUResponse response) {
		// TODO Auto-generated method stub
		paymentAuditRepository.updatePaymentStatus(3, response.getMerchantTransactionId());
		return null;
	}
	
	public Map<String, Object> handleClaim(@Valid ClaimDetail claim) {
		// TODO Auto-generated method stub
		Map<String, Object> outputMap= new HashMap<String, Object>();
		claimrepo.save(claim);
		Object wallet= walletService.updateWalletByuser(claim.getUserId()  , -(claim.getClaimAmount()), "DEBIT");
		outputMap.put("wallet", wallet);
		mailService.sendClaimEmail(claim);
		return outputMap;
	}


	

}
