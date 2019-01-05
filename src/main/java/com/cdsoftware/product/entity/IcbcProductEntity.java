package com.cdsoftware.product.entity;
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

/**   
 * @Title: Entity
 * @Description: 商品管理
 * @author onlineGenerator
 * @date 2018-04-12 15:37:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "icbc_product", schema = "")
@SuppressWarnings("serial")
public class IcbcProductEntity implements java.io.Serializable {
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
	 * 图片地址
	 */
	private  java.lang.String  img;
	/**
	 * 图片展示位置
	 */
	private  java.lang.String position;
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
	 *@return: java.lang.String  名称
	 */
	
	@Column(name ="NAME",nullable=true,length=32)
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
	
	@Column(name ="ORIGINAL_PRICE",nullable=true,scale=2,length=32)
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
	
	@Column(name ="PRICE",nullable=true,scale=2,length=32)
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
	 *方法: 取得javax.xml.soap.Text
	 *@return: javax.xml.soap.Text  基本描述
	 */
	
	@Column(name ="DESCRIBE",nullable=true,length=1000)
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
	
	@Column(name ="HAVE_ADDRESS",nullable=true,length=32)
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
	
	@Column(name ="HAVE_CODE",nullable=true,length=32)
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
	
	@Column(name ="CODE_LENGTH",nullable=true,length=32)
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
	
	@Column(name ="STOCK",nullable=true,length=32)
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
	
	@Column(name ="SALE_COUNT",nullable=true,length=32)
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
	 *方法: 取得javax.xml.soap.Text
	 *@return: javax.xml.soap.Text  规则
	 */
	
	@Column(name ="RULE",nullable=true,length=1000)
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
	 *方法: 取得javax.xml.soap.Text
	 *@return: javax.xml.soap.Text  规格参数
	 */
	
	@Column(name ="PARAMETER",nullable=true,length=1000)
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
	 *方法: 取得javax.xml.soap.Text
	 *@return: javax.xml.soap.Text  售后服务
	 */
	
	@Column(name ="SERVICE",nullable=true,length=1000)
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
	@Column(name ="img",nullable=true,length=1000)
	public java.lang.String getImg() {
		return img;
	}

	public void setImg(java.lang.String img) {
		this.img = img;
	}

	
	@Column(name ="position",nullable=true,length=1000)
	public java.lang.String getPosition() {
		return position;
	}

	public void setPosition(java.lang.String position) {
		this.position = position;
	}
	
	
	
}
