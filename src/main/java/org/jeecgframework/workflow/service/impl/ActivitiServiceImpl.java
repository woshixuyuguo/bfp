/*      */ package org.jeecgframework.workflow.service.impl;
/*      */ 
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.annotation.Resource;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import org.activiti.engine.ActivitiException;
/*      */ import org.activiti.engine.HistoryService;
/*      */ import org.activiti.engine.IdentityService;
/*      */ import org.activiti.engine.RepositoryService;
/*      */ import org.activiti.engine.RuntimeService;
/*      */ import org.activiti.engine.TaskService;
/*      */ import org.activiti.engine.delegate.Expression;
/*      */ import org.activiti.engine.history.HistoricProcessInstance;
/*      */ import org.activiti.engine.history.HistoricProcessInstanceQuery;
/*      */ import org.activiti.engine.identity.Group;
/*      */ import org.activiti.engine.identity.GroupQuery;
/*      */ import org.activiti.engine.identity.User;
/*      */ import org.activiti.engine.identity.UserQuery;
/*      */ import org.activiti.engine.impl.ProcessEngineImpl;
/*      */ import org.activiti.engine.impl.RepositoryServiceImpl;
/*      */ import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
/*      */ import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
/*      */ import org.activiti.engine.impl.db.DbSqlSession;
/*      */ import org.activiti.engine.impl.db.DbSqlSessionFactory;
/*      */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*      */ import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
/*      */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*      */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*      */ import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
/*      */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*      */ import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
/*      */ import org.activiti.engine.impl.task.TaskDefinition;
/*      */ import org.activiti.engine.repository.ProcessDefinition;
/*      */ import org.activiti.engine.repository.ProcessDefinitionQuery;
/*      */ import org.activiti.engine.runtime.Execution;
/*      */ import org.activiti.engine.runtime.ExecutionQuery;
/*      */ import org.activiti.engine.runtime.ProcessInstance;
/*      */ import org.activiti.engine.runtime.ProcessInstanceQuery;
/*      */ import org.activiti.engine.task.Task;
/*      */ import org.activiti.engine.task.TaskQuery;
/*      */ import org.apache.commons.beanutils.PropertyUtils;
/*      */ import org.apache.commons.lang.builder.ToStringBuilder;
/*      */ import org.apache.ibatis.session.SqlSession;
/*      */ import org.apache.ibatis.session.SqlSessionFactory;
/*      */ import org.hibernate.Query;
/*      */ import org.hibernate.Session;
/*      */ import org.jeecgframework.core.common.dao.ICommonDao;
/*      */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*      */ import org.jeecgframework.core.util.MyClassLoader;
/*      */ import org.jeecgframework.core.util.ReflectHelper;
/*      */ import org.jeecgframework.core.util.StringUtil;
/*      */ import org.jeecgframework.core.util.oConvertUtils;
/*      */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*      */ import org.jeecgframework.web.system.pojo.base.TSRoleUser;
/*      */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*      */ import org.jeecgframework.web.system.service.SystemService;
/*      */ import org.jeecgframework.workflow.common.WorkFlowGlobals;
/*      */ import org.jeecgframework.workflow.dao.IActivitiCommonDao;
/*      */ import org.jeecgframework.workflow.model.activiti.ActivitiCom;
/*      */ import org.jeecgframework.workflow.model.activiti.Process;
/*      */ import org.jeecgframework.workflow.model.activiti.ProcessHandle;
/*      */ import org.jeecgframework.workflow.model.activiti.WorkflowUtils;
/*      */ import org.jeecgframework.workflow.pojo.activiti.ActHiProcinst;
/*      */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcess;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*      */ import org.jeecgframework.workflow.pojo.base.TSBusConfig;
/*      */ import org.jeecgframework.workflow.pojo.base.TSPrjstatus;
/*      */ import org.jeecgframework.workflow.pojo.base.TSTable;
/*      */ import org.jeecgframework.workflow.service.ActivitiService;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.stereotype.Service;
/*      */ import org.springframework.transaction.annotation.Transactional;
/*      */ 
/*      */ @Service("activitiService")
/*      */ @Transactional
/*      */ public class ActivitiServiceImpl extends CommonServiceImpl
/*      */   implements ActivitiService
/*      */ {
/*   83 */   private static Logger logger = LoggerFactory.getLogger(ActivitiServiceImpl.class);
/*      */   private IdentityService identityService;
/*      */   private RuntimeService runtimeService;
/*      */   protected TaskService taskService;
/*      */   protected HistoryService historyService;
/*      */   protected RepositoryService repositoryService;
/*      */   protected String hql;
/*      */   private SystemService systemService;
/*      */   private IActivitiCommonDao activitiCommonDao;
/*      */ 
/*      */   @Autowired
/*      */   public void setIdentityService(IdentityService identityService)
/*      */   {
/*   97 */     this.identityService = identityService;
/*      */   }
/*      */   @Autowired
/*      */   public void setRuntimeService(RuntimeService runtimeService) {
/*  102 */     this.runtimeService = runtimeService;
/*      */   }
/*      */   @Autowired
/*      */   public void setTaskService(TaskService taskService) {
/*  107 */     this.taskService = taskService;
/*      */   }
/*      */   @Autowired
/*      */   public void setHistoryService(HistoryService historyService) {
/*  112 */     this.historyService = historyService;
/*      */   }
/*      */   @Autowired
/*      */   public void setRepositoryService(RepositoryService repositoryService) {
/*  117 */     this.repositoryService = repositoryService;
/*      */   }
/*      */   @Resource
/*      */   public void setActivitiCommonDao(IActivitiCommonDao activitiCommonDao) {
/*  122 */     this.activitiCommonDao = activitiCommonDao;
/*      */   }
/*      */ 
/*      */   public SystemService getSystemService() {
/*  126 */     return this.systemService;
/*      */   }
/*  130 */   @Autowired
/*      */   public void setSystemService(SystemService systemService) { this.systemService = systemService;
/*      */   }
/*      */ 
/*      */   public void save(TSUser user, String roleidstr, Short synToActiviti)
/*      */   {
/*  137 */     if (WorkFlowGlobals.Activiti_Deploy_YES.equals(synToActiviti)) {
/*  138 */       String userId = user.getUserName();
/*  139 */       UserQuery userQuery = this.identityService.createUserQuery();
/*  140 */       List activitiUsers = userQuery.userId(userId).list();
/*  141 */       if (activitiUsers.size() == 1) {
/*  142 */         updateActivitiData(user, roleidstr, (User)activitiUsers.get(0)); } else {
/*  143 */         if (activitiUsers.size() > 1) {
/*  144 */           String errorMsg = "发现重复用户：id=" + userId;
/*  145 */           logger.error(errorMsg);
/*  146 */           throw new RuntimeException(errorMsg);
/*      */         }
/*  148 */         newActivitiUser(user, roleidstr);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void newActivitiUser(TSUser user, String roleidstr)
/*      */   {
/*  155 */     String userId = user.getUserName();
/*  156 */     addActivitiGroup(roleidstr);
/*      */ 
/*  158 */     saveActivitiUser(user);
/*      */ 
/*  160 */     addMembershipToIdentify(roleidstr, userId);
/*      */   }
/*      */ 
/*      */   private void addActivitiGroup(String roleidstr)
/*      */   {
/*  165 */     GroupQuery groupQuery = this.identityService.createGroupQuery();
/*  166 */     String[] roleIds = roleidstr.split(",");
/*  167 */     for (String string : roleIds) {
/*  168 */       TSRole role = (TSRole)this.commonDao.getEntity(TSRole.class, string);
/*  169 */       if (role != null) {
/*  170 */         List activitiGroups = groupQuery.groupId(role.getRoleCode()).list();
/*  171 */         if (activitiGroups.size() <= 0)
/*  172 */           saveActivitiGroup(role);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void saveActivitiGroup(TSRole role)
/*      */   {
/*  185 */     Group activitiGroup = this.identityService.newGroup(role.getRoleCode());
/*  186 */     activitiGroup.setId(role.getRoleCode());
/*  187 */     activitiGroup.setName(role.getRoleName());
/*  188 */     this.identityService.saveGroup(activitiGroup);
/*      */   }
/*      */ 
/*      */   private void saveActivitiUser(TSUser user) {
/*  192 */     String userId = oConvertUtils.getString(user.getUserName());
/*  193 */     User activitiUser = this.identityService.newUser(userId);
/*  194 */     cloneAndSaveActivitiUser(user, activitiUser);
/*      */   }
/*      */ 
/*      */   private void cloneAndSaveActivitiUser(TSUser user, User activitiUser)
/*      */   {
/*  199 */     activitiUser.setFirstName(user.getRealName());
/*  200 */     activitiUser.setLastName(user.getRealName());
/*  201 */     activitiUser.setPassword(user.getPassword());
/*  202 */     activitiUser.setEmail(user.getEmail());
/*  203 */     this.identityService.saveUser(activitiUser);
/*      */   }
/*      */ 
/*      */   private void addMembershipToIdentify(String roleidstr, String userId)
/*      */   {
/*  208 */     String[] roleIds = roleidstr.split(",");
/*  209 */     for (String string : roleIds) {
/*  210 */       TSRole role = (TSRole)this.commonDao.getEntity(TSRole.class, string);
/*  211 */       logger.debug("add role to activit: {}", role);
/*  212 */       if (role != null)
/*  213 */         this.identityService.createMembership(userId, role.getRoleCode());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void updateActivitiData(TSUser user, String roleidstr, User activitiUser)
/*      */   {
/*  219 */     String[] roleIds = roleidstr.split(",");
/*  220 */     String userId = user.getUserName();
/*  221 */     if (roleIds.length > 0) {
/*  222 */       addActivitiGroup(roleidstr);
/*      */     }
/*      */ 
/*  225 */     cloneAndSaveActivitiUser(user, activitiUser);
/*      */ 
/*  227 */     List<Group> activitiGroups = this.identityService.createGroupQuery().groupMember(userId).list();
/*  228 */     for (Group group : activitiGroups) {
/*  229 */       logger.debug("delete group from activit: {}", group);
/*  230 */       this.identityService.deleteMembership(userId, group.getId());
/*      */     }
/*      */ 
/*  233 */     addMembershipToIdentify(roleidstr, userId);
/*      */   }
/*      */ 
/*      */   public void delete(TSUser user)
/*      */   {
/*  244 */     if (user == null) {
/*  245 */       logger.debug("删除用户异常");
/*      */     }
/*      */ 
/*  248 */     List<TSRoleUser> roleUserList = findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
/*  249 */     if (roleUserList.size() >= 1) {
/*  250 */       for (TSRoleUser tRoleUser : roleUserList) {
/*  251 */         TSRole role = tRoleUser.getTSRole();
/*  252 */         if (role != null) {
/*  253 */           this.identityService.deleteMembership(user.getUserName(), role.getRoleCode());
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  259 */     this.identityService.deleteUser(user.getUserName());
/*      */   }
/*      */ 
/*      */   public ProcessInstance startWorkflow(Object entity, String businessKey, Map<String, Object> variables, TSBusConfig tsBusbase)
/*      */   {
/*  266 */     ReflectHelper reflectHelper = new ReflectHelper(entity);
/*  267 */     TSUser tsUser = (TSUser)reflectHelper.getMethodValue("TSUser");
/*  268 */     this.identityService.setAuthenticatedUserId(tsUser.getUserName());
/*      */ 
/*  271 */     variables.put("applyUserIdList", "admin,test,serviceReport");
/*  272 */     ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(tsBusbase.getTPProcess().getProcesskey(), businessKey, variables);
/*  273 */     return processInstance;
/*      */   }
/*      */ 
/*      */   public List findTodoTasks(String userId, List<TSBusConfig> tsBusConfigs)
/*      */   {
/*  285 */     List results = new ArrayList();
/*  286 */     List<Task> tasks = new ArrayList();
/*  287 */     Map classMap = new HashMap();
/*  288 */     String busentity = "";
/*      */ 
/*  291 */     if (tsBusConfigs.size() > 0) {
/*  292 */       for (TSBusConfig busConfig : tsBusConfigs) {
/*      */         try {
/*  294 */           String processKey = busConfig.getTPProcess().getProcesskey();
/*  295 */           busentity = busConfig.getTSTable().getEntityName();
/*      */ 
/*  297 */           List todoList = ((TaskQuery)((TaskQuery)this.taskService.createTaskQuery().processDefinitionKey(processKey).taskAssignee(userId).orderByTaskPriority().desc()).orderByTaskCreateTime().desc()).list();
/*      */ 
/*  299 */           List unsignedTasks = ((TaskQuery)((TaskQuery)this.taskService.createTaskQuery().processDefinitionKey(processKey).taskCandidateUser(userId).orderByTaskPriority().desc()).orderByTaskCreateTime().desc()).list();
/*      */ 
/*  302 */           List<Task> tempList = new ArrayList(0);
/*  303 */           tempList.addAll(todoList);
/*  304 */           tempList.addAll(unsignedTasks);
/*      */ 
/*  306 */           for (Task task : tempList) {
/*  307 */             classMap.put(task.getId(), busentity);
/*      */           }
/*  309 */           tasks.addAll(tempList);
/*      */         }
/*      */         catch (Exception e) {
/*  312 */           logger.info(e.getMessage());
/*      */         }
/*      */       }
/*      */ 
/*  316 */       for (Task task : tasks) {
/*  317 */         String processInstanceId = task.getProcessInstanceId();
/*  318 */         ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/*      */ 
/*  320 */         String businessKey = getBusinessKeyByTask(task);
/*  321 */         Class entityClass = MyClassLoader.getClassByScn((String)classMap.get(task.getId()));
/*  322 */         Object entityObj = getEntity(entityClass, businessKey);
/*  323 */         if (entityObj != null) {
/*  324 */           ReflectHelper reflectHelper = new ReflectHelper(entityObj);
/*  325 */           Process process = (Process)reflectHelper.getMethodValue("Process");
/*  326 */           process.setTask(task);
/*  327 */           process.setProcessInstance(processInstance);
/*  328 */           process.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
/*      */ 
/*  330 */           results.add(entityObj);
/*      */         } else {
/*  332 */           return tasks;
/*      */         }
/*      */       }
/*      */     }
/*  336 */     return results;
/*      */   }
/*      */ 
/*      */   public ProcessDefinition getProcessDefinition(String processDefinitionId)
/*      */   {
/*  347 */     return (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
/*      */   }
/*      */ 
/*      */   public List<Map<String, Object>> traceProcess(String processInstanceId)
/*      */     throws Exception
/*      */   {
/*  357 */     Execution execution = (Execution)this.runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
/*      */ 
/*  359 */     Object property = PropertyUtils.getProperty(execution, "activityId");
/*  360 */     String activityId = "";
/*  361 */     if (property != null) {
/*  362 */       activityId = property.toString();
/*      */     }
/*  364 */     ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/*  365 */     ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
/*  366 */     List<ActivityImpl> activitiList = processDefinition.getActivities();
/*      */ 
/*  368 */     List activityInfos = new ArrayList();
/*  369 */     for (ActivityImpl activity : activitiList)
/*      */     {
/*  371 */       boolean currentActiviti = false;
/*  372 */       String id = activity.getId();
/*      */ 
/*  375 */       if (id.equals(activityId)) {
/*  376 */         currentActiviti = true;
/*      */       }
/*      */ 
/*  379 */       Map activityImageInfo = packageSingleActivitiInfo(activity, processInstance, currentActiviti);
/*      */ 
/*  381 */       activityInfos.add(activityImageInfo);
/*      */     }
/*      */ 
/*  384 */     return activityInfos;
/*      */   }
/*      */ 
/*      */   private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity, ProcessInstance processInstance, boolean currentActiviti)
/*      */     throws Exception
/*      */   {
/*  397 */     Map vars = new HashMap();
/*  398 */     Map activityInfo = new HashMap();
/*  399 */     activityInfo.put("currentActiviti", Boolean.valueOf(currentActiviti));
/*  400 */     setPosition(activity, activityInfo);
/*  401 */     setWidthAndHeight(activity, activityInfo);
/*      */ 
/*  403 */     Map properties = activity.getProperties();
/*  404 */     vars.put("任务类型", WorkflowUtils.parseToZhType(properties.get("type").toString()));
/*      */ 
/*  406 */     ActivityBehavior activityBehavior = activity.getActivityBehavior();
/*  407 */     logger.debug("activityBehavior={}", activityBehavior);
/*  408 */     if ((activityBehavior instanceof UserTaskActivityBehavior))
/*      */     {
/*  410 */       Task currentTask = null;
/*      */ 
/*  415 */       if (currentActiviti) {
/*  416 */         currentTask = getCurrentTaskInfo(processInstance);
/*      */       }
/*      */ 
/*  422 */       UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior)activityBehavior;
/*  423 */       TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
/*  424 */       Set candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
/*  425 */       if (!candidateGroupIdExpressions.isEmpty())
/*      */       {
/*  428 */         setTaskGroup(vars, candidateGroupIdExpressions);
/*      */ 
/*  431 */         if (currentTask != null) {
/*  432 */           setCurrentTaskAssignee(vars, currentTask);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  437 */     vars.put("节点说明", properties.get("documentation"));
/*      */ 
/*  439 */     String description = activity.getProcessDefinition().getDescription();
/*  440 */     vars.put("描述", description);
/*      */ 
/*  442 */     logger.debug("trace variables: {}", vars);
/*  443 */     activityInfo.put("vars", vars);
/*  444 */     return activityInfo;
/*      */   }
/*      */ 
/*      */   private void setTaskGroup(Map<String, Object> vars, Set<Expression> candidateGroupIdExpressions) {
/*  448 */     String roles = "";
/*  449 */     for (Expression expression : candidateGroupIdExpressions) {
/*  450 */       String expressionText = expression.getExpressionText();
/*  451 */       if (expressionText.startsWith("$")) {
/*  452 */         expressionText = expressionText.replace("${insuranceType}", "life");
/*      */       }
/*  454 */       String roleName = ((Group)this.identityService.createGroupQuery().groupId(expressionText).singleResult()).getName();
/*  455 */       roles = roles + roleName;
/*      */     }
/*  457 */     vars.put("任务所属角色", roles);
/*      */   }
/*      */ 
/*      */   private void setCurrentTaskAssignee(Map<String, Object> vars, Task currentTask)
/*      */   {
/*  467 */     String assignee = currentTask.getAssignee();
/*  468 */     if (assignee != null) {
/*  469 */       User assigneeUser = (User)this.identityService.createUserQuery().userId(assignee).singleResult();
/*  470 */       String userInfo = assigneeUser.getFirstName() + " " + assigneeUser.getLastName();
/*  471 */       vars.put("当前处理人", userInfo);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Task getCurrentTaskInfo(ProcessInstance processInstance)
/*      */   {
/*  482 */     Task currentTask = null;
/*      */     try {
/*  484 */       String activitiId = (String)PropertyUtils.getProperty(processInstance, "activityId");
/*  485 */       logger.debug("current activity id: {}", activitiId);
/*      */ 
/*  487 */       currentTask = (Task)this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId).singleResult();
/*  488 */       logger.debug("current task for processInstance: {}", ToStringBuilder.reflectionToString(currentTask));
/*      */     }
/*      */     catch (Exception e) {
/*  491 */       logger.error("can not get property activityId from processInstance: {}", processInstance);
/*      */     }
/*  493 */     return currentTask;
/*      */   }
/*      */ 
/*      */   private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo)
/*      */   {
/*  503 */     activityInfo.put("width", Integer.valueOf(activity.getWidth()));
/*  504 */     activityInfo.put("height", Integer.valueOf(activity.getHeight()));
/*      */   }
/*      */ 
/*      */   private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo)
/*      */   {
/*  514 */     activityInfo.put("x", Integer.valueOf(activity.getX()));
/*  515 */     activityInfo.put("y", Integer.valueOf(activity.getY()));
/*      */   }
/*      */ 
/*      */   public ActivityImpl getProcessMap(String processInstanceId)
/*      */   {
/*  525 */     Execution execution = (Execution)this.runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();
/*  526 */     if (execution == null) return null;
/*  527 */     Object property = null;
/*      */     try {
/*  529 */       property = PropertyUtils.getProperty(execution, "activityId");
/*      */     } catch (IllegalAccessException e) {
/*  531 */       logger.error("反射异常", e);
/*      */     } catch (InvocationTargetException e) {
/*  533 */       e.printStackTrace();
/*      */     } catch (NoSuchMethodException e) {
/*  535 */       e.printStackTrace();
/*      */     }
/*  537 */     String activityId = "";
/*  538 */     if (property != null) {
/*  539 */       activityId = property.toString();
/*      */     }
/*  541 */     ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/*  542 */     ProcessDefinition processDefinition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
/*  543 */     ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl)processDefinition;
/*  544 */     String processDefinitionId = pdImpl.getId();
/*  545 */     ProcessDefinitionEntity def = (ProcessDefinitionEntity)((RepositoryServiceImpl)this.repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
/*  546 */     ActivityImpl actImpl = null;
/*  547 */     List activitiIds = this.runtimeService.getActiveActivityIds(execution.getId());
/*  548 */     List a = highLight(processInstanceId);
/*  549 */     List<ActivityImpl> activitiList = def.getActivities();
/*      */ 
/*  551 */     for (ActivityImpl activityImpl : activitiList) {
/*  552 */       String id = activityImpl.getId();
/*  553 */       if (id.equals(activityId)) {
/*  554 */         actImpl = activityImpl;
/*  555 */         break;
/*      */       }
/*      */     }
/*      */ 
/*  559 */     return actImpl;
/*      */   }
/*      */ 
/*      */   public List<String> highLight(String processInstanceId)
/*      */   {
/*  566 */     List highLihth = new ArrayList();
/*  567 */     List<Execution> executions = this.runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
/*  568 */     for (Execution execution : executions) {
/*  569 */       ExecutionEntity entity = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(execution.getId()).singleResult();
/*  570 */       highLihth.add(entity.getActivityId());
/*      */     }
/*  572 */     return highLihth;
/*      */   }
/*      */ 
/*      */   public String oldgetBusinessKeyByTask(Task task)
/*      */   {
/*  583 */     String businessKey = "";
/*  584 */     TaskEntity taskEntity = (TaskEntity)this.taskService.createTaskQuery().taskId(task.getId()).singleResult();
/*  585 */     ExecutionEntity executionEntity = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(taskEntity.getExecutionId()).singleResult();
/*  586 */     if (executionEntity.getSuperExecutionId() == null) {
/*  587 */       businessKey = executionEntity.getBusinessKey();
/*      */     } else {
/*  589 */       ExecutionEntity executionEntity2 = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(executionEntity.getSuperExecutionId()).singleResult();
/*  590 */       ExecutionEntity executionEntity3 = (ExecutionEntity)this.runtimeService.createExecutionQuery().executionId(executionEntity2.getParentId()).singleResult();
/*  591 */       businessKey = executionEntity3.getBusinessKey();
/*      */     }
/*  593 */     return businessKey;
/*      */   }
/*      */ 
/*      */   public String getBusinessKeyByTask(Task task)
/*      */   {
/*  603 */     String businessKey = "";
/*  604 */     TaskEntity taskEntity = (TaskEntity)this.taskService.createTaskQuery().taskId(task.getId()).singleResult();
/*  605 */     HistoricProcessInstance hiproins = (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().processInstanceId(taskEntity.getProcessInstanceId()).singleResult();
/*  606 */     if (hiproins != null) {
/*  607 */       if ((hiproins.getSuperProcessInstanceId() != null) && (hiproins.getBusinessKey() == null)) {
/*  608 */         hiproins = (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().processInstanceId(hiproins.getSuperProcessInstanceId()).singleResult();
/*  609 */         businessKey = hiproins.getBusinessKey();
/*      */       } else {
/*  611 */         businessKey = hiproins.getBusinessKey();
/*      */       }
/*      */     }
/*  614 */     return businessKey;
/*      */   }
/*      */ 
/*      */   public HistoricProcessInstance getHiProcInstByBusKey(String businessKey)
/*      */   {
/*  624 */     HistoricProcessInstance hiproins = null;
/*  625 */     hiproins = (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
/*  626 */     return hiproins;
/*      */   }
/*      */ 
/*      */   public HistoricProcessInstance getHiProcInstByParprocInstId(String parprocInstId)
/*      */   {
/*  636 */     return (HistoricProcessInstance)this.historyService.createHistoricProcessInstanceQuery().superProcessInstanceId(parprocInstId).singleResult();
/*      */   }
/*      */ 
/*      */   public void updateHiProcInstBusKey(String parBusKey, String subBusKey)
/*      */   {
/*  649 */     HistoricProcessInstance parhiproins = getHiProcInstByBusKey(parBusKey);
/*  650 */     HistoricProcessInstance subhiproins = getHiProcInstByParprocInstId(parhiproins.getId());
/*  651 */     if (subhiproins != null) {
/*  652 */       HistoricProcessInstanceEntity historicProcessInstanceEntity = (HistoricProcessInstanceEntity)subhiproins;
/*  653 */       ActHiProcinst actHiProcinst = (ActHiProcinst)getEntity(ActHiProcinst.class, historicProcessInstanceEntity.getId());
/*  654 */       actHiProcinst.setBusinessKey(subBusKey);
/*  655 */       updateEntitie(actHiProcinst);
/*      */     }
/*      */   }
/*      */ 
/*      */   public ActivitiCom complete(String taskId, Map<String, Object> map)
/*      */   {
/*  664 */     ActivitiCom activitiCom = new ActivitiCom();
/*  665 */     String msg = "";
/*  666 */     Boolean complete = Boolean.valueOf(false);
/*      */     try
/*      */     {
/*  669 */       String businessKey = getBusinessKeyByTask(taskId);
/*  670 */       this.taskService.complete(taskId, map);
/*      */ 
/*  672 */       List list = this.historyService.createHistoricProcessInstanceQuery().finished().processInstanceBusinessKey(businessKey).list();
/*  673 */       if ((list != null) && (list.size() == 1))
/*      */       {
/*  675 */         this.commonDao.updateBySqlString("update t_s_basebus set prjstateid = '3' where id ='" + businessKey + "'");
/*      */       }
/*  677 */       complete = Boolean.valueOf(true);
/*  678 */       msg = "办理成功";
/*      */     } catch (ActivitiException e) {
/*  680 */       if (e.getMessage().indexOf("no processes deployed with key") != -1) {
/*  681 */         msg = "没有部署子流程!,请在流程配置中部署流程";
/*  682 */         complete = Boolean.valueOf(false);
/*      */       } else {
/*  684 */         msg = "启动流程失败!,内部错误";
/*  685 */         complete = Boolean.valueOf(false);
/*      */       }
/*      */     }
/*      */     catch (Exception e) {
/*  689 */       msg = "内部错误";
/*  690 */       complete = Boolean.valueOf(false);
/*      */     }
/*  692 */     activitiCom.setComplete(complete);
/*  693 */     activitiCom.setMsg(msg);
/*  694 */     return activitiCom;
/*      */   }
/*      */ 
/*      */   public String getBusinessKeyByTask(String taskId)
/*      */   {
/*  701 */     Task task = (Task)this.taskService.createTaskQuery().taskId(taskId).singleResult();
/*  702 */     return getBusinessKeyByTask(task);
/*      */   }
/*      */ 
/*      */   public TPProcessnode getTPProcessnode(String taskDefinitionKey, String processkey)
/*      */   {
/*  709 */     TPProcessnode processnode = null;
/*  710 */     this.hql = ("from TPProcessnode t where t.TPProcess.processkey='" + processkey + "' and t.processnodecode='" + taskDefinitionKey + "'");
/*  711 */     List processnodeList = this.commonDao.findByQueryString(this.hql);
/*  712 */     if (processnodeList.size() > 0) {
/*  713 */       processnode = (TPProcessnode)processnodeList.get(0);
/*      */     }
/*  715 */     return processnode;
/*      */   }
/*      */ 
/*      */   @Transactional(readOnly=true)
/*      */   public List processDefinitionList()
/*      */   {
/*  723 */     return this.repositoryService.createProcessDefinitionQuery().list();
/*      */   }
/*      */ 
/*      */   public Task getTask(String taskId)
/*      */   {
/*  733 */     return (Task)this.taskService.createTaskQuery().taskId(taskId).singleResult();
/*      */   }
/*      */ 
/*      */   public ProcessHandle getProcessHandle(String taskId)
/*      */   {
/*  743 */     ProcessHandle processHandle = new ProcessHandle();
/*  744 */     Task task = getTask(taskId);
/*  745 */     String businessKey = getBusinessKeyByTask(taskId);
/*  746 */     String processDefinitionKey = getProcessDefinition(task.getProcessDefinitionId()).getKey();
/*  747 */     String taskDefinitionKey = task.getTaskDefinitionKey();
/*      */ 
/*  749 */     TPProcess tpProcess = (TPProcess)findUniqueByProperty(TPProcess.class, "processkey", processDefinitionKey);
/*  750 */     TPProcessnode tpProcessnode = getTPProcessnode(taskDefinitionKey, processDefinitionKey);
/*  751 */     TPForm tpForm = tpProcessnode.getTPForm();
/*  752 */     List tpProcesspros = tpProcessnode.getTPProcesspros();
/*  753 */     processHandle.setProcessDefinitionKey(processDefinitionKey);
/*  754 */     processHandle.setTaskDefinitionKey(taskDefinitionKey);
/*  755 */     processHandle.setBusinessKey(businessKey);
/*  756 */     processHandle.setTaskId(taskId);
/*  757 */     processHandle.setTpForm(tpForm);
/*  758 */     processHandle.setTpProcess(tpProcess);
/*  759 */     processHandle.setTpProcessnode(tpProcessnode);
/*  760 */     processHandle.setTpProcesspros(tpProcesspros);
/*  761 */     return processHandle;
/*      */   }
/*      */ 
/*      */   private static SqlSession getSqlSession() {
/*  765 */     ProcessEngineImpl processEngine = null;
/*  766 */     DbSqlSessionFactory dbSqlSessionFactory = (DbSqlSessionFactory)processEngine.getProcessEngineConfiguration().getSessionFactories().get(DbSqlSession.class);
/*  767 */     SqlSessionFactory sqlSessionFactory = dbSqlSessionFactory.getSqlSessionFactory();
/*  768 */     return sqlSessionFactory.openSession();
/*      */   }
/*      */ 
/*      */   public TSPrjstatus getTBPrjstatusByCode(String prjstate, String rolecode)
/*      */   {
/*  780 */     TSPrjstatus tsPrjstatus = null;
/*  781 */     String[] rolecodes = rolecode.split(",");
/*  782 */     String search = "";
/*  783 */     for (int i = 0; i < rolecodes.length; i++) {
/*  784 */       search = search + "'" + rolecodes[i] + "'";
/*  785 */       if (i < rolecodes.length - 1) {
/*  786 */         search = search + ",";
/*      */       }
/*      */     }
/*      */ 
/*  790 */     if (search != "") {
/*  791 */       List tbPrjstatuList = this.commonDao.findByQueryString("from TSPrjstatus p where p.code = '" + prjstate + "' and p.executerole in(" + search + ")");
/*  792 */       if (tbPrjstatuList.size() > 0) {
/*  793 */         tsPrjstatus = (TSPrjstatus)tbPrjstatuList.get(0);
/*      */       }
/*      */     }
/*      */ 
/*  797 */     return tsPrjstatus;
/*      */   }
/*      */ 
/*      */   public TSBusConfig getTSBusConfig(Class classname, String processkey)
/*      */   {
/*  804 */     TSBusConfig tsBusConfig = null;
/*  805 */     String hql = "from TSBusConfig where TSTable.entityName='" + classname.getName() + "' and TPProcess.processkey='" + processkey + "'";
/*  806 */     List tsBusConfigList = this.commonDao.findByQueryString(hql);
/*  807 */     if (tsBusConfigList.size() > 0) {
/*  808 */       tsBusConfig = (TSBusConfig)tsBusConfigList.get(0);
/*      */     }
/*  810 */     return tsBusConfig;
/*      */   }
/*      */ 
/*      */   public <T> void batchDelete(Class<T> entityClass)
/*      */   {
/*  816 */     this.activitiCommonDao.batchDelete(entityClass); } 
/*  826 */   private List findBaseTodoTasks(boolean isPri, String id, HttpServletRequest request) { List results = new ArrayList();
/*  827 */     List<Task> tasks = new ArrayList();
/*      */ 
/*  831 */     Integer page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
/*  832 */     Integer rows = Integer.valueOf(Integer.parseInt(request.getParameter("rows")));
/*  833 */     Integer start = Integer.valueOf((page.intValue() - 1) * rows.intValue());
/*  834 */     Integer end = Integer.valueOf(page.intValue() * rows.intValue() - 1);
/*      */     TaskQuery tq;
/*      */     try { List tempList = new ArrayList(0);
/*  838 */       if (isPri)
/*      */       {
/*  843 */          tq = (TaskQuery)((TaskQuery)this.taskService.createTaskQuery().taskAssignee(id).orderByTaskPriority().desc()).orderByTaskCreateTime().desc();
/*  844 */         tq = installQueryParam(tq, request);
/*  845 */         List todoList = tq.listPage(start.intValue(), end.intValue());
/*  846 */         tempList.addAll(todoList);
/*      */       }
/*      */       else
/*      */       {
/*  852 */         tq = (TaskQuery)((TaskQuery)this.taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList(id.split(","))).orderByTaskPriority().desc()).orderByTaskCreateTime().desc();
/*  853 */         tq = installQueryParam(tq, request);
/*  854 */         List unsignedTasks = tq.listPage(start.intValue(), end.intValue());
/*  855 */         tempList.addAll(unsignedTasks);
/*      */       }
/*  857 */       tasks.addAll(tempList);
/*      */     } catch (Exception e) {
/*  859 */       e.printStackTrace();
/*      */     }
/*      */ 
/*  862 */     for (Task task : tasks) {
/*  863 */       String processInstanceId = task.getProcessInstanceId();
/*  864 */       ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/*      */ 
/*  866 */       String businessKey = getBusinessKeyByTask(task);
/*  867 */       Class entityClass = MyClassLoader.getClassByScn("org.jeecgframework.workflow.pojo.base.TSBaseBusQuery");
/*  868 */       Object entityObj = getEntity(entityClass, businessKey);
/*  869 */       if (entityObj != null) {
/*  870 */         ReflectHelper reflectHelper = new ReflectHelper(entityObj);
/*  871 */         Process process = (Process)reflectHelper.getMethodValue("Process");
/*  872 */         process.setTask(task);
/*  873 */         process.setProcessInstance(processInstance);
/*  874 */         process.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
/*      */ 
/*  876 */         results.add(entityObj);
/*      */       } else {
/*  878 */         return tasks;
/*      */       }
/*      */     }
/*  881 */     return results;
/*      */   }
/*      */ 
/*      */   public List findPriTodoTasks(String userId, HttpServletRequest request)
/*      */   {
/*  886 */     return findBaseTodoTasks(true, userId, request);
/*      */   }
/*      */ 
/*      */   public List findGroupTodoTasks(List<TSRoleUser> roles, HttpServletRequest request) {
/*  890 */     StringBuilder roleIds = new StringBuilder();
/*      */ 
/*  892 */     for (TSRoleUser role : roles) {
/*  893 */       roleIds.append(role.getTSRole().getRoleCode()).append(",");
/*      */     }
/*  895 */     roleIds.deleteCharAt(roleIds.length() - 1);
/*  896 */     List resulttemp = findBaseTodoTasks(false, roleIds.toString(), request);
/*  897 */     return resulttemp;
/*      */   }
/*      */ 
/*      */   public List findHistoryTasks(String userName, HttpServletRequest request)
/*      */   {
/*  904 */     Integer page = Integer.valueOf(Integer.parseInt(request.getParameter("page")));
/*  905 */     Integer rows = Integer.valueOf(Integer.parseInt(request.getParameter("rows")));
/*  906 */     Integer start = Integer.valueOf((page.intValue() - 1) * rows.intValue());
/*  907 */     Query query = getSession().createQuery(installQueryParamH("from ActHiTaskinst o where o.duration>0 and o.assignee = ? ", request));
/*  908 */     query.setFirstResult(start.intValue());
/*  909 */     query.setMaxResults(rows.intValue());
/*  910 */     query.setString(0, userName);
/*  911 */     installQueryParamHV(query, request);
/*  912 */     List result = query.list();
/*  913 */     return result;
/*      */   }
/*      */ 
/*      */   public Long countPriTodaoTask(String userName, HttpServletRequest request)
/*      */   {
/*  919 */     String procDefId = request.getParameter("Process.processDefinition.id");
/*  920 */     String procName = request.getParameter("Process.processDefinition.name");
/*  921 */     Long size = Long.valueOf(0L);
/*      */ 
/*  923 */     TaskQuery tq = (TaskQuery)((TaskQuery)this.taskService.createTaskQuery().taskAssignee(userName).orderByTaskPriority().desc()).orderByTaskCreateTime().desc();
/*  924 */     installQueryParam(tq, request);
/*  925 */     size = Long.valueOf(tq.count());
/*  926 */     return size;
/*      */   }
/*      */ 
/*      */   public Long countGroupTodoTasks(List<TSRoleUser> roles, HttpServletRequest request)
/*      */   {
/*  932 */     String procDefId = request.getParameter("Process.processDefinition.id");
/*  933 */     String procName = request.getParameter("Process.processDefinition.name");
/*  934 */     Long size = Long.valueOf(0L);
/*  935 */     StringBuilder roleIds = new StringBuilder();
/*      */ 
/*  937 */     for (TSRoleUser role : roles) {
/*  938 */       roleIds.append(role.getTSRole().getRoleCode()).append(",");
/*      */     }
/*  940 */     roleIds.deleteCharAt(roleIds.length() - 1);
/*      */ 
/*  942 */     TaskQuery tq = (TaskQuery)((TaskQuery)this.taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList(roleIds.toString().split(","))).orderByTaskPriority().desc()).orderByTaskCreateTime().desc();
/*  943 */     installQueryParam(tq, request);
/*  944 */     size = Long.valueOf(tq.count());
/*  945 */     return size;
/*      */   }
/*      */ 
/*      */   public Long countHistoryTasks(String userName, HttpServletRequest request)
/*      */   {
/*  950 */     Map r = this.systemService.findOneForJdbc(installCountH("select count(1) as hsize  from act_hi_taskinst o inner join act_re_procdef ap where ap.id_ = o.proc_def_id_ and o.duration_>0 and  o.assignee_ = ?", request), installCountHv(userName, request));
/*  951 */     Long size = Long.valueOf(Long.parseLong(r.get("hsize").toString()));
/*  952 */     return size;
/*      */   }
/*      */ 
/*      */   private TaskQuery installQueryParam(TaskQuery tq, HttpServletRequest request)
/*      */   {
/*  963 */     String procDefId = request.getParameter("Process.processDefinition.id");
/*  964 */     String procName = request.getParameter("Process.processDefinition.name");
/*  965 */     if (StringUtil.isNotEmpty(procDefId)) {
/*  966 */       tq = tq.processDefinitionId(procDefId);
/*      */     }
/*  968 */     if (StringUtil.isNotEmpty(procName)) {
/*  969 */       tq = tq.processDefinitionName(procName);
/*      */     }
/*  971 */     return tq;
/*      */   }
/*      */ 
/*      */   private String installQueryParamH(String sql, HttpServletRequest request)
/*      */   {
/*  981 */     String procDefId = request.getParameter("procDefId");
/*  982 */     String procName = request.getParameter("prodef.name");
/*  983 */     StringBuilder s = new StringBuilder(sql);
/*  984 */     if (StringUtil.isNotEmpty(procDefId)) {
/*  985 */       s.append(" and o.procDefId = ? ");
/*      */     }
/*  987 */     if (StringUtil.isNotEmpty(procName)) {
/*  988 */       s.append(" and o.prodef.name = ? ");
/*      */     }
/*  990 */     return s.toString();
/*      */   }
/*      */ 
/*      */   private void installQueryParamHV(Query query, HttpServletRequest request) {
/*  994 */     String procDefId = request.getParameter("procDefId");
/*  995 */     String procName = request.getParameter("prodef.name");
/*  996 */     if (StringUtil.isNotEmpty(procDefId)) {
/*  997 */       query.setParameter(1, procDefId);
/*      */     }
/*  999 */     if (StringUtil.isNotEmpty(procName))
/* 1000 */       query.setParameter(2, procName);
/*      */   }
/*      */ 
/*      */   private String installCountH(String sql, HttpServletRequest request)
/*      */   {
/* 1006 */     String procDefId = request.getParameter("procDefId");
/* 1007 */     String procName = request.getParameter("prodef.name");
/* 1008 */     StringBuilder s = new StringBuilder(sql);
/* 1009 */     if (StringUtil.isNotEmpty(procDefId)) {
/* 1010 */       s.append(" and o.proc_def_id_ = ? ");
/*      */     }
/* 1012 */     if (StringUtil.isNotEmpty(procName)) {
/* 1013 */       s.append(" and ap.name_ = ? ");
/*      */     }
/* 1015 */     return s.toString();
/*      */   }
/*      */ 
/*      */   private Object[] installCountHv(String userName, HttpServletRequest request)
/*      */   {
/* 1020 */     List reList = new ArrayList(0);
/* 1021 */     reList.add(userName);
/* 1022 */     String procDefId = request.getParameter("procDefId");
/* 1023 */     String procName = request.getParameter("prodef.name");
/* 1024 */     if (StringUtil.isNotEmpty(procDefId)) {
/* 1025 */       reList.add(procDefId);
/*      */     }
/* 1027 */     if (StringUtil.isNotEmpty(procName)) {
/* 1028 */       reList.add(procName);
/*      */     }
/* 1030 */     return reList.toArray();
/*      */   }
/*      */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.service.impl.ActivitiServiceImpl
 * JD-Core Version:    0.6.0
 */