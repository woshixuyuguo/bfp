package com.cdsoftware.group.entity;

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
 * @Description: 转发记录
 * @author onlineGenerator
 * @date 2018-03-29 13:34:56
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cd_member_group", schema = "")
@SuppressWarnings("serial")
public class CdMemberGroupEntity implements java.io.Serializable {
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
	private java.lang.String memberId;
	/**活动*/
	@Excel(name="活动")
	private java.lang.String activityId;
	/**群Id*/
	@Excel(name="群Id")
	private java.lang.String groupId;
	
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
	@Column(name ="MEMBER_ID",nullable=true,length=32)
	public java.lang.String getMemberId(){
		return this.memberId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员
	 */
	public void setMemberId(java.lang.String memberId){
		this.memberId = memberId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动
	 */
	@Column(name ="ACTIVITY_ID",nullable=true,length=32)
	public java.lang.String getActivityId(){
		return this.activityId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动
	 */
	public void setActivityId(java.lang.String activityId){
		this.activityId = activityId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  群Id
	 */
	@Column(name ="GROUP_ID",nullable=true,length=32)
	public java.lang.String getGroupId(){
		return this.groupId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  群Id
	 */
	public void setGroupId(java.lang.String groupId){
		this.groupId = groupId;
	}
}
