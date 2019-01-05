package org.jeecgframework.workflow.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.workflow.model.activiti.ActivitiCom;
import org.jeecgframework.workflow.model.activiti.ProcessHandle;
import org.jeecgframework.workflow.pojo.base.TPProcessnode;
import org.jeecgframework.workflow.pojo.base.TSBusConfig;
import org.jeecgframework.workflow.pojo.base.TSPrjstatus;
import org.springframework.transaction.annotation.Transactional;

public abstract interface ActivitiService extends CommonService
{
  public abstract void save(TSUser paramTSUser, String paramString, Short paramShort);

  public abstract void delete(TSUser paramTSUser);

  public abstract ProcessInstance startWorkflow(Object paramObject, String paramString, Map<String, Object> paramMap, TSBusConfig paramTSBusConfig);

  public abstract List findTodoTasks(String paramString, List<TSBusConfig> paramList);

  public abstract List<Map<String, Object>> traceProcess(String paramString)
    throws Exception;

  public abstract ActivityImpl getProcessMap(String paramString);

  public abstract List<String> highLight(String paramString);

  public abstract String getBusinessKeyByTask(Task paramTask);

  public abstract ActivitiCom complete(String paramString, Map<String, Object> paramMap);

  public abstract String getBusinessKeyByTask(String paramString);

  public abstract TPProcessnode getTPProcessnode(String paramString1, String paramString2);

  @Transactional(readOnly=true)
  public abstract List processDefinitionList();

  public abstract Task getTask(String paramString);

  public abstract ProcessDefinition getProcessDefinition(String paramString);

  public abstract ProcessHandle getProcessHandle(String paramString);

  public abstract HistoricProcessInstance getHiProcInstByBusKey(String paramString);

  public abstract void updateHiProcInstBusKey(String paramString1, String paramString2);

  public abstract TSPrjstatus getTBPrjstatusByCode(String paramString1, String paramString2);

  public abstract TSBusConfig getTSBusConfig(Class paramClass, String paramString);

  public abstract <T> void batchDelete(Class<T> paramClass);

  public abstract List findPriTodoTasks(String paramString, HttpServletRequest paramHttpServletRequest);

  public abstract List findGroupTodoTasks(List<TSRoleUser> paramList, HttpServletRequest paramHttpServletRequest);

  public abstract List findHistoryTasks(String paramString, HttpServletRequest paramHttpServletRequest);

  public abstract Long countPriTodaoTask(String paramString, HttpServletRequest paramHttpServletRequest);

  public abstract Long countGroupTodoTasks(List<TSRoleUser> paramList, HttpServletRequest paramHttpServletRequest);

  public abstract Long countHistoryTasks(String paramString, HttpServletRequest paramHttpServletRequest);
}

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.service.ActivitiService
 * JD-Core Version:    0.6.0
 */