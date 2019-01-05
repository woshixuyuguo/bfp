/*    */ package org.jeecgframework.workflow.dao.impl;
/*    */ 
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.jeecgframework.core.common.dao.IGenericBaseCommonDao;
/*    */ import org.jeecgframework.core.common.dao.impl.GenericBaseCommonDao;
/*    */ import org.jeecgframework.workflow.dao.IActivitiCommonDao;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ActivitiCommonDao extends GenericBaseCommonDao
/*    */   implements IActivitiCommonDao, IGenericBaseCommonDao
/*    */ {
/*    */   public <T> void batchDelete(Class<T> entityClass)
/*    */   {
/* 19 */     Query query = getSession().createQuery("DELETE FROM " + entityClass.getName());
/* 20 */     query.executeUpdate();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.dao.impl.ActivitiCommonDao
 * JD-Core Version:    0.6.0
 */