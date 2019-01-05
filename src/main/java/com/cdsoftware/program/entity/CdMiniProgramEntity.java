package com.cdsoftware.program.entity;

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
 * @Description: 小程序管理
 * @author onlineGenerator
 * @date 2018-03-27 13:47:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cd_mini_program")
public class CdMiniProgramEntity implements java.io.Serializable {
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
	/**小程序名称*/
	@Excel(name="小程序名称")
	private java.lang.String name;
	/**appId*/
	@Excel(name="appId")
	private java.lang.String appid;
	/**appSecret*/
	@Excel(name="appSecret")
	private java.lang.String appSecret;
	/**商户号*/
	@Excel(name="商户号")
	private java.lang.String mchId;
	/**支付key*/
	@Excel(name="支付key")
	private java.lang.String mchKey;
	/**超级管理员账号*/
	@Excel(name="超级管理员账号")
	private java.lang.String superManageAcount;
	/**首页路径*/
	@Excel(name="首页路径")
	private java.lang.String path;
	/**所属公众账号*/
	@Excel(name="所属公众账号")
	private java.lang.String officialAccount;
	
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
	 *@return: java.lang.String  小程序名称
	 */
	@Column(name ="NAME",nullable=false,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小程序名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  appId
	 */
	@Column(name ="APPID",nullable=false,length=32)
	public java.lang.String getAppid(){
		return this.appid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  appId
	 */
	public void setAppid(java.lang.String appid){
		this.appid = appid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  appSecret
	 */
	@Column(name ="APP_SECRET",nullable=false,length=32)
	public java.lang.String getAppSecret(){
		return this.appSecret;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  appSecret
	 */
	public void setAppSecret(java.lang.String appSecret){
		this.appSecret = appSecret;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户号
	 */
	@Column(name ="MCH_ID",nullable=false,length=32)
	public java.lang.String getMchId(){
		return this.mchId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户号
	 */
	public void setMchId(java.lang.String mchId){
		this.mchId = mchId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付key
	 */
	@Column(name ="MCH_KEY",nullable=false,length=32)
	public java.lang.String getMchKey(){
		return this.mchKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付key
	 */
	public void setMchKey(java.lang.String mchKey){
		this.mchKey = mchKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  超级管理员账号
	 */
	@Column(name ="SUPER_MANAGE_ACOUNT",nullable=false,length=50)
	public java.lang.String getSuperManageAcount(){
		return this.superManageAcount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  超级管理员账号
	 */
	public void setSuperManageAcount(java.lang.String superManageAcount){
		this.superManageAcount = superManageAcount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  首页路径
	 */
	@Column(name ="PATH",nullable=false,length=100)
	public java.lang.String getPath(){
		return this.path;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  首页路径
	 */
	public void setPath(java.lang.String path){
		this.path = path;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公众账号
	 */
	@Column(name ="OFFICIAL_ACCOUNT",nullable=false,length=50)
	public java.lang.String getOfficialAccount(){
		return this.officialAccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公众账号
	 */
	public void setOfficialAccount(java.lang.String officialAccount){
		this.officialAccount = officialAccount;
	}
}
