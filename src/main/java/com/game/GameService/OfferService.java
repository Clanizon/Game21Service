package com.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.model.user.UserOffer;
import com.game.model.user.Users;
import com.game.repository.GameOfferRepository;
import com.game.repository.UserOfferRepository;

@Component
public class OfferService {
 
	String DEFAULT_CODE="01095";
	@Autowired
	GameOfferRepository gameofferrepo;
	@Autowired
	UserOfferRepository userofferrepo;
    public UserOffer associateOffer(Users newuser){
    	UserOffer userOffer = new UserOffer();
    	userOffer.setGameOffer(gameofferrepo.findByOfferCode(DEFAULT_CODE));
    	userOffer.setStatus(1);
    	userOffer.setGameUser(newuser.getUserId());
    	userOffer.setCreatedDateTime(newuser.getUserCreatedDate());
    	return userofferrepo.save(userOffer);
    }
	
	
}
