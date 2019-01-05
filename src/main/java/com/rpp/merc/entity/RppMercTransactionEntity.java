package com.rpp.merc.entity;

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
 * @Description: 商户资金流水表
 * @author onlineGenerator
 * @date 2018-07-05 08:56:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rpp_merc_transaction", schema = "")
@SuppressWarnings("serial")
public class RppMercTransactionEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**商户id*/
	private java.lang.String mercId;
	/**商户号*/
	@Excel(name="商户号",width=15)
	private java.lang.String mercCode;
	/**变动类型*/
	@Excel(name="变动类型",width=15,dicCode="accountChangeType")
	private java.lang.String type;
	/**变动金额*/
	@Excel(name="变动金额",width=15)
	private java.lang.Double money;
	/**变动前金额*/
	@Excel(name="变动前金额",width=15)
	private java.lang.Double beforMoney;
	/**变动后金额*/
	@Excel(name="变动后金额",width=15)
	private java.lang.Double afterMoney;
	/**资金来源*/
	@Excel(name="资金来源",width=15,dicCode="accountChangeSource")
	private java.lang.String source;
	/**数据源*/
	private java.lang.String dataId;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
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
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户id
	 */

	@Column(name ="MERC_ID",nullable=true,length=32)
	public java.lang.String getMercId(){
		return this.mercId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户id
	 */
	public void setMercId(java.lang.String mercId){
		this.mercId = mercId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户号
	 */

	@Column(name ="MERC_CODE",nullable=true,length=32)
	public java.lang.String getMercCode(){
		return this.mercCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户号
	 */
	public void setMercCode(java.lang.String mercCode){
		this.mercCode = mercCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  变动类型
	 */

	@Column(name ="TYPE",nullable=true,length=32)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  变动类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  变动金额
	 */

	@Column(name ="MONEY",nullable=true,scale=2,length=32)
	public java.lang.Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  变动金额
	 */
	public void setMoney(java.lang.Double money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  变动前金额
	 */

	@Column(name ="BEFOR_MONEY",nullable=true,scale=2,length=32)
	public java.lang.Double getBeforMoney(){
		return this.beforMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  变动前金额
	 */
	public void setBeforMoney(java.lang.Double beforMoney){
		this.beforMoney = beforMoney;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  变动后金额
	 */

	@Column(name ="AFTER_MONEY",nullable=true,scale=2,length=32)
	public java.lang.Double getAfterMoney(){
		return this.afterMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  变动后金额
	 */
	public void setAfterMoney(java.lang.Double afterMoney){
		this.afterMoney = afterMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资金来源
	 */

	@Column(name ="SOURCE",nullable=true,length=32)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资金来源
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数据源
	 */

	@Column(name ="DATA_ID",nullable=true,length=32)
	public java.lang.String getDataId(){
		return this.dataId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数据源
	 */
	public void setDataId(java.lang.String dataId){
		this.dataId = dataId;
	}
}
