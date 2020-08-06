package com.game.model.wallet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@org.hibernate.annotations.DynamicUpdate
@Table(name = "WALLET_AUDIT")
public class WalletAudit {
	public WalletAudit() {
		super();
	}
	@Id
	@Column(name = "wallet_audit_id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer  walletAuditId;	
	String action;
	Integer  walletId;
	String walletSource;
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	Double auditAmount;
	String orderId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date  updatedTimeStamp;
	public Integer getWalletAuditId() {
		return walletAuditId;
	}
	public void setWalletAuditId(Integer walletAuditId) {
		this.walletAuditId = walletAuditId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Double getAuditAmount() {
		return auditAmount;
	}
	public void setAuditAmount(Double auditAmount) {
		this.auditAmount = auditAmount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
	public String getWalletSource() {
		return walletSource;
	}
	public void setWalletSource(String walletSource) {
		this.walletSource = walletSource;
	}
}
