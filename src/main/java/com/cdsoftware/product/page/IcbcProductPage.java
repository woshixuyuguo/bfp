
package com.cdsoftware.product.page;
import com.cdsoftware.product.entity.IcbcProductEntity;
import com.cdsoftware.product.entity.IcbcProductPictureEntity;

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
 * @Description: 商品管理
 * @author onlineGenerator
 * @date 2018-04-12 15:37:13
 * @version V1.0   
 *
 */
public class IcbcProductPage implements java.io.Serializable {
	/**保存-商品图片*/
	private List<IcbcProductPictureEntity> icbcProductPictureList = new ArrayList<IcbcProductPictureEntity>();
	public List<IcbcProductPictureEntity> getIcbcProductPictureList() {
		return icbcProductPictureList;
	}
	public void setIcbcProductPictureList(List<IcbcProductPictureEntity> icbcProductPictureList) {
		this.icbcProductPictureList = icbcProductPictureList;
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
	/**名称*/
	private java.lang.String name;
	/**原价*/
	private java.lang.String originalPrice;
	/**售价*/
	private java.lang.String price;
	/**基本描述*/
	private java.lang.String describe;
	/**是否收货*/
	private java.lang.String haveAddress;
	/**生成码*/
	private java.lang.String haveCode;
	/**码长度*/
	private java.lang.Integer codeLength;
	/**库存*/
	private java.lang.Integer stock;
	/**售量*/
	private java.lang.Integer saleCount;
	/**规则*/
	private java.lang.String rule;
	/**规格参数*/
	private java.lang.String parameter;
	/**售后服务*/
	private java.lang.String service;
	
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
	 *@return: java.lang.String  名称
	 */
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原价
	 */
	public java.lang.String getOriginalPrice(){
		return this.originalPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原价
	 */
	public void setOriginalPrice(java.lang.String originalPrice){
		this.originalPrice = originalPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  售价
	 */
	public java.lang.String getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  售价
	 */
	public void setPrice(java.lang.String price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  基本描述
	 */
	public java.lang.String getDescribe(){
		return this.describe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  基本描述
	 */
	public void setDescribe(java.lang.String describe){
		this.describe = describe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否收货
	 */
	public java.lang.String getHaveAddress(){
		return this.haveAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否收货
	 */
	public void setHaveAddress(java.lang.String haveAddress){
		this.haveAddress = haveAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生成码
	 */
	public java.lang.String getHaveCode(){
		return this.haveCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生成码
	 */
	public void setHaveCode(java.lang.String haveCode){
		this.haveCode = haveCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  码长度
	 */
	public java.lang.Integer getCodeLength(){
		return this.codeLength;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  码长度
	 */
	public void setCodeLength(java.lang.Integer codeLength){
		this.codeLength = codeLength;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  库存
	 */
	public java.lang.Integer getStock(){
		return this.stock;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  库存
	 */
	public void setStock(java.lang.Integer stock){
		this.stock = stock;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  售量
	 */
	public java.lang.Integer getSaleCount(){
		return this.saleCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  售量
	 */
	public void setSaleCount(java.lang.Integer saleCount){
		this.saleCount = saleCount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规则
	 */
	public java.lang.String getRule(){
		return this.rule;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规则
	 */
	public void setRule(java.lang.String rule){
		this.rule = rule;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格参数
	 */
	public java.lang.String getParameter(){
		return this.parameter;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格参数
	 */
	public void setParameter(java.lang.String parameter){
		this.parameter = parameter;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  售后服务
	 */
	public java.lang.String getService(){
		return this.service;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  售后服务
	 */
	public void setService(java.lang.String service){
		this.service = service;
	}
}
