package com.rpp.agent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.jeecgframework.web.system.pojo.base.TSUser;


@Entity
@Table(name = "t_s_agent")
@PrimaryKeyJoinColumn(name = "id")
public class TSAgent extends TSUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/*
	 * 秘钥
	 */
	private String  key;
	
	/**
	 * 账户余额
	 */
	
	private double money;
	
	/**
	 * 账户锁定额度（可用余额=账户余额-账户锁定余额）
	 */
	private double lockMoney;
	
	/**
	 * 机构名称
	 */
	private  String  agtName;
	
	
	/**
	 * 机构简称
	 */
	private String  shortName;
	
	/**
	 * 银行卡号
	 */
	
	private String  bankCardNo;
	
	/**
	 * 银行名称
	 */
	private String  bankName;
	
	/**
	 * 联行号
	 * 
	 */
	private String bankCode;
	
	
	@Column(name = "key", length = 100)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "money", length = 100)
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	@Column(name = "lock_money", length = 100)
	public double getLockMoney() {
		return lockMoney;
	}

	public void setLockMoney(double lockMoney) {
		this.lockMoney = lockMoney;
	}

	@Column(name = "agt_name", length = 100)
	public String getAgtName() {
		return agtName;
	}

	public void setAgtName(String agtName) {
		this.agtName = agtName;
	}

	@Column(name = "short_name", length = 100)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "bank_card_no", length = 100)
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	@Column(name = "bank_name", length = 100)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "bank_code", length = 100)
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	
}
