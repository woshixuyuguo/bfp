/*    */ package org.jeecgframework.workflow.model.activiti;
/*    */ 
/*    */ public class ActivitiCom
/*    */ {
/*    */   private String msg;
/* 11 */   private Boolean complete = Boolean.valueOf(true);
/*    */ 
/* 13 */   public String getMsg() { return this.msg; }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 16 */     this.msg = msg;
/*    */   }
/*    */   public Boolean getComplete() {
/* 19 */     return this.complete;
/*    */   }
/*    */   public void setComplete(Boolean complete) {
/* 22 */     this.complete = complete;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.activiti.ActivitiCom
 * JD-Core Version:    0.6.0
 */