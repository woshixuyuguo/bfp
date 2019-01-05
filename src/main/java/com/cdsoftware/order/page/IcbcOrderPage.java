
package com.cdsoftware.order.page;
import com.cdsoftware.order.entity.IcbcOrderEntity;
import com.cdsoftware.order.entity.IcbcOrderDetailEntity;

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
 * @Description: 工商订单
 * @author onlineGenerator
 * @date 2018-04-12 15:21:51
 * @version V1.0   
 *
 */
public class IcbcOrderPage implements java.io.Serializable {
	/**保存-订单明细*/
	private List<IcbcOrderDetailEntity> icbcOrderDetailList = new ArrayList<IcbcOrderDetailEntity>();
	public List<IcbcOrderDetailEntity> getIcbcOrderDetailList() {
		return icbcOrderDetailList;
	}
	public void setIcbcOrderDetailList(List<IcbcOrderDetailEntity> icbcOrderDetailList) {
		this.icbcOrderDetailList = icbcOrderDetailList;
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
	/**商品*/
	private java.lang.String productId;
	/**数量*/
	private java.lang.Integer count;
	/**单价*/
	private java.math.BigDecimal price;
	/**总价*/
	private java.math.BigDecimal amount;
	/**优惠券*/
	private java.lang.String couponId;
	/**实际付款*/
	private java.math.BigDecimal realAmount;
	/**状态*/
	private java.lang.String state;
	/**省份*/
	private java.lang.String provinceId;
	/**城市*/
	private java.lang.String cityId;
	/**地址*/
	private java.lang.String address;
	/**物流单号*/
	private java.lang.String expressNo;
	/**物流名称*/
	private java.lang.String expressName;
	
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
	 *@return: java.lang.String  商品
	 */
	public java.lang.String getProductId(){
		return this.productId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品
	 */
	public void setProductId(java.lang.String productId){
		this.productId = productId;
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
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  总价
	 */
	public java.math.BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  总价
	 */
	public void setAmount(java.math.BigDecimal amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优惠券
	 */
	public java.lang.String getCouponId(){
		return this.couponId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优惠券
	 */
	public void setCouponId(java.lang.String couponId){
		this.couponId = couponId;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实际付款
	 */
	public java.math.BigDecimal getRealAmount(){
		return this.realAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实际付款
	 */
	public void setRealAmount(java.math.BigDecimal realAmount){
		this.realAmount = realAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */
	public java.lang.String getProvinceId(){
		return this.provinceId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setProvinceId(java.lang.String provinceId){
		this.provinceId = provinceId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  城市
	 */
	public java.lang.String getCityId(){
		return this.cityId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  城市
	 */
	public void setCityId(java.lang.String cityId){
		this.cityId = cityId;
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
	 *@return: java.lang.String  物流单号
	 */
	public java.lang.String getExpressNo(){
		return this.expressNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流单号
	 */
	public void setExpressNo(java.lang.String expressNo){
		this.expressNo = expressNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流名称
	 */
	public java.lang.String getExpressName(){
		return this.expressName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流名称
	 */
	public void setExpressName(java.lang.String expressName){
		this.expressName = expressName;
	}
}
