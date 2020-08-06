package com.game.model.game;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "GAME_REFERRAL_AUDIT")
public class GameReferralAudit {
	public GameReferralAudit() {
		super();
	}
	@Id
	@Column(name = "AUDIT_ID", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer auditId;
	
	private Integer gameReferralId;
	private String loginUserId ;
	private String referralUserId ;
	private String  referralCode;
	private double  referralAmount;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date  createdDateTime;
	public Integer getGameReferralId() {
		return gameReferralId;
	}
	public void setGameReferralId(Integer gameReferralId) {
		this.gameReferralId = gameReferralId;
	}
	
	public String getReferralUserId() {
		return referralUserId;
	}
	public void setReferralUserId(String referralUserId) {
		this.referralUserId = referralUserId;
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
	public Integer getAuditId() {
		return auditId;
	}
	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	
	
	

}
