package com.game.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.GameService.PaymentService;
import com.game.GameService.WalletService;
import com.game.model.user.ClaimDetail;
import com.game.model.wallet.Wallet;
import com.game.model.wallet.WalletAudit;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	@Autowired
	private PaymentService paymentService;

	
	@SuppressWarnings("unchecked")
	@PostMapping("/updatewallet")
	public ResponseEntity<Object> updatewallet(@Valid @RequestBody WalletAudit wallet) {
		return ResponseEntity.status(HttpStatus.OK).body(walletService.updateWalletbyId(wallet));
		
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/updatewalletbyuser")
	public ResponseEntity<Object> updatewalletbyUser(@Valid @RequestBody Wallet wallet) {
		return ResponseEntity.status(HttpStatus.OK).body(walletService.updateWalletByuser(wallet.getUserId(), wallet.getWalletAmount(),"CREDIT"));
		
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/getwalletbyid")
	public ResponseEntity<Object> getWalletById(@Valid @RequestBody Wallet wallet) {
		return ResponseEntity.status(HttpStatus.OK).body(walletService.getWalletById(wallet.getWalletId()));
		
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/claimamount")
	public ResponseEntity<Object> claimAmount(@Valid @RequestBody ClaimDetail claimDetail) {
		return ResponseEntity.status(HttpStatus.OK).body(paymentService.handleClaim(claimDetail));
		
	}
	
}