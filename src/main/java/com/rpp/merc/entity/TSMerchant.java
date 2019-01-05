package com.rpp.merc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.jeecgframework.web.system.pojo.base.TSUser;

import com.rpp.agent.entity.TSAgent;


@Entity
@Table(name = "t_s_merchant")
@PrimaryKeyJoinColumn(name = "id")
public class TSMerchant extends TSUser implements java.io.Serializable {
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
	 * 商户名称
	 */
	private  String  mercName;
	
	
	/**
	 * 商户简称
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
	
	
	/**
	 * 所属机构商户
	 * 
	 * @return
	 */
	private String agentId;
	
	/**
	 * 所属机构商户号
	 * @return
	 */
	private String agentCode;
	
	
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

	@Column(name = "merc_name", length = 100)
	public String getMercName() {
		return mercName;
	}

	public void setMercName(String mercName) {
		this.mercName = mercName;
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
	@Column(name = "agent_id", length = 100)
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	@Column(name = "agent_code", length = 100)
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	
	
}
