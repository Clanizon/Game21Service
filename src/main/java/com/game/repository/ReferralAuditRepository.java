package com.game.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.game.GameReferralAudit;

@RepositoryRestResource

public interface ReferralAuditRepository extends CrudRepository<GameReferralAudit, Long>{
	  
	}
