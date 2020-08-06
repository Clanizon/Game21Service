package com.game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.wallet.*;

@RepositoryRestResource

public interface WalletAuditRepository extends CrudRepository<WalletAudit, Long>{
	  
	  
	}
