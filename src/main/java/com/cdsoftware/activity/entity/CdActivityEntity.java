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

import com.cdsoftware.program.entity.CdMiniProgramEntity;

/**   
 * @Title: Entity
 * @Description: 活动管理
 * @author onlineGenerator
 * @date 2018-03-27 13:47:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cd_activity", schema = "")
@SuppressWarnings("serial")
public class CdActivityEntity implements java.io.Serializable {
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
	/**活动名称*/
	@Excel(name="活动名称")
	private java.lang.String name;
	/**关联小程序*/
	@Excel(name="关联小程序")
	private CdMiniProgramEntity program;
	/**开始时间*/
	@Excel(name="开始时间")
	private java.util.Date startTime;
	/**结束时间*/
	@Excel(name="结束时间")
	private java.util.Date endTime;
	/**活动类型*/
	@Excel(name="活动类型")
	private java.lang.Integer type;
	/**转发字数*/
	@Excel(name="转发字数")
	private java.lang.Integer wordCount;
	/**转发次数*/
	@Excel(name="转发次数")
	private java.lang.Integer transmitCount;
	/**转发限制*/
	@Excel(name="转发限制")
	private java.lang.Integer transmitLimit;
	/**原价*/
	@Excel(name="原价")
	private java.math.BigDecimal originalPrice;
	/**活动价*/
	@Excel(name="活动价")
	private java.math.BigDecimal price;
	/**主办方*/
	@Excel(name="主办方")
	private java.lang.String sponsor;
	/**联系人*/
	@Excel(name="联系人")
	private java.lang.String linkMan;
	/**联系电话*/
	@Excel(name="联系电话")
	private java.lang.String linkPhone;
	/**是否允许重复购买*/
	@Excel(name="是否允许重复购买")
	private java.lang.String allowRepeat;
	/**活动描述*/
	@Excel(name="活动描述")
	private java.lang.String describe;
	/**活动主题图片*/
	@Excel(name="活动主题图片")
	private java.lang.String img;
	/**一次购买多个*/
	@Excel(name="一次购买多个")
	private java.lang.String allowBuyMany;
	/**是否生成兑换码*/
	@Excel(name="是否生成兑换码")
	private java.lang.String generateExchangeCode;
	/**兑换码长度*/
	@Excel(name="兑换码长度")
	private java.lang.Integer exchangeCodeLength;
	/**有效期*/
	@Excel(name="有效期")
	private java.lang.Integer validity;
	/**兑换码图片*/
	@Excel(name="兑换码图片")
	private java.lang.String exchangeCodeImg;
	/**周期*/
	@Excel(name="周期")
	private java.lang.String validityUnit;
	
	private java.lang.String state;
	
	private  java.lang.String category;
	
	private java.lang.String rule;
	
	private java.lang.String isBindPhone;
	private java.lang.String carousel;
	private String messageId;
	private Integer buyLimit;
	private String haveAddress;
	/*商客创意token*/
	private String token;
	/*商客创意tokenFlag*/
	private String tokenFlag;
	/*商客创意用户名 */
	private  String userName;
	/*商客创意密码*/
	private String password;
	
	@Excel(name="跳转小程序")
	private CdMiniProgramEntity otherProgram;
	

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
	 *@return: java.lang.String  活动名称
	 */
	@Column(name ="NAME",nullable=false,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关联小程序
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROGRAM_ID")
//	@Column(name ="PROGRAM_ID",nullable=false,length=32)
	public CdMiniProgramEntity getProgram(){
		return this.program;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关联小程序
	 */
	public void setProgram(CdMiniProgramEntity program){
		this.program = program;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="START_TIME",nullable=true,length=32)
	public java.util.Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="END_TIME",nullable=true,length=32)
	public java.util.Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  活动类型
	 */
	@Column(name ="TYPE",nullable=false,length=32)
	public java.lang.Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  活动类型
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  转发字数
	 */
	@Column(name ="WORD_COUNT",nullable=true,length=32)
	public java.lang.Integer getWordCount(){
		return this.wordCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  转发字数
	 */
	public void setWordCount(java.lang.Integer wordCount){
		this.wordCount = wordCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  转发次数
	 */
	@Column(name ="TRANSMIT_COUNT",nullable=true,length=32)
	public java.lang.Integer getTransmitCount(){
		return this.transmitCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  转发次数
	 */
	public void setTransmitCount(java.lang.Integer transmitCount){
		this.transmitCount = transmitCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  转发限制
	 */
	@Column(name ="TRANSMIT_LIMIT",nullable=true,length=32)
	public java.lang.Integer getTransmitLimit(){
		return this.transmitLimit;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  转发限制
	 */
	public void setTransmitLimit(java.lang.Integer transmitLimit){
		this.transmitLimit = transmitLimit;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  原价
	 */
	@Column(name ="ORIGINAL_PRICE",nullable=true,scale=2,length=32)
	public java.math.BigDecimal getOriginalPrice(){
		return this.originalPrice;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  原价
	 */
	public void setOriginalPrice(java.math.BigDecimal originalPrice){
		this.originalPrice = originalPrice;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  活动价
	 */
	@Column(name ="PRICE",nullable=true,scale=2,length=32)
	public java.math.BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  活动价
	 */
	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主办方
	 */
	@Column(name ="SPONSOR",nullable=false,length=50)
	public java.lang.String getSponsor(){
		return this.sponsor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主办方
	 */
	public void setSponsor(java.lang.String sponsor){
		this.sponsor = sponsor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */
	@Column(name ="LINK_MAN",nullable=false,length=50)
	public java.lang.String getLinkMan(){
		return this.linkMan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setLinkMan(java.lang.String linkMan){
		this.linkMan = linkMan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */
	@Column(name ="LINK_PHONE",nullable=false,length=50)
	public java.lang.String getLinkPhone(){
		return this.linkPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setLinkPhone(java.lang.String linkPhone){
		this.linkPhone = linkPhone;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否允许重复购买
	 */
	@Column(name ="ALLOW_REPEAT",nullable=true,length=32)
	public java.lang.String getAllowRepeat(){
		return this.allowRepeat;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否允许重复购买
	 */
	public void setAllowRepeat(java.lang.String allowRepeat){
		this.allowRepeat = allowRepeat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动描述
	 */
	@Column(name ="DESCRIBE",nullable=true,length=32)
	public java.lang.String getDescribe(){
		return this.describe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动描述
	 */
	public void setDescribe(java.lang.String describe){
		this.describe = describe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动主题图片
	 */
	@Column(name ="IMG",nullable=true,length=32)
	public java.lang.String getImg(){
		return this.img;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动主题图片
	 */
	public void setImg(java.lang.String img){
		this.img = img;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  一次购买多个
	 */
	@Column(name ="ALLOW_BUY_MANY",nullable=true,length=32)
	public java.lang.String getAllowBuyMany(){
		return this.allowBuyMany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  一次购买多个
	 */
	public void setAllowBuyMany(java.lang.String allowBuyMany){
		this.allowBuyMany = allowBuyMany;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否生成兑换码
	 */
	@Column(name ="GENERATE_EXCHANGE_CODE",nullable=true,length=32)
	public java.lang.String getGenerateExchangeCode(){
		return this.generateExchangeCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否生成兑换码
	 */
	public void setGenerateExchangeCode(java.lang.String generateExchangeCode){
		this.generateExchangeCode = generateExchangeCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  兑换码长度
	 */
	@Column(name ="EXCHANGE_CODE_LENGTH",nullable=true,length=50)
	public java.lang.Integer getExchangeCodeLength(){
		return this.exchangeCodeLength;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  兑换码长度
	 */
	public void setExchangeCodeLength(java.lang.Integer exchangeCodeLength){
		this.exchangeCodeLength = exchangeCodeLength;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效期
	 */
	@Column(name ="VALIDITY",nullable=true,length=32)
	public java.lang.Integer getValidity(){
		return this.validity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  有效期
	 */
	public void setValidity(java.lang.Integer validity){
		this.validity = validity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  兑换码图片
	 */
	@Column(name ="EXCHANGE_CODE_IMG",nullable=true,length=32)
	public java.lang.String getExchangeCodeImg(){
		return this.exchangeCodeImg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  兑换码图片
	 */
	public void setExchangeCodeImg(java.lang.String exchangeCodeImg){
		this.exchangeCodeImg = exchangeCodeImg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  周期
	 */
	@Column(name ="VALIDITY_UNIT",nullable=true,length=32)
	public java.lang.String getValidityUnit(){
		return this.validityUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  周期
	 */
	public void setValidityUnit(java.lang.String validityUnit){
		this.validityUnit = validityUnit;
	}
	
	
	@Column(name="state",nullable=true,length=32)
	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	@Column(name="category",nullable=true,length=20)
	public java.lang.String getCategory() {
		return category;
	}

	public void setCategory(java.lang.String category) {
		this.category = category;
	}
	@Column(name="rule",nullable=true,length=2000)
	public java.lang.String getRule() {
		return rule;
	}

	public void setRule(java.lang.String rule) {
		this.rule = rule;
	}
	@Column(name="is_bind_phone")
	public java.lang.String getIsBindPhone() {
		return isBindPhone;
	}

	public void setIsBindPhone(java.lang.String isBindPhone) {
		this.isBindPhone = isBindPhone;
	}
	
	
	@Column(name="Carousel")
	public java.lang.String getCarousel() {
		return carousel;
	}

	public void setCarousel(java.lang.String carousel) {
		this.carousel = carousel;
	}
	
	@Column(name="message_id")
	public java.lang.String getMessageId() {
		return messageId;
	}

	public void setMessageId(java.lang.String messageId) {
		this.messageId = messageId;
	}
	
	@Column(name="buy_limit")
	public java.lang.Integer getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(java.lang.Integer buyLimit) {
		this.buyLimit = buyLimit;
	}
	
	@Column(name="have_address")
	public java.lang.String getHaveAddress() {
		return haveAddress;
	}

	public void setHaveAddress(java.lang.String haveAddress) {
		this.haveAddress = haveAddress;
	}
	
	
	
	@Column(name="token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	@Column(name="tokenFlag")
	public String getTokenFlag() {
		return tokenFlag;
	}

	public void setTokenFlag(String tokenFlag) {
		this.tokenFlag = tokenFlag;
	}
	@Column(name="userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="OTHER_PROGRAM_ID")
	public CdMiniProgramEntity getOtherProgram() {
		return otherProgram;
	}

	public void setOtherProgram(CdMiniProgramEntity otherProgram) {
		this.otherProgram = otherProgram;
	}
}
