package com.cdsoftware.activity.entity;

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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 转发字
 * @author onlineGenerator
 * @date 2018-03-29 10:12:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "transmit_word", schema = "")
@SuppressWarnings("serial")
public class TransmitWordEntity implements java.io.Serializable {
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
	/**活动*/
	@Excel(name="活动")
	private CdActivityEntity activity;
	/**转发字*/
	@Excel(name="转发字")
	private java.lang.String word;
	/**顺序*/
	@Excel(name="顺序")
	private java.lang.Integer orderNumber;
	/**总字数*/
	@Excel(name="总字数")
	private java.lang.Integer count;
	/**已发字数*/
	@Excel(name="已发字数")
	private java.lang.Integer haveCount;
	
	private java.lang.String code;
	private java.lang.String limit;
	
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
	 *@return: java.lang.String  活动
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name ="ACTIVITY")
	public CdActivityEntity getActivity(){
		return this.activity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动
	 */
	public void setActivity(CdActivityEntity activity){
		this.activity = activity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  转发字
	 */
	@Column(name ="WORD",nullable=true,length=32)
	public java.lang.String getWord(){
		return this.word;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  转发字
	 */
	public void setWord(java.lang.String word){
		this.word = word;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  顺序
	 */
	@Column(name ="ORDER_NUMBER",nullable=true,length=32)
	public java.lang.Integer getOrderNumber(){
		return this.orderNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  顺序
	 */
	public void setOrderNumber(java.lang.Integer orderNumber){
		this.orderNumber = orderNumber;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总字数
	 */
	@Column(name ="COUNT",nullable=true,length=32)
	public java.lang.Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总字数
	 */
	public void setCount(java.lang.Integer count){
		this.count = count;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  已发字数
	 */
	@Column(name ="HAVE_COUNT",nullable=true,length=32)
	public java.lang.Integer getHaveCount(){
		return this.haveCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  已发字数
	 */
	public void setHaveCount(java.lang.Integer haveCount){
		this.haveCount = haveCount;
	}
	
	
	@Column(name="code",nullable=true,length=32)
	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}
	@Column(name="limit",nullable=true,length=32)
	public java.lang.String getLimit() {
		return limit;
	}

	public void setLimit(java.lang.String limit) {
		this.limit = limit;
	}
	
}
