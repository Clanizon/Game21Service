package com.game.model.game;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "GAME_REFERRAL")
public class GameReferral {
	public GameReferral() {
		super();
	}
	@Id
	@Column(name = "REFERRAL_ID", nullable = false)
	private Integer referralId;
	private String referralLocation;
	private String referralUser ;
	private String  referralCode;
	private double  referralAmount;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date  createdDateTime;
	public Integer getReferralId() {
		return referralId;
	}
	public void setReferralId(Integer referralId) {
		this.referralId = referralId;
	}
	public String getReferralLocation() {
		return referralLocation;
	}
	public void setReferralLocation(String referralLocation) {
		this.referralLocation = referralLocation;
	}
	public String getReferralUser() {
		return referralUser;
	}
	public void setReferralUser(String referralUser) {
		this.referralUser = referralUser;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public double getReferralAmount() {
		return referralAmount;
	}
	public void setReferralAmount(double referralAmount) {
		this.referralAmount = referralAmount;
	}
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	

}
