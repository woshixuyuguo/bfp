
package com.cdsoftware.order.page;
import com.cdsoftware.order.entity.CdOrderEntity;
import com.cdsoftware.order.entity.CdOrderDetailEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;


/**   
 * @Title: Entity
 * @Description: 订单管理
 * @author onlineGenerator
 * @date 2018-03-27 13:51:38
 * @version V1.0   
 *
 */
public class CdOrderPage implements java.io.Serializable {
	/**保存-订单明细*/
	private List<CdOrderDetailEntity> cdOrderDetailList = new ArrayList<CdOrderDetailEntity>();
	public List<CdOrderDetailEntity> getCdOrderDetailList() {
		return cdOrderDetailList;
	}
	public void setCdOrderDetailList(List<CdOrderDetailEntity> cdOrderDetailList) {
		this.cdOrderDetailList = cdOrderDetailList;
	}

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
	private java.lang.String programId;
	/**活动名称*/
	private java.lang.String activityId;
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
	private java.lang.String membId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动名称
	 */
	public java.lang.String getActivityId(){
		return this.activityId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setActivityId(java.lang.String activityId){
		this.activityId = activityId;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  单价
	 */
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下单时间
	 */
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
