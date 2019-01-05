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
/*    */ import org.jeecgframework.web.system.pojo.base.TSType;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_table")
/*    */ public class TSTable extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String tableTitle;
/*    */   private String entityName;
/*    */   private String tableName;
/*    */   private String entityKey;
/*    */   private TSType TSType;
/*    */ 
/*    */   @Column(name="tabletitle", length=20)
/*    */   public String getTableTitle()
/*    */   {
/* 27 */     return this.tableTitle;
/*    */   }
/*    */ 
/*    */   public void setTableTitle(String tableTitle) {
/* 31 */     this.tableTitle = tableTitle;
/*    */   }
/*    */   @Column(name="entityname", length=200)
/*    */   public String getEntityName() {
/* 36 */     return this.entityName;
/*    */   }
/*    */ 
/*    */   public void setEntityName(String entityName) {
/* 40 */     this.entityName = entityName;
/*    */   }
/*    */   @Column(name="tablename", length=30)
/*    */   public String getTableName() {
/* 45 */     return this.tableName;
/*    */   }
/*    */ 
/*    */   public void setTableName(String tableName) {
/* 49 */     this.tableName = tableName;
/*    */   }
/*    */   @Column(name="entitykey", length=50)
/*    */   public String getEntityKey() {
/* 54 */     return this.entityKey;
/*    */   }
/*    */ 
/*    */   public void setEntityKey(String entityKey) {
/* 58 */     this.entityKey = entityKey; } 
/* 63 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="typeid")
/*    */   public TSType getTSType() { return this.TSType; }
/*    */ 
/*    */   public void setTSType(TSType TSType)
/*    */   {
/* 67 */     this.TSType = TSType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TSTable
 * JD-Core Version:    0.6.0
 */