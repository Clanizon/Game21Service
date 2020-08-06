package com.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.model.ApiResponse;
import com.game.model.user.Users;
import com.game.repository.ClaimDetailRepository;
import com.game.repository.UserOfferRepository;
import com.game.repository.UserRepository;
import com.game.repository.WalletRepository;
import com.game.util.GameUtil;
import com.game.model.Constants;

@Component
public class LoginService {
	@Autowired
	private UserRepository userRepository;

	
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private ReferralService referralService;

	@Autowired
	private UserOfferRepository userOfferRepository;


	
	@Autowired
	ClaimDetailRepository claimrepo;

	public ApiResponse login(Users user) {
		ApiResponse apiresponse = new ApiResponse();
		Users loggedInUser = userRepository.findByUserIdAndUserPassword(user.getUserId(), user.getUserPassword());
		if (loggedInUser != null && loggedInUser.getUserId() != null) {
			loggedInUser.setWallet(walletRepository.findByUserId(loggedInUser.getUserId()));
			loggedInUser.setOffer(userOfferRepository.findByGameUser(loggedInUser.getUserId()));
			apiresponse.setMessage("LOGIN_SUCCESS");
			apiresponse.setStatus(Constants.STATUS_SUCCESS);
			apiresponse.setResult(loggedInUser);
		}else{
			apiresponse.setMessage("LOGIN_FAILURE");
			apiresponse.setStatus(Constants.STATUS_FAILURE);
		}

		return apiresponse;
	}
	
	public ApiResponse addPlayer(Users user) {
		ApiResponse apiresponse = new ApiResponse();
		if (null != userRepository.findByUserId(user.getUserId())) {
			apiresponse.setMessage("User Already Exists");
			apiresponse.setStatus(Constants.STATUS_FAILURE);
			
	 } else {
		Users newPlayer = new Users();
		try{
			newPlayer = userRepository.save(user);
			walletService.createWallet(newPlayer);									
			newPlayer.setOffer(offerService.associateOffer(newPlayer));
			referralService.addReferral(user);
			mailService.sendEmailDelay(user.getUserId());
			apiresponse.setMessage("User Already Exists");
			apiresponse.setStatus(Constants.STATUS_SUCCESS);
			apiresponse.setResult(newPlayer);
		}
		catch(Exception e){		
			e.printStackTrace();
			apiresponse.setMessage("Exception while Adding User");
			apiresponse.setStatus(Constants.STATUS_FAILURE);
			apiresponse.setResult(newPlayer);
		}
	}
		return apiresponse;
	}
	

}
