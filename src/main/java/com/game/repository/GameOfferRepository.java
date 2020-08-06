package com.game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.game.GameOffer;

@RepositoryRestResource

public interface GameOfferRepository extends CrudRepository<GameOffer, Long>{
	  
	GameOffer findByOfferCode(String offercode);
	  
	}
