package com.game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.game.GameType;

@RepositoryRestResource

public interface GameTypeRepository extends CrudRepository<GameType, Long>{
	  
	  
	}
