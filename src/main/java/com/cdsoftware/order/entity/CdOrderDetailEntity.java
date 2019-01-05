package com.cdsoftware.order.entity;

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

import com.cdsoftware.member.entity.CdMemberEntity;
import com.cdsoftware.program.entity.CdMiniProgramEntity;

/**   
 * @Title: Entity
 * @Description: 订单明细
 * @author onlineGenerator
 * @date 2018-03-27 13:51:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cd_order_detail", schema = "")
@SuppressWarnings("serial")
public class CdOrderDetailEntity implements java.io.Serializable {
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
	/**订单*/
	@Excel(name="订单")
	private CdOrderEntity order;
	/**兑换码*/
	@Excel(name="兑换码")
	private java.lang.String exchangeCode;
	/**条形码*/
	@Excel(name="条形码")
	private java.lang.String barCode;
	/**二维码*/
	@Excel(name="二维码")
	private java.lang.String qrCode;
	/**购买人*/
	@Excel(name="购买人")
	private CdMemberEntity memb;
	/**有效期*/
	@Excel(name="有效期")
	private java.util.Date validity;
	/**状态*/
	@Excel(name="状态")
	private java.lang.Integer state;
	/**小程序*/
	@Excel(name="小程序")
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
	 *@return: java.lang.String  订单
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name ="ORDER_ID",nullable=true)
	public CdOrderEntity getOrder(){
		return this.order;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单
	 */
	public void setOrder(CdOrderEntity order){
		this.order = order;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  兑换码
	 */
	@Column(name ="EXCHANGE_CODE",nullable=true,length=32)
	public java.lang.String getExchangeCode(){
		return this.exchangeCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  兑换码
	 */
	public void setExchangeCode(java.lang.String exchangeCode){
		this.exchangeCode = exchangeCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  条形码
	 */
	@Column(name ="BAR_CODE",nullable=true,length=100)
	public java.lang.String getBarCode(){
		return this.barCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  条形码
	 */
	public void setBarCode(java.lang.String barCode){
		this.barCode = barCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二维码
	 */
	@Column(name ="QR_CODE",nullable=true,length=100)
	public java.lang.String getQrCode(){
		return this.qrCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码
	 */
	public void setQrCode(java.lang.String qrCode){
		this.qrCode = qrCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  购买人
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name ="MEMB_ID",nullable=true)
	public CdMemberEntity getMemb(){
		return this.memb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购买人
	 */
	public void setMemb(CdMemberEntity memb){
		this.memb = memb;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效期
	 */
	@Column(name ="VALIDITY",nullable=true,length=32)
	public java.util.Date getValidity(){
		return this.validity;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效期
	 */
	public void setValidity(java.util.Date validity){
		this.validity = validity;
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
	 *@return: java.lang.String  小程序
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	
	@JoinColumn(name ="PROGRAM_ID",nullable=true)
	public CdMiniProgramEntity getProgram(){
		return this.program;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小程序
	 */
	public void setProgram(CdMiniProgramEntity program){
		this.program = program;
	}
}
