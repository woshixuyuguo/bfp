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
 * @Description: 商户清算流水
 * @author onlineGenerator
 * @date 2018-07-05 09:47:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rpp_merc_pssfil", schema = "")
@SuppressWarnings("serial")
public class RppMercPssfilEntity implements java.io.Serializable {
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
	/**清算金额*/
	@Excel(name="清算金额",width=15)
	private java.lang.Double money;
	/**手续费*/
	@Excel(name="手续费",width=15)
	private java.lang.Double fee;
	/**到账金额*/
	@Excel(name="到账金额",width=15)
	private java.lang.Double moneyAct;
	/**收款人*/
	@Excel(name="收款人",width=15)
	private java.lang.String name;
	/**银行卡号*/
	@Excel(name="银行卡号",width=15)
	private java.lang.String bankCard;
	/**银行名称*/
	@Excel(name="银行名称",width=15)
	private java.lang.String bankName;
	/**联行号*/
	@Excel(name="联行号",width=15)
	private java.lang.String bankCode;
	/**分行名称*/
	@Excel(name="分行名称",width=15)
	private java.lang.String branchBank;
	/**电话*/
	@Excel(name="电话",width=15)
	private java.lang.String phone;
	/**清算结果*/
	@Excel(name="清算结果",width=15,dicCode="pssfilState")
	private java.lang.String state;
	/**结果说明*/
	@Excel(name="结果说明",width=15)
	private java.lang.String remark;
	
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  清算金额
	 */

	@Column(name ="MONEY",nullable=true,scale=2,length=32)
	public java.lang.Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  清算金额
	 */
	public void setMoney(java.lang.Double money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  手续费
	 */

	@Column(name ="FEE",nullable=true,scale=2,length=32)
	public java.lang.Double getFee(){
		return this.fee;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  手续费
	 */
	public void setFee(java.lang.Double fee){
		this.fee = fee;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  到账金额
	 */

	@Column(name ="MONEY_ACT",nullable=true,scale=2,length=32)
	public java.lang.Double getMoneyAct(){
		return this.moneyAct;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  到账金额
	 */
	public void setMoneyAct(java.lang.Double moneyAct){
		this.moneyAct = moneyAct;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款人
	 */

	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款人
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行卡号
	 */

	@Column(name ="BANK_CARD",nullable=true,length=32)
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
	 *@return: java.lang.String  银行名称
	 */

	@Column(name ="BANK_NAME",nullable=true,length=32)
	public java.lang.String getBankName(){
		return this.bankName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行名称
	 */
	public void setBankName(java.lang.String bankName){
		this.bankName = bankName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联行号
	 */

	@Column(name ="BANK_CODE",nullable=true,length=32)
	public java.lang.String getBankCode(){
		return this.bankCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联行号
	 */
	public void setBankCode(java.lang.String bankCode){
		this.bankCode = bankCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分行名称
	 */

	@Column(name ="BRANCH_BANK",nullable=true,length=32)
	public java.lang.String getBranchBank(){
		return this.branchBank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分行名称
	 */
	public void setBranchBank(java.lang.String branchBank){
		this.branchBank = branchBank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */

	@Column(name ="PHONE",nullable=true,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  清算结果
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  清算结果
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结果说明
	 */

	@Column(name ="REMARK",nullable=true,length=100)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结果说明
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
}
