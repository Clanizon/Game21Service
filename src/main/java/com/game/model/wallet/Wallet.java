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

import com.game.model.user.Roles;


@Entity
@org.hibernate.annotations.DynamicUpdate
@Table(name = "WALLET")
public class Wallet {
	public Wallet() {
		super();
	}
	@Id
	@Column(name = "wallet_id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer  walletId;	
	String userId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date  updatedTimeStamp;
	private double walletAmount;
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
	public double getWalletAmount() {
		return walletAmount;
	}
	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	
}
