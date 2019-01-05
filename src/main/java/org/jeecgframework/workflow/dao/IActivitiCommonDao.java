package org.jeecgframework.workflow.dao;

import org.jeecgframework.core.common.dao.IGenericBaseCommonDao;

public abstract interface IActivitiCommonDao extends IGenericBaseCommonDao
{
  public abstract <T> void batchDelete(Class<T> paramClass);
}

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.dao.IActivitiCommonDao
 * JD-Core Version:    0.6.0
 */