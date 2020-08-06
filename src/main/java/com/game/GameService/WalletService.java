package com.game.GameService;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.game.model.game.GameReferral;
import com.game.model.payment.PayUResponse;
import com.game.model.user.Users;
import com.game.model.wallet.Wallet;
import com.game.model.wallet.WalletAudit;
import com.game.repository.WalletAuditRepository;
import com.game.repository.WalletRepository;

@Component
public class WalletService {
	@Autowired
	private WalletAuditRepository walletAuditRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	public Object updateWalletbyId(WalletAudit walletAudit) {
		 walletRepository.updateWalletById(walletAudit.getAuditAmount(), walletAudit.getWalletId());
		 saveWalletAuidt(walletAudit);
		 return walletRepository.findByWalletId(walletAudit.getWalletId());
	}
	
	
	public void createWallet( Users newPlayer) {
		Wallet wallet= new Wallet();
		wallet.setUserId(newPlayer.getUserId());
		//wallet.setWalletAmount(referralrepository.findById(newPlayer.re));
		wallet.setUpdatedTimeStamp(new Date());
		walletRepository.save(wallet);
		newPlayer.setWallet(walletRepository.save(wallet));
	}
	
	public void updateReferralWallet(Users user, GameReferral newReferal) {
		Wallet walllet= walletRepository.findByUserId(newReferal.getReferralUser());
		WalletAudit walletAudit = new WalletAudit();
		if(walllet!=null && walllet.getWalletId()!=null){
			walletAudit.setAuditAmount(newReferal.getReferralAmount());
			walletAudit.setWalletId(walllet.getWalletId());
			walletAudit.setUpdatedTimeStamp(user.getUserCreatedDate());		
			walletAudit.setAction("CREDIT");
			walletAudit.setWalletSource("Referral");
			updateWalletbyId(walletAudit);
		}
     }
	
	public Wallet handleWalletPayment(PayUResponse response) {
		Wallet wallet = walletRepository.findByUserId(response.getCustomerEmail());
		if(wallet!=null){
		wallet.setWalletAmount(wallet.getWalletAmount()+  Double.parseDouble(response.getAmount()));
		Wallet newWallet= walletRepository.save(wallet);
		handleWalletPaymentAudit(response,newWallet);
		return newWallet;
		}
		return wallet;
	}
	
	private void handleWalletPaymentAudit(PayUResponse payresponse, Wallet newWallet) {
		WalletAudit walletAudit = new WalletAudit();
		walletAudit.setWalletId(newWallet.getWalletId());
		walletAudit.setUpdatedTimeStamp(new Date());
		walletAudit.setAction("CREDIT");
		walletAudit.setWalletSource(payresponse.getMerchantTransactionId());
		walletAudit.setAuditAmount( Double.parseDouble(payresponse.getAmount()));	
		saveWalletAuidt(walletAudit);
	}


	public Object getWalletByUser(String email) {
		// TODO Auto-generated method stub
		return walletRepository.findByUserId(email);
	}
	
	public Object updateWalletByuser(String userId,Double Amount,String action) {
		// TODO Auto-generated method stub
		walletRepository.updateWalletByUser(Amount, userId);
		Wallet wallet= walletRepository.findByUserId(userId);
		WalletAudit walletAudit= new WalletAudit();
		walletAudit.setWalletId(wallet.getWalletId());
		walletAudit.setAuditAmount(Amount);
		walletAudit.setAction(action);
		walletAudit.setUpdatedTimeStamp(new Date());
		saveWalletAuidt(walletAudit);
		return wallet;
	}
	
	
	
	@Async
	public void saveWalletAuidt(WalletAudit walletAudit)  {
		newSingleThreadScheduledExecutor().schedule(() -> {
			try {
				walletAuditRepository.save(walletAudit);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}, 0, TimeUnit.SECONDS);

	}


	public Object getWalletById(Integer walletId) {
		// TODO Auto-generated method stub
		return walletRepository.findByWalletId(walletId);
	}
	
	
}
