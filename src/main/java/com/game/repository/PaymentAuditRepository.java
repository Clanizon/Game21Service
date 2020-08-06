package com.game.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.game.model.payment.PaymentAudit;

@RepositoryRestResource

public interface PaymentAuditRepository extends CrudRepository<PaymentAudit, Long>{
	  
	
	@Modifying
    @Transactional
    @Query("UPDATE PaymentAudit pa set payment_status =:status where pa.txnid=:txnid")
	 int updatePaymentStatus(@Param("status") Integer status,@Param("txnid") String txnid);
	
	PaymentAudit findByTxnid(String transId);
	}
     

    
