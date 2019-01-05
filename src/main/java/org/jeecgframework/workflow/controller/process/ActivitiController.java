/*     */ package org.jeecgframework.workflow.controller.process;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.activiti.engine.ActivitiException;
/*     */ import org.activiti.engine.HistoryService;
/*     */ import org.activiti.engine.IdentityService;
/*     */ import org.activiti.engine.ManagementService;
/*     */ import org.activiti.engine.ProcessEngine;
/*     */ import org.activiti.engine.ProcessEngines;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.history.HistoricProcessInstance;
/*     */ import org.activiti.engine.history.HistoricProcessInstanceQuery;
/*     */ import org.activiti.engine.history.HistoricTaskInstance;
/*     */ import org.activiti.engine.history.HistoricTaskInstanceQuery;
/*     */ import org.activiti.engine.identity.GroupQuery;
/*     */ import org.activiti.engine.identity.User;
/*     */ import org.activiti.engine.identity.UserQuery;
/*     */ import org.activiti.engine.impl.interceptor.Command;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.activiti.engine.repository.Deployment;
/*     */ import org.activiti.engine.repository.DeploymentBuilder;
/*     */ import org.activiti.engine.repository.DeploymentQuery;
/*     */ import org.activiti.engine.repository.ProcessDefinition;
/*     */ import org.activiti.engine.repository.ProcessDefinitionQuery;
/*     */ import org.activiti.engine.runtime.ProcessInstanceQuery;
/*     */ import org.activiti.engine.task.Task;
/*     */ import org.activiti.engine.task.TaskQuery;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.time.DateFormatUtils;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.PagerUtil;
/*     */ import org.jeecgframework.core.common.model.common.UploadFile;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*     */ import org.jeecgframework.core.util.MyClassLoader;
/*     */ import org.jeecgframework.core.util.ReflectHelper;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.jeecgframework.workflow.common.WorkFlowGlobals;
/*     */ import org.jeecgframework.workflow.model.activiti.ActivitiCom;
/*     */ import org.jeecgframework.workflow.model.activiti.ProcessHandle;
/*     */ import org.jeecgframework.workflow.model.activiti.Variable;
/*     */ import org.jeecgframework.workflow.model.diagram.HistoryProcessInstanceDiagramCmd;
/*     */ import org.jeecgframework.workflow.pojo.activiti.ActHiProcinst;
/*     */ import org.jeecgframework.workflow.pojo.activiti.ActIdUser;
/*     */ import org.jeecgframework.workflow.pojo.activiti.ActRuTask;
/*     */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*     */ import org.jeecgframework.workflow.pojo.base.TPProcess;
/*     */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*     */ import org.jeecgframework.workflow.pojo.base.TSBusConfig;
/*     */ import org.jeecgframework.workflow.pojo.base.TSPrjstatus;
/*     */ import org.jeecgframework.workflow.pojo.base.TSTable;
/*     */ import org.jeecgframework.workflow.service.ActivitiService;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import org.springframework.web.servlet.view.RedirectView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/activitiController"})
/*     */ public class ActivitiController
/*     */ {
/*  92 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*     */   protected RepositoryService repositoryService;
/*     */   protected RuntimeService runtimeService;
/*     */   private ActivitiService activitiService;
/*     */   protected TaskService taskService;
/*     */   protected IdentityService identityService;
/*     */   private SystemService systemService;
/*     */   private String message;
/* 100 */   private ModelAndView modelAndView = null;
/*     */   private HistoryService historyService;
/*     */ 
/*     */   @Autowired
/*     */   private ProcessEngine processEngine;
/*     */ 
/*     */   @Autowired
/*     */   public void setIdentityService(IdentityService identityService)
/*     */   {
/* 108 */     this.identityService = identityService;
/*     */   }
/*     */   @Autowired
/*     */   public void setTaskService(TaskService taskService) {
/* 113 */     this.taskService = taskService;
/*     */   }
/*     */   @Autowired
/*     */   public void setRepositoryService(RepositoryService repositoryService) {
/* 118 */     this.repositoryService = repositoryService;
/*     */   }
/*     */   @Autowired
/*     */   public void setRuntimeService(RuntimeService runtimeService) {
/* 123 */     this.runtimeService = runtimeService;
/*     */   }
/*     */   @Autowired
/*     */   public void setActivitiService(ActivitiService activitiService) {
/* 128 */     this.activitiService = activitiService;
/*     */   }
/*     */   @Autowired
/*     */   public void setSystemService(SystemService systemService) {
/* 133 */     this.systemService = systemService;
/*     */   }
/*     */   @Autowired
/*     */   public void setHistoryService(HistoryService historyService) {
/* 138 */     this.historyService = historyService;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"deploymentList"})
/*     */   public ModelAndView deploymentList()
/*     */   {
/* 148 */     return new ModelAndView("workflow/deployment/deploymentList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processDeploymentGrid"})
/*     */   public void processDeploymentGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 156 */     List objects = new ArrayList();
/* 157 */     List<ProcessDefinition> processDefinitionList = this.activitiService.processDefinitionList();
/* 158 */     for (ProcessDefinition processDefinition : processDefinitionList) {
/* 159 */       String deploymentId = processDefinition.getDeploymentId();
/* 160 */       Deployment deployment = (Deployment)this.repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
/* 161 */       objects.add(new Object[] { processDefinition, deployment });
/*     */     }
/* 163 */     dataGrid.setTotal(processDefinitionList.size());
/* 164 */     dataGrid.setResults(processDefinitionList);
/* 165 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"openDeployProcess"})
/*     */   public ModelAndView openDeployProcess()
/*     */   {
/* 177 */     return new ModelAndView("workflow/deployment/deploypro");
/*     */   }
/* 183 */   @RequestMapping(params={"deployProcess"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public AjaxJson deployProcess(HttpServletRequest request) { UploadFile uploadFile = new UploadFile(request);
/* 184 */     AjaxJson j = new AjaxJson();
/* 185 */     Map<String, MultipartFile>  fileMap = uploadFile.getMultipartRequest().getFileMap();
/* 186 */     for (Map.Entry entity : fileMap.entrySet()) {
/* 187 */       MultipartFile file = (MultipartFile)entity.getValue();
/* 188 */       String fileName = file.getOriginalFilename();
/*     */       try {
/* 190 */         InputStream fileInputStream = file.getInputStream();
/* 191 */         String extension = FilenameUtils.getExtension(fileName);
/* 192 */         if ((extension.equals("zip")) || (extension.equals("bar"))) {
/* 193 */           ZipInputStream zip = new ZipInputStream(fileInputStream);
/* 194 */           this.repositoryService.createDeployment().addZipInputStream(zip).deploy();
/* 195 */         } else if (extension.equals("png")) {
/* 196 */           this.repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
/* 197 */         } else if (extension.indexOf("bpmn20.xml") != -1) {
/* 198 */           this.repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
/* 199 */         } else if (extension.equals("bpmn"))
/*     */         {
/* 203 */           String baseName = FilenameUtils.getBaseName(fileName);
/* 204 */           this.repositoryService.createDeployment().addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
/*     */         } else {
/* 206 */           throw new ActivitiException("错误信息:不支改文件类型" + extension);
/*     */         }
/*     */       } catch (Exception e) {
/* 209 */         this.logger.error("错误信息:在部署过程中,文件输入流异常" + e.toString());
/*     */       }
/*     */     }
/*     */ 
/* 213 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"startBusProcess"})
/*     */   @ResponseBody
/*     */   public AjaxJson startBusProcess(HttpServletRequest request)
/*     */   {
/* 225 */     AjaxJson j = new AjaxJson();
/*     */     try {
/* 227 */       String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));
/* 228 */       String busconfigId = oConvertUtils.getString(request.getParameter("busconfigKey"));
/* 229 */       TSBusConfig tsBusbase = (TSBusConfig)this.systemService.getEntity(TSBusConfig.class, busconfigId);
/* 230 */       if (tsBusbase != null) {
/* 231 */         Class entityClass = MyClassLoader.getClassByScn(tsBusbase.getTSTable().getEntityName());
/* 232 */         Object objbus = this.systemService.getEntity(entityClass, businessKey);
/* 233 */         TSPrjstatus prjstatus = (TSPrjstatus)this.systemService.findUniqueByProperty(TSPrjstatus.class, "code", "doing");
/* 234 */         ReflectHelper reflectHelper = new ReflectHelper(objbus);
/* 235 */         TSPrjstatus busPrjstatus = (TSPrjstatus)reflectHelper.getMethodValue("TSPrjstatus");
/* 236 */         String objbusstate = busPrjstatus.getCode();
/* 237 */         if (!prjstatus.getCode().equals(objbusstate)) {
/* 238 */           Map variables = new HashMap();
/* 239 */           this.activitiService.startWorkflow(objbus, businessKey, variables, tsBusbase);
/* 240 */           reflectHelper.setMethodValue("TSPrjstatus", prjstatus);
/* 241 */           this.systemService.saveOrUpdate(objbus);
/* 242 */           this.message = "提交成功,已进入办理流程";
/*     */         } else {
/* 244 */           this.message = "已提交,正在办理中";
/*     */         }
/*     */       }
/* 247 */       else if (busconfigId.equals("undefined")) {
/* 248 */         this.message = "busconfigKey参数未设置,请在业务列表设置参数";
/*     */       } else {
/* 250 */         this.message = "流程未关联,请在流程配置中配置业务";
/*     */       }
/*     */     }
/*     */     catch (ActivitiException e)
/*     */     {
/* 255 */       if (e.getMessage().indexOf("no processes deployed with key") != -1) {
/* 256 */         this.message = "没有部署流程!,请在流程配置中部署流程";
/*     */       }
/*     */       else
/* 259 */         this.message = "启动流程失败!,内部错误";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 263 */       this.message = "启动流程失败!,内部错误";
/*     */     }
/* 265 */     j.setMsg(this.message);
/* 266 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"claim"})
/*     */   @ResponseBody
/*     */   public AjaxJson claim(HttpServletRequest request)
/*     */   {
/* 275 */     AjaxJson j = new AjaxJson();
/* 276 */     String userId = ResourceUtil.getSessionUserName().getUserName().toString();
/* 277 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 278 */     this.taskService.claim(taskId, userId);
/* 279 */     j.setMsg("签收完成");
/* 280 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goEntrust"})
/*     */   public ModelAndView goEntrust(HttpServletRequest request)
/*     */   {
/* 290 */     return new ModelAndView("business/demobus/entruster");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"goEntrustAdd"})
/*     */   public ModelAndView goEntrustAdd(HttpServletRequest request)
/*     */   {
/* 300 */     String taskId = request.getParameter("taskId");
/* 301 */     request.setAttribute("taskId", taskId);
/* 302 */     return new ModelAndView("business/demobus/entruster-add");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagridEntruster"})
/*     */   public void datagridEntruster(ActIdUser actIdUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 314 */     CriteriaQuery cq = new CriteriaQuery(ActIdUser.class, dataGrid);
/* 315 */     HqlGenerateUtil.installHql(cq, actIdUser, request.getParameterMap());
/* 316 */     this.systemService.getDataGridReturn(cq, true);
/* 317 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doEntrust"})
/*     */   @ResponseBody
/*     */   public AjaxJson doEntrust(ActIdUser actIdUser, HttpServletRequest request)
/*     */   {
/* 327 */     AjaxJson j = new AjaxJson();
/* 328 */     String message = "委托成功!";
/* 329 */     String taskId = request.getParameter("taskId");
/*     */     try {
/* 331 */       ActRuTask ruTask = (ActRuTask)this.systemService.get(ActRuTask.class, taskId);
/* 332 */       ruTask.setAssignee(actIdUser.getId());
/* 333 */       this.systemService.saveOrUpdate(ruTask);
/* 334 */       this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */     } catch (Exception e) {
/* 336 */       e.printStackTrace();
/* 337 */       message = "委托失败!";
/* 338 */       throw new BusinessException(e.getMessage());
/*     */     }
/*     */ 
/* 341 */     j.setMsg(message);
/* 342 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"openProcessPic"})
/*     */   public ModelAndView openProcessPic(HttpServletRequest request)
/*     */   {
/* 350 */     String tag = oConvertUtils.getString(request.getParameter("tag"));
/* 351 */     if (tag.equals("task")) {
/* 352 */       String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 353 */       Task task = this.activitiService.getTask(taskId);
/* 354 */       String processInstanceId = task.getProcessInstanceId();
/* 355 */       ActivityImpl activityImpl = this.activitiService.getProcessMap(processInstanceId);
/* 356 */       request.setAttribute("activityImpl", activityImpl);
/* 357 */       request.setAttribute("processInstanceId", processInstanceId);
/*     */     }
/* 359 */     else if (tag.equals("deployment")) {
/* 360 */       request.setAttribute("resourceName", oConvertUtils.getString(request.getParameter("resourceName")));
/* 361 */       request.setAttribute("deploymentId", oConvertUtils.getString(request.getParameter("deploymentId")));
/* 362 */     } else if (tag.equals("project")) {
/* 363 */       String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));
/* 364 */       HistoricProcessInstance historicProcessInstance = this.activitiService.getHiProcInstByBusKey(businessKey);
/* 365 */       String processInstanceId = historicProcessInstance.getId();
/* 366 */       ActivityImpl activityImpl = this.activitiService.getProcessMap(processInstanceId);
/* 367 */       request.setAttribute("activityImpl", activityImpl);
/* 368 */       request.setAttribute("processInstanceId", processInstanceId);
/*     */     }
/* 370 */     request.setAttribute("tag", tag);
/* 371 */     return new ModelAndView("workflow/process/processPic");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processPic"})
/*     */   public void processPic(HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 386 */     response.setContentType("UTF-8");
/* 387 */     response.setCharacterEncoding("UTF-8");
/* 388 */     String processInstanceId = oConvertUtils.getString(request.getParameter("processInstanceId"));
/* 389 */     Command cmd = new HistoryProcessInstanceDiagramCmd(processInstanceId);
/* 390 */     ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
/* 391 */     InputStream is = (InputStream)processEngine.getManagementService().executeCommand(cmd);
/*     */ 
/* 393 */     int len = 0;
/* 394 */     byte[] b = new byte[1024];
/*     */ 
/* 396 */     while ((len = is.read(b, 0, 1024)) != -1)
/* 397 */       response.getOutputStream().write(b, 0, len);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processPicByDeploy"})
/*     */   public void processPicByDeploy(HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 413 */     String deploymentId = oConvertUtils.getString(request.getParameter("deploymentId"));
/* 414 */     String resourceName = oConvertUtils.getString(request.getParameter("resourceName"));
/* 415 */     InputStream resourceAsStream = this.repositoryService.getResourceAsStream(deploymentId, resourceName);
/* 416 */     byte[] b = new byte[1024];
/* 417 */     int len = -1;
/* 418 */     while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
/* 419 */       response.getOutputStream().write(b, 0, len);
/*     */     }
/* 421 */     response.getOutputStream().flush();
/* 422 */     response.getOutputStream().close();
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskList"})
/*     */   public void taskList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 431 */     TSUser user = ResourceUtil.getSessionUserName();
/* 432 */     String buscode = oConvertUtils.getString(request.getParameter("busCode"));
/* 433 */     TSTable table = (TSTable)this.systemService.findUniqueByProperty(TSTable.class, "entityKey", buscode);
/* 434 */     if (table != null) {
/* 435 */       List tsBusConfigs = this.systemService.findByProperty(TSBusConfig.class, "TSTable.id", table.getId());
/* 436 */       List taskList = this.activitiService.findTodoTasks(user.getUserName(), tsBusConfigs);
/* 437 */       dataGrid.setTotal(taskList.size());
/* 438 */       dataGrid.setResults(taskList);
/* 439 */       TagUtil.datagrid(response, dataGrid);
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"openProcessHandle"})
/*     */   public ModelAndView openProcessHandle(HttpServletRequest request)
/*     */   {
/* 449 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 450 */     ProcessHandle processHandle = this.activitiService.getProcessHandle(taskId);
/* 451 */     if (processHandle.getTpForm() != null) {
/* 452 */       TPForm tForm = processHandle.getTpForm();
/*     */ 
/* 454 */       List formpros = tForm.getTPFormpros();
/* 455 */       request.setAttribute("formpros", formpros);
/* 456 */       request.setAttribute("action", tForm.getFormaction());
/* 457 */       this.modelAndView = new ModelAndView("workflow/processHandle/dynamicHandle");
/*     */     } else {
/* 459 */       String modelandview = oConvertUtils.getString(processHandle.getTpProcessnode().getModelandview(), "activitiController.do?processHandle");
/* 460 */       this.modelAndView = new ModelAndView(new RedirectView(modelandview), "taskId", taskId);
/*     */     }
/* 462 */     return this.modelAndView;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processHandle"})
/*     */   public ModelAndView processHandle(HttpServletRequest request)
/*     */   {
/* 470 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 471 */     request.setAttribute("taskId", taskId);
/* 472 */     return new ModelAndView("workflow/processhandle/processHandle");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"processComplete"})
/*     */   @ResponseBody
/*     */   public AjaxJson processComplete(HttpServletRequest request, Variable var)
/*     */   {
/* 486 */     AjaxJson j = new AjaxJson();
/* 487 */     String taskId = oConvertUtils.getString(request.getParameter("taskId"));
/* 488 */     ProcessHandle processHandle = this.activitiService.getProcessHandle(taskId);
/* 489 */     Map map = var.getVariableMap(processHandle.getTpProcesspros());
/* 490 */     ActivitiCom complete = this.activitiService.complete(taskId, map);
/* 491 */     if (complete.getComplete().booleanValue())
/* 492 */       j.setMsg(complete.getMsg());
/*     */     else {
/* 494 */       j.setMsg(complete.getMsg());
/*     */     }
/* 496 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"deleteProcess"})
/*     */   @ResponseBody
/*     */   public AjaxJson deleteProcess(HttpServletRequest request)
/*     */   {
/* 507 */     AjaxJson j = new AjaxJson();
/* 508 */     String deploymentId = oConvertUtils.getString(request.getParameter("deploymentId"));
/* 509 */     String key = oConvertUtils.getString(request.getParameter("key"));
/* 510 */     TPProcess process = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", key);
/* 511 */     ProcessDefinition processDefinition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).processDefinitionKey(key).singleResult();
/* 512 */     List actHiProcinsts = this.systemService.findByProperty(ActHiProcinst.class, "procDefId", processDefinition.getId());
/* 513 */     if (actHiProcinsts.size() <= 0) {
/* 514 */       this.repositoryService.deleteDeployment(deploymentId, true);
/* 515 */       process.setProcessstate(WorkFlowGlobals.Process_Deploy_NO);
/* 516 */       this.systemService.updateEntitie(process);
/* 517 */       this.message = "流程删除成功";
/*     */     } else {
/* 519 */       this.message = "流程跟业务已关联无法删除";
/*     */     }
/* 521 */     j.setMsg(this.message);
/* 522 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"setProcessState"})
/*     */   @ResponseBody
/*     */   public AjaxJson setProcessState(HttpServletRequest request)
/*     */   {
/* 531 */     AjaxJson j = new AjaxJson();
/* 532 */     String state = oConvertUtils.getString(request.getParameter("state"));
/* 533 */     String processDefinitionId = oConvertUtils.getString(request.getParameter("processDefinitionId"));
/* 534 */     if (state.equals("active")) {
/* 535 */       this.repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
/* 536 */       this.message = "流程已激活";
/*     */     } else {
/* 538 */       this.repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
/* 539 */       this.message = "流程已挂起";
/*     */     }
/* 541 */     j.setMsg(this.message);
/* 542 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getUsers"})
/*     */   @ResponseBody
/*     */   public void getUsers(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 554 */     CriteriaQuery cq = new CriteriaQuery(User.class, dataGrid);
/* 555 */     List userList = this.identityService.createUserQuery().list();
/* 556 */     dataGrid.setTotal(userList.size());
/* 557 */     dataGrid.setResults(userList);
/* 558 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getGroups"})
/*     */   @ResponseBody
/*     */   public void getGroups(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 570 */     List groupList = this.identityService.createGroupQuery().list();
/* 571 */     dataGrid.setTotal(groupList.size());
/* 572 */     dataGrid.setResults(groupList);
/* 573 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskRunningList"})
/*     */   public ModelAndView taskRunningList(HttpServletRequest request)
/*     */   {
/* 581 */     return new ModelAndView("business/demobus/taskRunningList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskRunningGrid"})
/*     */   public void taskRunningGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 589 */     int allCounts = (int)this.runtimeService.createProcessInstanceQuery().count();
/* 590 */     int pageSize = dataGrid.getRows();
/* 591 */     int curPageNO = PagerUtil.getcurPageNo(allCounts, dataGrid.getPage(), 
/* 592 */       pageSize);
/* 593 */     int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
/* 594 */     List list = this.runtimeService.createProcessInstanceQuery().listPage(offset, pageSize);
/* 595 */     dataGrid.setResults(list);
/* 596 */     dataGrid.setTotal(list.size());
/* 597 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskFinishedList"})
/*     */   public ModelAndView taskFinishedList(HttpServletRequest request)
/*     */   {
/* 605 */     return new ModelAndView("business/demobus/taskFinishedList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskFinishedGrid"})
/*     */   public void taskFinishedGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 613 */     int allCounts = (int)this.historyService.createHistoricProcessInstanceQuery().count();
/* 614 */     int pageSize = dataGrid.getRows();
/* 615 */     int curPageNO = PagerUtil.getcurPageNo(allCounts, dataGrid.getPage(), 
/* 616 */       pageSize);
/* 617 */     int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
/* 618 */     List list = this.historyService.createHistoricProcessInstanceQuery().finished().listPage(offset, pageSize);
/* 619 */     dataGrid.setResults(list);
/* 620 */     dataGrid.setTotal(allCounts);
/* 621 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"traceImage"})
/*     */   public void traceImage(@RequestParam("processInstanceId") String processInstanceId, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 632 */     Command cmd = new HistoryProcessInstanceDiagramCmd(processInstanceId);
/* 633 */     ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
/* 634 */     InputStream is = (InputStream)processEngine.getManagementService().executeCommand(cmd);
/*     */ 
/* 636 */     int len = 0;
/* 637 */     byte[] b = new byte[1024];
/*     */ 
/* 639 */     while ((len = is.read(b, 0, 1024)) != -1)
/* 640 */       response.getOutputStream().write(b, 0, len);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"viewProcessInstanceHistory"})
/*     */   public ModelAndView viewProcessInstanceHistory(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request, HttpServletResponse respone, Model model)
/*     */   {
/* 655 */     model.addAttribute("processInstanceId", processInstanceId);
/*     */ 
/* 657 */     return new ModelAndView("workflow/activiti/viewProcessInstanceHistory");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"taskHistoryList"})
/*     */   public void taskHistoryList(@RequestParam("processInstanceId") String processInstanceId, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 671 */     List<HistoricTaskInstance> historicTasks = this.historyService
/* 672 */       .createHistoricTaskInstanceQuery()
/* 673 */       .processInstanceId(processInstanceId).list();
/*     */ 
/* 675 */     StringBuffer rows = new StringBuffer();
/* 676 */     for (HistoricTaskInstance hi : historicTasks) {
/* 677 */       String starttime = hi.getStartTime() == null ? "" : DateFormatUtils.format(hi.getStartTime(), "yyyy-MM-dd HH:mm:ss");
/* 678 */       String endtime = hi.getEndTime() == null ? "" : DateFormatUtils.format(hi.getEndTime(), "yyyy-MM-dd HH:mm:ss");
/* 679 */       rows.append("{'name':'" + hi.getName() + "','processInstanceId':'" + hi.getProcessInstanceId() + "','startTime':'" + starttime + "','endTime':'" + endtime + "','assignee':'" + StringUtils.trimToEmpty(hi.getAssignee()) + "','deleteReason':'" + StringUtils.trimToEmpty(hi.getDeleteReason()) + "'},");
/*     */     }
/*     */ 
/* 683 */     String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
/*     */ 
/* 685 */     JSONObject jObject = JSONObject.fromObject("{'total':" + historicTasks.size() + ",'rows':[" + rowStr + "]}");
/* 686 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   public void responseDatagrid(HttpServletResponse response, JSONObject jObject)
/*     */   {
/* 693 */     response.setContentType("application/json");
/* 694 */     response.setHeader("Cache-Control", "no-store");
/*     */     try {
/* 696 */       PrintWriter pw = response.getWriter();
/* 697 */       pw.write(jObject.toString());
/* 698 */       pw.flush();
/*     */     } catch (IOException e) {
/* 700 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"waitingClaimTask"})
/*     */   public ModelAndView waitingClaimTask()
/*     */   {
/* 714 */     return new ModelAndView("workflow/activiti/waitingClaimTask");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"waitingClaimTaskDataGrid"})
/*     */   public void waitingClaimTaskDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 726 */     String userId = ResourceUtil.getSessionUserName().getUserName();
/* 727 */     TaskService taskService = this.processEngine.getTaskService();
/* 728 */     List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userId).active().list();
/*     */ 
/* 730 */     StringBuffer rows = new StringBuffer();
/* 731 */     for (Task t : tasks) {
/* 732 */       rows.append("{'name':'" + t.getName() + "','taskId':'" + t.getId() + "','processDefinitionId':'" + t.getProcessDefinitionId() + "'},");
/*     */     }
/* 734 */     String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
/*     */ 
/* 736 */     JSONObject jObject = JSONObject.fromObject("{'total':" + tasks.size() + ",'rows':[" + rowStr + "]}");
/* 737 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"claimedTask"})
/*     */   public ModelAndView claimedTask()
/*     */   {
/* 749 */     return new ModelAndView("workflow/activiti/claimedTask");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"claimedTaskDataGrid"})
/*     */   public void claimedTaskDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 761 */     String userId = ResourceUtil.getSessionUserName().getUserName();
/* 762 */     TaskService taskService = this.processEngine.getTaskService();
/* 763 */     List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
/*     */ 
/* 765 */     StringBuffer rows = new StringBuffer();
/* 766 */     for (Task t : tasks) {
/* 767 */       rows.append("{'name':'" + t.getName() + "','description':'" + t.getDescription() + "','taskId':'" + t.getId() + "','processDefinitionId':'" + t.getProcessDefinitionId() + "','processInstanceId':'" + t.getProcessInstanceId() + "'},");
/*     */     }
/* 769 */     String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
/*     */ 
/* 771 */     JSONObject jObject = JSONObject.fromObject("{'total':" + tasks.size() + ",'rows':[" + rowStr + "]}");
/* 772 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"finishedTask"})
/*     */   public ModelAndView finishedTask()
/*     */   {
/* 783 */     return new ModelAndView("workflow/activiti/finishedTask");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"finishedTaskDataGrid"})
/*     */   public void finishedTask(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 795 */     String userId = ResourceUtil.getSessionUserName().getUserName();
/* 796 */     List<HistoricTaskInstance> historicTasks = this.historyService
/* 797 */       .createHistoricTaskInstanceQuery().taskAssignee(userId)
/* 798 */       .finished().list();
/*     */ 
/* 800 */     StringBuffer rows = new StringBuffer();
/* 801 */     for (HistoricTaskInstance t : historicTasks) {
/* 802 */       rows.append("{'name':'" + t.getName() + "','description':'" + t.getDescription() + "','taskId':'" + t.getId() + "','processDefinitionId':'" + t.getProcessDefinitionId() + "','processInstanceId':'" + t.getProcessInstanceId() + "'},");
/*     */     }
/* 804 */     String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
/*     */ 
/* 806 */     JSONObject jObject = JSONObject.fromObject("{'total':" + historicTasks.size() + ",'rows':[" + rowStr + "]}");
/* 807 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"claimTask"})
/*     */   @ResponseBody
/*     */   public AjaxJson claimTask(@RequestParam("taskId") String taskId, HttpServletRequest request)
/*     */   {
/* 817 */     AjaxJson j = new AjaxJson();
/*     */ 
/* 819 */     String userId = ResourceUtil.getSessionUserName().getUserName();
/*     */ 
/* 821 */     TaskService taskService = this.processEngine.getTaskService();
/* 822 */     taskService.claim(taskId, userId);
/*     */ 
/* 824 */     String message = "签收成功";
/* 825 */     j.setMsg(message);
/* 826 */     return j;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.controller.process.ActivitiController
 * JD-Core Version:    0.6.0
 */