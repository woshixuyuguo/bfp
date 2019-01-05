/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ import org.activiti.engine.impl.interceptor.Session;
/*    */ import org.activiti.engine.impl.interceptor.SessionFactory;
/*    */ import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
/*    */ import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
/*    */ 
/*    */ public class CustomGroupEntityManagerFactory
/*    */   implements SessionFactory
/*    */ {
/*    */   private GroupEntityManager groupEntityManager;
/*    */ 
/*    */   public void setGroupEntityManager(GroupEntityManager groupEntityManager)
/*    */   {
/* 13 */     this.groupEntityManager = groupEntityManager;
/*    */   }
/*    */ 
/*    */   public Class<?> getSessionType() {
/* 17 */     return GroupIdentityManager.class;
/*    */   }
/*    */ 
/*    */   public Session openSession() {
/* 21 */     return this.groupEntityManager;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.CustomGroupEntityManagerFactory
 * JD-Core Version:    0.6.0
 */