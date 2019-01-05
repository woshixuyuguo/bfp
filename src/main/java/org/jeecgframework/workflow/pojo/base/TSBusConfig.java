/*    */ package org.jeecgframework.workflow.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_busconfig")
/*    */ public class TSBusConfig extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String busname;
/* 20 */   private TSTable TSTable = new TSTable();
/* 21 */   private TPProcess TPProcess = new TPProcess();
/*    */ 
/* 24 */   @Column(name="busname", length=30)
/*    */   public String getBusname() { return this.busname; }
/*    */ 
/*    */   public void setBusname(String busname)
/*    */   {
/* 28 */     this.busname = busname; } 
/* 33 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="tableid")
/*    */   public TSTable getTSTable() { return this.TSTable; }
/*    */ 
/*    */   public void setTSTable(TSTable tSTable)
/*    */   {
/* 37 */     this.TSTable = tSTable;
/*    */   }
/* 43 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="processid")
/*    */   public TPProcess getTPProcess() { return this.TPProcess; }
/*    */ 
/*    */   public void setTPProcess(TPProcess tPProcess)
/*    */   {
/* 47 */     this.TPProcess = tPProcess;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TSBusConfig
 * JD-Core Version:    0.6.0
 */