package com.game.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.GameService.LoginService;
import com.game.GameService.MailService;
import com.game.GameService.OfferService;
import com.game.GameService.ReferralService;
import com.game.GameService.WalletService;
import com.game.model.ApiResponse;
import com.game.model.user.Users;
import com.game.repository.UserOfferRepository;
import com.game.repository.UserRepository;
import com.game.repository.WalletRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/signup")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	Double INITIAL_WALLET_AMOUNT = 10.00;
	@Autowired
	private WalletService walletService;

	@Autowired
	private OfferService offerService;
	
	
	
	@Autowired
	private LoginService loginService;
	
	




	@PostMapping("/login")
	public ApiResponse getUserByRole(@Valid @RequestBody Users user) {
		return loginService.login(user);
	}

	

	@PostMapping("/addPlayer")
	public ApiResponse createplayer(@Valid @RequestBody Users user) {
		return loginService.addPlayer(user);
	}



	

	


}