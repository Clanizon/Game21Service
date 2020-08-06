package com.game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.user.Users;

@RepositoryRestResource

public interface UserRepository extends CrudRepository<Users, Long>{
	  
	  Users findByUserId(String userId);
	  
	   Users findByUserIdAndUserPassword(String userId,String userPassword);
	}
