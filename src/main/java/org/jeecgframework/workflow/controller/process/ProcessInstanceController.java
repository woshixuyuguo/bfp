/*     */ package org.jeecgframework.workflow.controller.process;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.ParseException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.activiti.engine.HistoryService;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.history.HistoricProcessInstance;
/*     */ import org.activiti.engine.history.HistoricProcessInstanceQuery;
/*     */ import org.activiti.engine.repository.ProcessDefinition;
/*     */ import org.activiti.engine.repository.ProcessDefinitionQuery;
/*     */ import org.activiti.engine.runtime.ProcessInstance;
/*     */ import org.activiti.engine.runtime.ProcessInstanceQuery;
/*     */ import org.activiti.engine.task.Task;
/*     */ import org.activiti.engine.task.TaskQuery;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.time.DateFormatUtils;
/*     */ import org.apache.commons.lang3.time.DateUtils;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.UserService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/processInstanceController"})
/*     */ public class ProcessInstanceController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   protected RepositoryService repositoryService;
/*     */ 
/*     */   @Autowired
/*     */   private HistoryService historyService;
/*     */ 
/*     */   @Autowired
/*     */   private RuntimeService runtimeService;
/*     */ 
/*     */   @Autowired
/*     */   private TaskService taskService;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @RequestMapping(params={"runningProcessList"})
/*     */   public ModelAndView runningProcessList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  73 */     return new ModelAndView("workflow/activiti/runninglist");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"myRunningProcessListDataGrid"})
/*     */   public void myRunningProcessListDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  83 */     String currentUserName = ResourceUtil.getSessionUserName().getUserName();
/*  84 */     HistoricProcessInstanceQuery historicProcessInstanceQuery = this.historyService.createHistoricProcessInstanceQuery().startedBy(currentUserName);
/*  85 */     List<HistoricProcessInstance> list = ((HistoricProcessInstanceQuery)historicProcessInstanceQuery.orderByProcessInstanceEndTime().asc()).listPage((dataGrid.getPage() - 1) * dataGrid.getRows(), dataGrid.getRows());
/*     */ 
/*  87 */     StringBuffer rows = new StringBuffer();
/*  88 */     for (HistoricProcessInstance hi : list) {
/*  89 */       String starttime = DateFormatUtils.format(hi.getStartTime(), "yyyy-MM-dd HH:mm:ss");
/*  90 */       String endtime = hi.getEndTime() == null ? "" : DateFormatUtils.format(hi.getEndTime(), "yyyy-MM-dd HH:mm:ss");
/*     */ 
/*  92 */       long totalTimes = hi.getEndTime() == null ? Calendar.getInstance().getTimeInMillis() - hi.getStartTime().getTime() : hi.getEndTime().getTime() - hi.getStartTime().getTime();
/*     */ 
/*  94 */       long dayCount = totalTimes / 86400000L;
/*  95 */       long restTimes = totalTimes % 86400000L;
/*  96 */       long hourCount = restTimes / 3600000L;
/*  97 */       restTimes %= 3600000L;
/*  98 */       long minuteCount = restTimes / 60000L;
/*     */ 
/* 100 */       String spendTimes = dayCount + "天" + hourCount + "小时" + minuteCount + "分";
/* 101 */       ProcessDefinition processDefinition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().processDefinitionId(hi.getProcessDefinitionId()).singleResult();
/* 102 */       rows.append("{'id':" + hi.getId() + ",'prcocessDefinitionName':'" + StringUtils.trimToEmpty(processDefinition.getName()) + "','startUserId':'" + hi.getStartUserId() + "','starttime':'" + starttime + "','endtime':'" + endtime + "','spendTimes':'" + spendTimes + "','processDefinitionId':'" + hi.getProcessDefinitionId() + "','processInstanceId':'" + hi.getId() + "'},");
/*     */     }
/*     */ 
/* 106 */     String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
/*     */ 
/* 108 */     JSONObject jObject = JSONObject.fromObject("{'total':" + historicProcessInstanceQuery.count() + ",'rows':[" + rowStr + "]}");
/* 109 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"invalidProcess"})
/*     */   @ResponseBody
/*     */   public AjaxJson invalidProcess(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request)
/*     */   {
/* 121 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 123 */     ProcessInstance pi = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/* 124 */     this.runtimeService.deleteProcessInstance(processInstanceId, "发起人流程作废");
/* 125 */     String message = "作废流程成功";
/* 126 */     j.setMsg(message);
/* 127 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"callBackProcess"})
/*     */   @ResponseBody
/*     */   public AjaxJson callBackProcess(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request)
/*     */   {
/* 138 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 140 */     ProcessInstance pi = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
/*     */ 
/* 142 */     this.runtimeService.deleteProcessInstance(processInstanceId, "发起人流程追回");
/* 143 */     String message = "流程追回成功";
/* 144 */     j.setMsg(message);
/* 145 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"reassignInit"})
/*     */   public ModelAndView reassignInit(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 159 */     String processInstanceId = request.getParameter("processInstanceId");
/* 160 */     request.setAttribute("processInstanceId", processInstanceId);
/* 161 */     return new ModelAndView("workflow/activiti/reassignInit");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"reassign"})
/*     */   @ResponseBody
/*     */   public AjaxJson reassign(@RequestParam("processInstanceId") String processInstanceId, @RequestParam("userName") String assignUserId, HttpServletRequest request)
/*     */   {
/* 171 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 174 */     Task task = (Task)this.taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
/*     */ 
/* 176 */     String currentUserName = ResourceUtil.getSessionUserName().getUserName();
/* 177 */     this.taskService.setOwner(task.getId(), currentUserName);
/* 178 */     this.taskService.setAssignee(task.getId(), assignUserId);
/*     */ 
/* 180 */     String message = "委派成功";
/* 181 */     j.setMsg(message);
/* 182 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"reassignUsers"})
/*     */   public String reassignUsers()
/*     */   {
/* 192 */     return "workflow/activiti/reassignUsers";
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagridUsers"})
/*     */   public void datagridUsers(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 204 */     CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
/* 205 */     this.userService.getDataGridReturn(cq, true);
/* 206 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"runningProcessDataGrid"})
/*     */   public void runningProcessDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 222 */     HistoricProcessInstanceQuery historicProcessInstanceQuery = this.historyService.createHistoricProcessInstanceQuery();
/*     */ 
/* 224 */     if (StringUtils.isNotEmpty(request.getParameter("startUserId"))) {
/* 225 */       historicProcessInstanceQuery = historicProcessInstanceQuery.startedBy(request.getParameter("startUserId"));
/*     */     }
/* 227 */     if (StringUtils.isNotEmpty(request.getParameter("processInstanceId"))) {
/* 228 */       historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceId(request.getParameter("processInstanceId"));
/*     */     }
/*     */ 
/* 231 */     String starttime_begin = request.getParameter("starttime_begin");
/* 232 */     String starttime_end = request.getParameter("starttime_end");
/* 233 */     if (StringUtils.isNotEmpty(starttime_begin)) {
/*     */       try {
/* 235 */         historicProcessInstanceQuery.startedAfter(DateUtils.parseDate(starttime_begin, new String[] { "yyyy-MM-dd" }));
/*     */       } catch (ParseException e) {
/* 237 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 240 */     if (StringUtils.isNotEmpty(starttime_end)) {
/*     */       try {
/* 242 */         historicProcessInstanceQuery.startedBefore(DateUtils.parseDate(starttime_end, new String[] { "yyyy-MM-dd" }));
/*     */       } catch (ParseException e) {
/* 244 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 248 */     String endtime_begin = request.getParameter("endtime_begin");
/* 249 */     String endtime_end = request.getParameter("endtime_end");
/* 250 */     if (StringUtils.isNotEmpty(endtime_begin)) {
/*     */       try {
/* 252 */         historicProcessInstanceQuery.finishedAfter(DateUtils.parseDate(endtime_begin, new String[] { "yyyy-MM-dd" }));
/*     */       } catch (ParseException e) {
/* 254 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 257 */     if (StringUtils.isNotEmpty(endtime_end)) {
/*     */       try {
/* 259 */         historicProcessInstanceQuery.finishedBefore(DateUtils.parseDate(endtime_end, new String[] { "yyyy-MM-dd" }));
/*     */       } catch (ParseException e) {
/* 261 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 265 */     List<HistoricProcessInstance> list = ((HistoricProcessInstanceQuery)historicProcessInstanceQuery.orderByProcessInstanceEndTime().asc()).listPage((dataGrid.getPage() - 1) * dataGrid.getRows(), dataGrid.getRows());
/*     */ 
/* 267 */     StringBuffer rows = new StringBuffer();
/* 268 */     for (HistoricProcessInstance hi : list) {
/* 269 */       String starttime = DateFormatUtils.format(hi.getStartTime(), "yyyy-MM-dd HH:mm:ss");
/* 270 */       String endtime = hi.getEndTime() == null ? "" : DateFormatUtils.format(hi.getEndTime(), "yyyy-MM-dd HH:mm:ss");
/*     */ 
/* 273 */       long totalTimes = hi.getEndTime() == null ? Calendar.getInstance().getTimeInMillis() - hi.getStartTime().getTime() : hi.getEndTime().getTime() - hi.getStartTime().getTime();
/*     */ 
/* 275 */       long dayCount = totalTimes / 86400000L;
/* 276 */       long restTimes = totalTimes % 86400000L;
/* 277 */       long hourCount = restTimes / 3600000L;
/* 278 */       restTimes %= 3600000L;
/* 279 */       long minuteCount = restTimes / 60000L;
/*     */ 
/* 281 */       String spendTimes = dayCount + "天" + hourCount + "小时" + minuteCount + "分";
/*     */ 
/* 283 */       boolean isSuspended = true;
/* 284 */       String activityName = "";
/* 285 */       String activityUser = "";
/* 286 */       if (hi.getEndTime() == null) {
/* 287 */         ProcessInstance pi = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(hi.getId()).singleResult();
/* 288 */         Task task = (Task)this.taskService.createTaskQuery().processInstanceId(hi.getId()).singleResult();
/* 289 */         isSuspended = pi.isSuspended();
/* 290 */         activityName = StringUtils.trimToEmpty(task.getName());
/* 291 */         activityUser = StringUtils.trimToEmpty(task.getAssignee());
/*     */       }
/*     */ 
/* 295 */       ProcessDefinition processDefinition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().processDefinitionId(hi.getProcessDefinitionId()).singleResult();
/*     */ 
/* 297 */       rows.append("{'id':" + hi.getId() + ",'activityName':'" + activityName + "','activityUser':'" + activityUser + "','prcocessDefinitionName':'" + processDefinition.getName() + "','startUserId':'" + hi.getStartUserId() + "','starttime':'" + starttime + "','endtime':'" + endtime + "','spendTimes':'" + spendTimes + "','isSuspended':'" + isSuspended + "','processDefinitionId':'" + hi.getProcessDefinitionId() + "','processInstanceId':'" + hi.getId() + "'},");
/*     */     }
/*     */ 
/* 301 */     String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
/*     */ 
/* 303 */     JSONObject jObject = JSONObject.fromObject("{'total':" + historicProcessInstanceQuery.count() + ",'rows':[" + rowStr + "]}");
/* 304 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"suspend"})
/*     */   @ResponseBody
/*     */   public AjaxJson suspend(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request)
/*     */   {
/* 314 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 316 */     this.runtimeService.suspendProcessInstanceById(processInstanceId);
/*     */ 
/* 318 */     String message = "暂停成功";
/* 319 */     j.setMsg(message);
/* 320 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"restart"})
/*     */   @ResponseBody
/*     */   public AjaxJson restart(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request)
/*     */   {
/* 330 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 332 */     this.runtimeService.activateProcessInstanceById(processInstanceId);
/*     */ 
/* 334 */     String message = "启动成功";
/* 335 */     j.setMsg(message);
/* 336 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"close"})
/*     */   @ResponseBody
/*     */   public AjaxJson close(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request)
/*     */   {
/* 346 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 349 */     this.runtimeService.deleteProcessInstance(processInstanceId, "主动作废流程");
/*     */ 
/* 351 */     String message = "作废成功";
/* 352 */     j.setMsg(message);
/* 353 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"myRunningProcessList"})
/*     */   public String processListStartedByMe()
/*     */   {
/* 362 */     return "workflow/activiti/myRunningProcessList";
/*     */   }
/*     */ 
/*     */   public void responseDatagrid(HttpServletResponse response, JSONObject jObject)
/*     */   {
/* 376 */     response.setContentType("application/json");
/* 377 */     response.setHeader("Cache-Control", "no-store");
/*     */     try {
/* 379 */       PrintWriter pw = response.getWriter();
/* 380 */       pw.write(jObject.toString());
/* 381 */       pw.flush();
/*     */     } catch (IOException e) {
/* 383 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.controller.process.ProcessInstanceController
 * JD-Core Version:    0.6.0
 */