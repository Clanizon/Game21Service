package com.game.model.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.game.model.game.GameOffer;


@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "USER_OFFER")
public class UserOffer {
	public UserOffer() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_offer_id", nullable = false)
	private Integer userOfferId;	
	private String gameUser;
	@OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
	@JoinColumn(name = "offerId")
	private GameOffer gameOffer;
	private Integer status;
	@Transient 
	private Integer walletId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date  CreatedDateTime;
	public Integer getUserOfferId() {
		return userOfferId;
	}
	public void setUserOfferId(Integer userOfferId) {
		this.userOfferId = userOfferId;
	}
	public String getGameUser() {
		return gameUser;
	}
	public void setGameUser(String gameUser) {
		this.gameUser = gameUser;
	}
	
	public Date getCreatedDateTime() {
		return CreatedDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		CreatedDateTime = createdDateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public GameOffer getGameOffer() {
		return gameOffer;
	}
	public void setGameOffer(GameOffer gameOffer) {
		this.gameOffer = gameOffer;
	}
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	

}
