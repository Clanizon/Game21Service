package com.game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.user.ClaimDetail;;

@RepositoryRestResource

public interface ClaimDetailRepository extends CrudRepository<ClaimDetail, Long>{
	  
	  
	}
