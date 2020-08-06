package com.game.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.user.UserOffer;

@RepositoryRestResource

public interface UserOfferRepository extends CrudRepository<UserOffer, Long>{
	  
     @Query("select uof from UserOffer uof where uof.gameUser = :userId and status=1")
	 UserOffer findByGameUser(@Param("userId") String userId);
	 
	 @Modifying
     @Transactional
     @Query("UPDATE UserOffer uof set status =:status where uof.userOfferId=:userOfferId")
	 void updateOfferStatus(@Param("userOfferId") Integer userOfferId,@Param("status") Integer status);
	}
