/*    */ package org.jeecgframework.workflow.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_prjstatus")
/*    */ public class TSPrjstatus extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String status;
/*    */   private String description;
/*    */   private String code;
/*    */   private String name;
/*    */   private String executerole;
/*    */   private String nextsteprole;
/*    */ 
/*    */   @Column(name="executerole", length=100)
/*    */   public String getExecuterole()
/*    */   {
/* 26 */     return this.executerole;
/*    */   }
/*    */ 
/*    */   public void setExecuterole(String executerole) {
/* 30 */     this.executerole = executerole;
/*    */   }
/*    */   @Column(name="nextsteprole", length=100)
/*    */   public String getNextsteprole() {
/* 35 */     return this.nextsteprole;
/*    */   }
/*    */ 
/*    */   public void setNextsteprole(String nextsteprole) {
/* 39 */     this.nextsteprole = nextsteprole;
/*    */   }
/*    */   @Column(name="status", length=100)
/*    */   public String getStatus() {
/* 44 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(String status) {
/* 48 */     this.status = status;
/*    */   }
/*    */   @Column(name="description", length=300)
/*    */   public String getDescription() {
/* 53 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description) {
/* 57 */     this.description = description;
/*    */   }
/*    */   @Column(name="code", length=50)
/*    */   public String getCode() {
/* 62 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 66 */     this.code = code;
/*    */   }
/*    */   @Column(name="name", length=50)
/*    */   public String getName() {
/* 71 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 75 */     this.name = name;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TSPrjstatus
 * JD-Core Version:    0.6.0
 */