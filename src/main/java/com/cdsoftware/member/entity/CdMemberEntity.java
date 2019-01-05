package com.cdsoftware.member.entity;

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
 * @Description: 会员管理
 * @author onlineGenerator
 * @date 2018-03-27 13:47:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cd_member", schema = "")
@SuppressWarnings("serial")
public class CdMemberEntity implements java.io.Serializable {
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
	/**昵称*/
	@Excel(name="昵称")
	private java.lang.String petName;
	/**标志码*/
	@Excel(name="标志码")
	private java.lang.String openId;
	/**头像*/
	@Excel(name="头像")
	private java.lang.String imgUrl;
	/**状态*/
	@Excel(name="状态")
	private java.lang.Integer state;
	/**sessionKey*/
	@Excel(name="sessionKey")
	private java.lang.String sessionKey;
	/**会话*/
	private java.lang.String sessionId;
	/**手机号*/
	@Excel(name="手机号")
	private java.lang.String phone;
	/**账户余额*/
	private java.math.BigDecimal money;
	/**经度*/
	@Excel(name="经度")
	private java.lang.Double lng;
	/**纬度*/
	@Excel(name="纬度")
	private java.lang.Double lat;
	/**归属小程序*/
	@Excel(name="归属小程序")
	private CdMiniProgramEntity program;
	
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
	 *@return: java.lang.String  昵称
	 */
	@Column(name ="PET_NAME",nullable=true,length=50)
	public java.lang.String getPetName(){
		return this.petName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  昵称
	 */
	public void setPetName(java.lang.String petName){
		this.petName = petName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标志码
	 */
	@Column(name ="OPEN_ID",nullable=true,length=32)
	public java.lang.String getOpenId(){
		return this.openId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标志码
	 */
	public void setOpenId(java.lang.String openId){
		this.openId = openId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="IMG_URL",nullable=true,length=100)
	public java.lang.String getImgUrl(){
		return this.imgUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setImgUrl(java.lang.String imgUrl){
		this.imgUrl = imgUrl;
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
	 *@return: java.lang.String  sessionKey
	 */
	@Column(name ="SESSION_KEY",nullable=true,length=32)
	public java.lang.String getSessionKey(){
		return this.sessionKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  sessionKey
	 */
	public void setSessionKey(java.lang.String sessionKey){
		this.sessionKey = sessionKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会话
	 */
	@Column(name ="SESSION_ID",nullable=true,length=32)
	public java.lang.String getSessionId(){
		return this.sessionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会话
	 */
	public void setSessionId(java.lang.String sessionId){
		this.sessionId = sessionId;
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
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  账户余额
	 */
	@Column(name ="MONEY",nullable=true,scale=2,length=32)
	public java.math.BigDecimal getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  账户余额
	 */
	public void setMoney(java.math.BigDecimal money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  经度
	 */
	@Column(name ="LNG",nullable=true,length=32)
	public java.lang.Double getLng(){
		return this.lng;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  经度
	 */
	public void setLng(java.lang.Double lng){
		this.lng = lng;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  纬度
	 */
	@Column(name ="LAT",nullable=true,length=32)
	public java.lang.Double getLat(){
		return this.lat;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  纬度
	 */
	public void setLat(java.lang.Double lat){
		this.lat = lat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  归属小程序
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROGRAM_ID")
	//@Column(name ="PROGRAM_ID",nullable=true,length=32)
	public CdMiniProgramEntity getProgram(){
		return this.program;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  归属小程序
	 */
	public void setProgram(CdMiniProgramEntity program){
		this.program = program;
	}
}
