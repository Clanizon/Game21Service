package com.game.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.GameService.WalletService;
import com.game.model.game.GameOffer;
import com.game.model.game.GameReferral;
import com.game.model.game.GameType;
import com.game.model.user.UserOffer;
import com.game.model.wallet.WalletAudit;
import com.game.repository.GameOfferRepository;
import com.game.repository.GameReferralRepository;
import com.game.repository.GameTypeRepository;
import com.game.repository.UserOfferRepository;
import com.game.util.GameUtil;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameUtil gameUtil;
	@Autowired
	private GameTypeRepository gameTypeRepository;
	@Autowired
	private GameReferralRepository gameReferralRepository;
	@Autowired
	private GameOfferRepository gameOfferRepository;
	@Autowired
	private WalletService walletService;
	@Autowired
	private UserOfferRepository userOfferRepository;
	
	
	@GetMapping("/getGameId")
	public String  getGameId(@RequestParam String gameType,@RequestParam String userId
			) {	
		int amount=Integer.parseInt(gameType);
		
		walletService.updateWalletByuser(userId,(double) -(amount),"DEBIT");
		synchronized (userId) {			
			
			gameType="game_"+gameType;
			return gameUtil.assignGameId(gameType,amount);			
		}			
	}
	
	@GetMapping("/getGameTypes")
	public List<GameType>  getGameType() {			
		
			return (List<GameType>) gameTypeRepository.findAll();		
	}
	
	@GetMapping("/getoffers")
	public List<GameOffer>  getOffers() {					
			return (List<GameOffer>) gameOfferRepository.findAll();		
	}
	
	@PostMapping("/createoffers")
	public GameOffer createOffers(@Valid @RequestBody GameOffer offer) {			
		return gameOfferRepository.save(offer);
				
	}
	@PostMapping("/createreferral")
	public GameReferral createReferral(@Valid @RequestBody GameReferral  gamereferral) {					
		return gameReferralRepository.save(gamereferral);		
	}
	
	
	
	@PostMapping("/creategametype")
	public GameType  createGameType(@Valid @RequestBody GameType gameType) {					
			 return gameTypeRepository.save(gameType);		
	}
	
	
	
	@PostMapping("/redeemOffer")
	public Object  redeemOffer(@Valid @RequestBody UserOffer userOffer) {					
			  userOfferRepository.updateOfferStatus(userOffer.getUserOfferId(), 3);
			  WalletAudit walletAudit = new WalletAudit();
			  walletAudit.setWalletId(userOffer.getWalletId());
			  walletAudit.setAuditAmount(userOffer.getGameOffer().getOfferAmount());
			  walletAudit.setWalletSource("offer redeem");
			  walletAudit.setAction("CREDIT");
			 return walletService.updateWalletbyId(walletAudit);
	
	}
	
	@PostMapping("/associateoffer")
	public Object  associateOffer(@Valid @RequestBody UserOffer userOffer) {					
			  return userOfferRepository.save(userOffer);
	
	}
}