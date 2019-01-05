package com.rpp.trade.entity;

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
 * @Description: 交易
 * @author onlineGenerator
 * @date 2018-07-02 16:50:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "rpp_transaction", schema = "")
@SuppressWarnings("serial")
public class RppTransactionEntity implements java.io.Serializable {
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
	/**机构商户*/
	@Excel(name="机构商户",width=15)
	private java.lang.String agentCode;
	/**商户*/
	@Excel(name="商户",width=15)
	private java.lang.String mercCode;
	/**通道*/
	@Excel(name="通道",width=15)
	private java.lang.String orgCode;
	/**费率代码*/
	@Excel(name="费率代码",width=15)
	private java.lang.String rateCode;
	/**交易费率*/
	@Excel(name="交易费率",width=15)
	private java.lang.Double rate;
	/**通道费率*/
	@Excel(name="通道费率",width=15)
	private java.lang.Double orgRate;
	/**交易金额*/
	@Excel(name="交易金额",width=15)
	private java.lang.Double money;
	/**手续费*/
	@Excel(name="手续费",width=15)
	private java.lang.Double moneyFee;
	/**入账金额*/
	@Excel(name="入账金额",width=15)
	private java.lang.Double moneyAct;
	/**交易状态*/
	@Excel(name="交易状态",width=15)
	private java.lang.String state;
	/**入账状态*/
	@Excel(name="入账状态",width=15)
	private java.lang.String enter;
	/**下游订单号*/
	@Excel(name="下游订单号",width=15)
	private java.lang.String outOrderNo;
	/**我方订单号*/
	@Excel(name="我方订单号",width=15)
	private java.lang.String orderNo;
	/**上游订单号*/
	@Excel(name="上游订单号",width=15)
	private java.lang.String orderId;
	/**异步通知地址*/
	@Excel(name="异步通知地址",width=15)
	private java.lang.String notifyUrl;
	/**同步跳转页面*/
	@Excel(name="同步跳转页面",width=15)
	private java.lang.String sysnNotifyUrl;
	/**通知结果*/
	@Excel(name="通知结果",width=15)
	private java.lang.String notifyFlag;
	/**通知次数*/
	@Excel(name="通知次数",width=15)
	private java.lang.String notifyTime;
	
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
	 *@return: java.lang.String  机构商户
	 */

	@Column(name ="AGENT_CODE",nullable=true,length=32)
	public java.lang.String getAgentCode(){
		return this.agentCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机构商户
	 */
	public void setAgentCode(java.lang.String agentCode){
		this.agentCode = agentCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户
	 */

	@Column(name ="MERC_CODE",nullable=true,length=32)
	public java.lang.String getMercCode(){
		return this.mercCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户
	 */
	public void setMercCode(java.lang.String mercCode){
		this.mercCode = mercCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通道
	 */

	@Column(name ="ORG_CODE",nullable=true,length=32)
	public java.lang.String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通道
	 */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费率代码
	 */

	@Column(name ="RATE_CODE",nullable=true,length=32)
	public java.lang.String getRateCode(){
		return this.rateCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费率代码
	 */
	public void setRateCode(java.lang.String rateCode){
		this.rateCode = rateCode;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  交易费率
	 */

	@Column(name ="RATE",nullable=true,scale=4,length=32)
	public java.lang.Double getRate(){
		return this.rate;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  交易费率
	 */
	public void setRate(java.lang.Double rate){
		this.rate = rate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  通道费率
	 */

	@Column(name ="ORG_RATE",nullable=true,scale=4,length=32)
	public java.lang.Double getOrgRate(){
		return this.orgRate;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  通道费率
	 */
	public void setOrgRate(java.lang.Double orgRate){
		this.orgRate = orgRate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  交易金额
	 */

	@Column(name ="MONEY",nullable=true,scale=2,length=32)
	public java.lang.Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  交易金额
	 */
	public void setMoney(java.lang.Double money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  手续费
	 */

	@Column(name ="MONEY_FEE",nullable=true,scale=2,length=32)
	public java.lang.Double getMoneyFee(){
		return this.moneyFee;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  手续费
	 */
	public void setMoneyFee(java.lang.Double moneyFee){
		this.moneyFee = moneyFee;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  入账金额
	 */

	@Column(name ="MONEY_ACT",nullable=true,scale=2,length=32)
	public java.lang.Double getMoneyAct(){
		return this.moneyAct;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  入账金额
	 */
	public void setMoneyAct(java.lang.Double moneyAct){
		this.moneyAct = moneyAct;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易状态
	 */

	@Column(name ="STATE",nullable=true,length=32)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入账状态
	 */

	@Column(name ="ENTER",nullable=true,length=32)
	public java.lang.String getEnter(){
		return this.enter;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入账状态
	 */
	public void setEnter(java.lang.String enter){
		this.enter = enter;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下游订单号
	 */

	@Column(name ="OUT_ORDER_NO",nullable=true,length=50)
	public java.lang.String getOutOrderNo(){
		return this.outOrderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下游订单号
	 */
	public void setOutOrderNo(java.lang.String outOrderNo){
		this.outOrderNo = outOrderNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  我方订单号
	 */

	@Column(name ="ORDER_NO",nullable=true,length=50)
	public java.lang.String getOrderNo(){
		return this.orderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  我方订单号
	 */
	public void setOrderNo(java.lang.String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上游订单号
	 */

	@Column(name ="ORDER_ID",nullable=true,length=50)
	public java.lang.String getOrderId(){
		return this.orderId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上游订单号
	 */
	public void setOrderId(java.lang.String orderId){
		this.orderId = orderId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  异步通知地址
	 */

	@Column(name ="NOTIFY_URL",nullable=true,length=200)
	public java.lang.String getNotifyUrl(){
		return this.notifyUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  异步通知地址
	 */
	public void setNotifyUrl(java.lang.String notifyUrl){
		this.notifyUrl = notifyUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  同步跳转页面
	 */

	@Column(name ="SYSN_NOTIFY_URL",nullable=true,length=200)
	public java.lang.String getSysnNotifyUrl(){
		return this.sysnNotifyUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  同步跳转页面
	 */
	public void setSysnNotifyUrl(java.lang.String sysnNotifyUrl){
		this.sysnNotifyUrl = sysnNotifyUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通知结果
	 */

	@Column(name ="NOTIFY_FLAG",nullable=true,length=32)
	public java.lang.String getNotifyFlag(){
		return this.notifyFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通知结果
	 */
	public void setNotifyFlag(java.lang.String notifyFlag){
		this.notifyFlag = notifyFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通知次数
	 */

	@Column(name ="NOTIFY_TIME",nullable=true,length=32)
	public java.lang.String getNotifyTime(){
		return this.notifyTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通知次数
	 */
	public void setNotifyTime(java.lang.String notifyTime){
		this.notifyTime = notifyTime;
	}
}
