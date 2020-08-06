package com.game.model.game;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@org.hibernate.annotations.DynamicUpdate
@Table(name = "GAME_TYPE")
public class GameType {
	public GameType() {
		super();
	}
	@Id
	@Column(name = "game_type_id", nullable = false)
    Integer  id;	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
	String gameType;
	String amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date  updatedTimeStamp;
}
