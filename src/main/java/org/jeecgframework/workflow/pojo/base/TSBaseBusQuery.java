/*    */ package org.jeecgframework.workflow.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Temporal;
/*    */ import javax.persistence.TemporalType;
/*    */ import javax.persistence.Transient;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ import org.jeecgframework.web.system.pojo.base.TSType;
/*    */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*    */ import org.jeecgframework.workflow.model.activiti.Process;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_basebus")
/*    */ public class TSBaseBusQuery extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private TSUser TSUser;
/*    */   private TSType TSType;
/*    */   private Date createtime;
/*    */   private TSPrjstatus TSPrjstatus;
/* 34 */   private Process Process = new Process();
/* 35 */   private TSBusConfig TSBusConfig = new TSBusConfig();
/*    */ 
/* 40 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="userid")
/*    */   public TSUser getTSUser() { return this.TSUser; }
/*    */ 
/*    */   public void setTSUser(TSUser tSUser)
/*    */   {
/* 44 */     this.TSUser = tSUser;
/*    */   }
/* 50 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="typeid")
/*    */   public TSType getTSType() { return this.TSType; }
/*    */ 
/*    */   public void setTSType(TSType tSType)
/*    */   {
/* 54 */     this.TSType = tSType;
/*    */   }
/* 60 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="prjstateid")
/*    */   public TSPrjstatus getTSPrjstatus() { return this.TSPrjstatus; }
/*    */ 
/*    */   public void setTSPrjstatus(TSPrjstatus tSPrjstatus)
/*    */   {
/* 64 */     this.TSPrjstatus = tSPrjstatus; } 
/* 69 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="busconfigid")
/*    */   public TSBusConfig getTSBusConfig() { return this.TSBusConfig; }
/*    */ 
/*    */   public void setTSBusConfig(TSBusConfig TSBusConfig)
/*    */   {
/* 73 */     this.TSBusConfig = TSBusConfig;
/*    */   }
/* 79 */   @Temporal(TemporalType.DATE)
/*    */   @Column(name="createtime", length=13)
/*    */   public Date getCreatetime() { return this.createtime; }
/*    */ 
/*    */   public void setCreatetime(Date createtime)
/*    */   {
/* 83 */     this.createtime = createtime;
/*    */   }
/* 87 */   @Transient
/*    */   public Process getProcess() { return this.Process; }
/*    */ 
/*    */   public void setProcess(Process Process) {
/* 90 */     this.Process = Process;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TSBaseBusQuery
 * JD-Core Version:    0.6.0
 */