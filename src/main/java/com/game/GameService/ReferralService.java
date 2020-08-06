package com.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.model.game.GameReferral;
import com.game.model.game.GameReferralAudit;
import com.game.model.user.Users;
import com.game.repository.GameReferralRepository;
import com.game.repository.ReferralAuditRepository;

@Component
public class ReferralService {

    @Autowired
    private WalletService walletService;
    
    @Autowired
	private GameReferralRepository referalRepo;
    
    @Autowired
    private ReferralAuditRepository referralAuditRepo;
	
	public GameReferral addReferral(Users user){
		GameReferral newReferal = null;
		if(user!=null && user.getReferal()!=null && user.getReferal().getReferralCode()!=null){
		  newReferal=  referalRepo.findByReferralCode(user.getReferal().getReferralCode());
		  addreferralAudit(user, newReferal);
		 // walletService.updateReferralWallet(user, newReferal);
		}
		return newReferal;
	}
	
	public void addreferralAudit(Users user, GameReferral newReferal) {
		System.out.println("in Referal Audit");
		GameReferralAudit referralAudit = new GameReferralAudit();
		  referralAudit.setCreatedDateTime(user.getUserCreatedDate());
		  referralAudit.setGameReferralId(newReferal.getReferralId());
		  referralAudit.setLoginUserId(user.getUserId());
		  referralAuditRepo.save(referralAudit);
	}
	
	

}
