package com.game.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.wallet.*;

@RepositoryRestResource

public interface WalletRepository extends CrudRepository<Wallet, Long>{
	  
	
	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE Wallet W set WALLET_AMOUNT =W.WALLET_AMOUNT +:walletAmount where W.WallET_ID = :walletId",
	            nativeQuery = true)
	void updateWalletById(@Param("walletAmount") Double walletAmount, @Param("walletId") Integer walletId);
	 
	 
	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE Wallet W set WALLET_AMOUNT =W.WALLET_AMOUNT +:walletAmount where W.USER_ID = :userId",
	            nativeQuery = true)
	void updateWalletByUser(@Param("walletAmount") Double walletAmount, @Param("userId") String userId);
	
	 Wallet findByUserId(String userId);
	 
	 Wallet findByWalletId(Integer id);
	  
	}
