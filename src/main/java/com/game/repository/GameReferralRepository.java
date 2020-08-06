package com.game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.game.GameReferral;

@RepositoryRestResource

public interface GameReferralRepository extends CrudRepository<GameReferral, Long>{
	  
	GameReferral findByReferralCode(String referralode);
	}
