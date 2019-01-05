/*     */ package org.jeecgframework.workflow.controller.process;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.activiti.engine.HistoryService;
/*     */ import org.activiti.engine.IdentityService;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.repository.ProcessDefinition;
/*     */ import org.activiti.engine.task.Task;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRoleUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.jeecgframework.workflow.common.WorkFlowGlobals;
/*     */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*     */ import org.jeecgframework.workflow.service.ActivitiService;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/taskController"})
/*     */ public class TaskController
/*     */ {
/*  45 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   @Autowired
/*     */   protected RepositoryService repositoryService;
/*     */ 
/*     */   @Autowired
/*     */   protected RuntimeService runtimeService;
/*     */ 
/*     */   @Autowired
/*     */   private ActivitiService activitiService;
/*     */ 
/*     */   @Autowired
/*     */   protected TaskService taskService;
/*     */ 
/*     */   @Autowired
/*     */   protected IdentityService identityService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   private HistoryService historyService;
/*     */ 
/*  66 */   @RequestMapping(params={"goTaskListTab"})
/*     */   public ModelAndView goTaskListTab(HttpServletRequest request) { return new ModelAndView("workflow/task/taskList-tab");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goMyTaskList"})
/*     */   public ModelAndView goMyTaskList(HttpServletRequest request)
/*     */   {
/*  74 */     return new ModelAndView("workflow/task/taskList-person");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goGroupTaskList"})
/*     */   public ModelAndView goGroupTaskList(HttpServletRequest request)
/*     */   {
/*  84 */     return new ModelAndView("workflow/task/taskList-group");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goHistoryTaskList"})
/*     */   public ModelAndView goHistoryTaskList(HttpServletRequest request)
/*     */   {
/*  93 */     return new ModelAndView("workflow/task/taskList-history");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goTaskTab"})
/*     */   public ModelAndView goTaskTab(HttpServletRequest request)
/*     */   {
/* 101 */     String taskId = request.getParameter("taskId");
/* 102 */     request.setAttribute("taskId", taskId);
/* 103 */     return new ModelAndView("workflow/task/task-tab");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goTaskForm"})
/*     */   public ModelAndView goTaskForm(HttpServletRequest request)
/*     */   {
/* 111 */     String taskId = request.getParameter("taskId");
/* 112 */     Task task = this.activitiService.getTask(taskId);
/*     */ 
/* 114 */     String pdId = task.getProcessDefinitionId();
/* 115 */     String insId = task.getProcessInstanceId();
/* 116 */     ProcessDefinition procDf = this.repositoryService.getProcessDefinition(pdId);
/* 117 */     String pkey = procDf.getKey();
/*     */ 
/* 119 */     TPProcessnode node = this.activitiService.getTPProcessnode(WorkFlowGlobals.ProcNode_Start, pkey);
/* 120 */     if ((node != null) && (node.getModelandview() != null))
/*     */     {
/* 122 */       String nodeStartForm = node.getModelandview();
/* 123 */       String bId = this.activitiService.getBusinessKeyByTask(taskId);
/* 124 */       nodeStartForm = nodeStartForm + "&load=detail&id=" + bId;
/* 125 */       request.setAttribute(WorkFlowGlobals.ProcNode_Start, nodeStartForm);
/*     */     }
/* 127 */     return new ModelAndView("workflow/task/task-form");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goTaskOperate"})
/*     */   public ModelAndView goTaskNode(HttpServletRequest request)
/*     */   {
/* 135 */     String taskId = request.getParameter("taskId");
/* 136 */     Task task = this.activitiService.getTask(taskId);
/* 137 */     String taskKey = task.getTaskDefinitionKey();
/* 138 */     String pdId = task.getProcessDefinitionId();
/* 139 */     ProcessDefinition procDf = this.repositoryService.getProcessDefinition(pdId);
/* 140 */     String pkey = procDf.getKey();
/* 141 */     String bId = this.activitiService.getBusinessKeyByTask(taskId);
/*     */ 
/* 143 */     TPProcessnode node = this.activitiService.getTPProcessnode(taskKey, pkey);
/* 144 */     if ((node != null) && (node.getModelandview() != null))
/*     */     {
/* 146 */       String modelandView = node.getModelandview();
/* 147 */       modelandView = modelandView + "&id=" + bId + "&taskId=" + taskId;
/* 148 */       request.setAttribute("taskOperateUrl", modelandView);
/*     */     }
/* 150 */     request.setAttribute("taskId", taskId);
/* 151 */     return new ModelAndView("workflow/task/task-operate");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goTaskMap"})
/*     */   public ModelAndView goTaskMap(HttpServletRequest request)
/*     */   {
/* 159 */     String taskId = request.getParameter("taskId");
/* 160 */     String mapUrl = "activitiController.do?openProcessPic&tag=task&taskId=" + taskId;
/* 161 */     request.setAttribute("mapUrl", mapUrl);
/* 162 */     return new ModelAndView("workflow/task/task-map");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskAllList"})
/*     */   public void taskAllList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 170 */     TSUser user = ResourceUtil.getSessionUserName();
/* 171 */     List taskList = this.activitiService.findPriTodoTasks(user.getUserName(), request);
/* 172 */     Long taskSize = this.activitiService.countPriTodaoTask(user.getUserName(), request);
/* 173 */     dataGrid.setTotal(taskSize.intValue());
/* 174 */     dataGrid.setResults(taskList);
/* 175 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskGroupList"})
/*     */   public void taskGroupList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 184 */     TSUser user = ResourceUtil.getSessionUserName();
/*     */ 
/* 186 */     List roles = this.systemService.findByProperty(TSRoleUser.class, "TSUser", user);
/* 187 */     List taskList = this.activitiService.findGroupTodoTasks(roles, request);
/* 188 */     Long taskSize = this.activitiService.countGroupTodoTasks(roles, request);
/* 189 */     dataGrid.setTotal(taskSize.intValue());
/* 190 */     dataGrid.setResults(taskList);
/* 191 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskHistoryList"})
/*     */   public void taskHistoryList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 200 */     TSUser user = ResourceUtil.getSessionUserName();
/* 201 */     List taskList = this.activitiService.findHistoryTasks(user.getUserName(), request);
/* 202 */     Long taskSize = this.activitiService.countHistoryTasks(user.getUserName(), request);
/* 203 */     dataGrid.setTotal(taskSize.intValue());
/* 204 */     dataGrid.setResults(taskList);
/* 205 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.controller.process.TaskController
 * JD-Core Version:    0.6.0
 */