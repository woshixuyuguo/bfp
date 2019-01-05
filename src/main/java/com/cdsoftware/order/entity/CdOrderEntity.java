package com.cdsoftware.order.entity;
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

import com.cdsoftware.activity.entity.CdActivityEntity;
import com.cdsoftware.member.entity.CdMemberEntity;
import com.cdsoftware.program.entity.CdMiniProgramEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 订单管理
 * @author onlineGenerator
 * @date 2018-03-27 13:51:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cd_order", schema = "")
@SuppressWarnings("serial")
public class CdOrderEntity implements java.io.Serializable {
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
	/**小程序*/
	private CdMiniProgramEntity program;
	/**活动名称*/
	private CdActivityEntity activity;
	/**单价*/
	private java.math.BigDecimal price;
	/**数量*/
	private java.lang.Integer count;
	/**金额*/
	private java.math.BigDecimal amount;
	/**订单号*/
	private java.lang.String orderNo;
	/**上游订单号*/
	private java.lang.String orderId;
	/**会员*/
	private CdMemberEntity memb;
	/**下单时间*/
	private java.lang.String orderTime;
	/**支付时间*/
	private java.lang.String payTime;
	/**状态*/
	private java.lang.Integer state;
	/**姓名*/
	private java.lang.String name;
	/**手机号*/
	private java.lang.String phone;
	/**地址*/
	private java.lang.String address;
	/**省份*/
	private java.lang.String province;
	/**城市*/
	private java.lang.String city;
	/**身份证号*/
	private java.lang.String idcard;
	/**银行卡号*/
	private java.lang.String bankCard;
	/**快递单号*/
	private java.lang.String trackNumber;
	/**有效期*/
	private java.lang.String validity;
	
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
	 *@return: java.lang.String  小程序
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROGRAM_ID")
	//@Column(name ="PROGRAM_ID",nullable=true,length=32)
	public CdMiniProgramEntity getProgram(){
		return this.program;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小程序
	 */
	public void setProgram(CdMiniProgramEntity program){
		this.program = program;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动名称
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ACTIVITY_ID")
	//@Column(name ="ACTIVITY_ID",nullable=true,length=32)
	public CdActivityEntity getActivity(){
		return this.activity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setActivity(CdActivityEntity activity){
		this.activity = activity;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  单价
	 */
	
	@Column(name ="PRICE",nullable=true,length=32)
	public java.math.BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  单价
	 */
	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  数量
	 */
	
	@Column(name ="COUNT",nullable=true,length=32)
	public java.lang.Integer getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  数量
	 */
	public void setCount(java.lang.Integer count){
		this.count = count;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  金额
	 */
	
	@Column(name ="AMOUNT",nullable=true,length=32)
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
	
	@Column(name ="ORDER_NO",nullable=true,length=32)
	public java.lang.String getOrderNo(){
		return this.orderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderNo(java.lang.String orderNo){
		this.orderNo = orderNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上游订单号
	 */
	
	@Column(name ="ORDER_ID",nullable=true,length=32)
	public java.lang.String getOrderId(){
		return this.orderId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上游订单号
	 */
	public void setOrderId(java.lang.String orderId){
		this.orderId = orderId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会员
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name ="MEMB_ID",nullable=true)
	public CdMemberEntity getMemb(){
		return this.memb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员
	 */
	public void setMemb(CdMemberEntity memb){
		this.memb = memb;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下单时间
	 */
	
	@Column(name ="ORDER_TIME",nullable=true,length=32)
	public java.lang.String getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下单时间
	 */
	public void setOrderTime(java.lang.String orderTime){
		this.orderTime = orderTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付时间
	 */
	
	@Column(name ="PAY_TIME",nullable=true,length=32)
	public java.lang.String getPayTime(){
		return this.payTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付时间
	 */
	public void setPayTime(java.lang.String payTime){
		this.payTime = payTime;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态
	 */
	
	@Column(name ="STATE",nullable=true,length=32)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	
	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */
	
	@Column(name ="PHONE",nullable=true,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	
	@Column(name ="ADDRESS",nullable=true,length=100)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */
	
	@Column(name ="PROVINCE",nullable=true,length=32)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  城市
	 */
	
	@Column(name ="CITY",nullable=true,length=32)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  城市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号
	 */
	
	@Column(name ="IDCARD",nullable=true,length=32)
	public java.lang.String getIdcard(){
		return this.idcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号
	 */
	public void setIdcard(java.lang.String idcard){
		this.idcard = idcard;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行卡号
	 */
	
	@Column(name ="BANK_CARD",nullable=true,length=32)
	public java.lang.String getBankCard(){
		return this.bankCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行卡号
	 */
	public void setBankCard(java.lang.String bankCard){
		this.bankCard = bankCard;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递单号
	 */
	
	@Column(name ="TRACK_NUMBER",nullable=true,length=32)
	public java.lang.String getTrackNumber(){
		return this.trackNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递单号
	 */
	public void setTrackNumber(java.lang.String trackNumber){
		this.trackNumber = trackNumber;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  有效期
	 */
	
	@Column(name ="VALIDITY",nullable=true,length=32)
	public java.lang.String getValidity(){
		return this.validity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  有效期
	 */
	public void setValidity(java.lang.String validity){
		this.validity = validity;
	}
	
}
