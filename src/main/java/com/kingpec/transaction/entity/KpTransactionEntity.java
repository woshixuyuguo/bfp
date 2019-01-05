package com.kingpec.transaction.entity;

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
 * @Description: 交易流水
 * @author onlineGenerator
 * @date 2018-06-25 11:43:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "kp_transaction", schema = "")
@SuppressWarnings("serial")
public class KpTransactionEntity implements java.io.Serializable {
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
	/**站点名称*/
	private java.lang.String siteName;
	/**商户号*/
	private java.lang.String mercNo;
	
	/**交易时间*/
	private java.lang.String tranTime;
	/**交易码*/
	private java.lang.String tranCode;
	
	/**交易状态*/
	private java.lang.String tranState;
	/**结算日期*/
	@Excel(name="结算日期",width=15,format="yyyyMMdd")
	private java.util.Date settleDate;
	/**交易类型*/
	@Excel(name="交易类型",width=15)
	private java.lang.String tranType;
	/**交易日期*/
	@Excel(name="交易日期",width=15)
	private java.lang.String tranDate;
	/**交易时间*/
	@Excel(name="交易时间",width=15)
	private java.lang.String transTime;
	/**终端号*/
	@Excel(name="终端号",width=15)
	private java.lang.String termNo;
	/**交易卡号*/
	@Excel(name="卡号",width=15)
	private java.lang.String cardNo;
	/**总店号*/
	@Excel(name="总店号",width=15)
	private java.lang.String storeNo;
	/**分店号*/
	@Excel(name="分店名称",width=15)
	private java.lang.String branchName;
	/**手续费*/
	@Excel(name="手续费",width=15)
	private java.lang.Double tranFee;
	/**入账金额*/
	@Excel(name="入账金额（元）",width=15)
	private java.lang.Double tranAct;
	
	/**交易金额*/
	@Excel(name="交易金额(元)",width=15)
	private java.lang.Double tranMoney;
	
	/**
	 * 分公司
	 */
	private String   branchCompany;
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
	 *@return: java.lang.String  站点名称
	 */

	@Column(name ="SITE_NAME",nullable=true,length=32)
	public java.lang.String getSiteName(){
		return this.siteName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  站点名称
	 */
	public void setSiteName(java.lang.String siteName){
		this.siteName = siteName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户号
	 */

	@Column(name ="MERC_NO",nullable=true,length=32)
	public java.lang.String getMercNo(){
		return this.mercNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户号
	 */
	public void setMercNo(java.lang.String mercNo){
		this.mercNo = mercNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端号
	 */

	@Column(name ="TERM_NO",nullable=true,length=32)
	public java.lang.String getTermNo(){
		return this.termNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端号
	 */
	public void setTermNo(java.lang.String termNo){
		this.termNo = termNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易时间
	 */

	@Column(name ="TRAN_TIME",nullable=true,length=32)
	public java.lang.String getTranTime(){
		return this.tranTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易时间
	 */
	public void setTranTime(java.lang.String tranTime){
		this.tranTime = tranTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易码
	 */

	@Column(name ="TRAN_CODE",nullable=true,length=32)
	public java.lang.String getTranCode(){
		return this.tranCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易码
	 */
	public void setTranCode(java.lang.String tranCode){
		this.tranCode = tranCode;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  交易金额
	 */

	@Column(name ="TRAN_MONEY",nullable=true,scale=2,length=32)
	public java.lang.Double getTranMoney(){
		return this.tranMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  交易金额
	 */
	public void setTranMoney(java.lang.Double tranMoney){
		this.tranMoney = tranMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易状态
	 */

	@Column(name ="TRAN_STATE",nullable=true,length=32)
	public java.lang.String getTranState(){
		return this.tranState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易状态
	 */
	public void setTranState(java.lang.String tranState){
		this.tranState = tranState;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结算日期
	 */

	@Column(name ="SETTLE_DATE",nullable=true,length=32)
	public java.util.Date getSettleDate(){
		return this.settleDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结算日期
	 */
	public void setSettleDate(java.util.Date settleDate){
		this.settleDate = settleDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易类型
	 */

	@Column(name ="TRAN_TYPE",nullable=true,length=32)
	public java.lang.String getTranType(){
		return this.tranType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易类型
	 */
	public void setTranType(java.lang.String tranType){
		this.tranType = tranType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易日期
	 */

	@Column(name ="TRAN_DATE",nullable=true,length=32)
	public java.lang.String getTranDate(){
		return this.tranDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易日期
	 */
	public void setTranDate(java.lang.String tranDate){
		this.tranDate = tranDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易时间
	 */

	@Column(name ="TRANS_TIME",nullable=true,length=32)
	public java.lang.String getTransTime(){
		return this.transTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易时间
	 */
	public void setTransTime(java.lang.String transTime){
		this.transTime = transTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易卡号
	 */

	@Column(name ="CARD_NO",nullable=true,length=32)
	public java.lang.String getCardNo(){
		return this.cardNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易卡号
	 */
	public void setCardNo(java.lang.String cardNo){
		this.cardNo = cardNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  总店号
	 */

	@Column(name ="STORE_NO",nullable=true,length=32)
	public java.lang.String getStoreNo(){
		return this.storeNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  总店号
	 */
	public void setStoreNo(java.lang.String storeNo){
		this.storeNo = storeNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分店号
	 */

	@Column(name ="BRANCH_NAME",nullable=true,length=32)
	public java.lang.String getBranchName(){
		return this.branchName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分店号
	 */
	public void setBranchName(java.lang.String branchName){
		this.branchName = branchName;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  手续费
	 */

	@Column(name ="TRAN_FEE",nullable=true,scale=2,length=32)
	public java.lang.Double getTranFee(){
		return this.tranFee;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  手续费
	 */
	public void setTranFee(java.lang.Double tranFee){
		this.tranFee = tranFee;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  入账金额
	 */

	@Column(name ="TRAN_ACT",nullable=true,scale=2,length=32)
	public java.lang.Double getTranAct(){
		return this.tranAct;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  入账金额
	 */
	public void setTranAct(java.lang.Double tranAct){
		this.tranAct = tranAct;
	}

	@Column(name ="branch_company",nullable=true,scale=2,length=32)
	public String getBranchCompany() {
		return branchCompany;
	}

	public void setBranchCompany(String branchCompany) {
		this.branchCompany = branchCompany;
	}
	
	
	
}
