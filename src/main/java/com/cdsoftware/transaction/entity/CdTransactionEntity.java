package com.cdsoftware.transaction.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 消费流水
 * @author onlineGenerator
 * @date 2018-03-27 13:51:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cd_transaction", schema = "")
@SuppressWarnings("serial")
public class CdTransactionEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**修改人名称*/
	private java.lang.String updateName;
	/**修改日期*/
	private java.util.Date updateDate;
	/**会员*/
	@Excel(name="会员")
	private java.lang.String membId;
	/**金额*/
	@Excel(name="金额")
	private java.math.BigDecimal amount;
	/**订单号*/
	@Excel(name="订单号")
	private java.lang.String orderId;
	/**小程序*/
	@Excel(name="小程序")
	private java.lang.String programId;
	/**类型*/
	@Excel(name="类型")
	private java.lang.Integer type;
	/**地点*/
	@Excel(name="地点")
	private java.lang.String address;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会员
	 */
	@Column(name ="MEMB_ID",nullable=true,length=32)
	public java.lang.String getMembId(){
		return this.membId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员
	 */
	public void setMembId(java.lang.String membId){
		this.membId = membId;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  金额
	 */
	@Column(name ="AMOUNT",nullable=true,scale=2,length=32)
	public java.math.BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  金额
	 */
	public void setAmount(java.math.BigDecimal amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单号
	 */
	@Column(name ="ORDER_ID",nullable=true,length=32)
	public java.lang.String getOrderId(){
		return this.orderId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderId(java.lang.String orderId){
		this.orderId = orderId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  小程序
	 */
	@Column(name ="PROGRAM_ID",nullable=true,length=32)
	public java.lang.String getProgramId(){
		return this.programId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小程序
	 */
	public void setProgramId(java.lang.String programId){
		this.programId = programId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  类型
	 */
	@Column(name ="TYPE",nullable=true,length=32)
	public java.lang.Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  类型
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地点
	 */
	@Column(name ="ADDRESS",nullable=true,length=100)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地点
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
}
