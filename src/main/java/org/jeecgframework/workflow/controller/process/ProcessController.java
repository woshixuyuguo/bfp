/*      */ package org.jeecgframework.workflow.controller.process;
/*      */ 
/*      */ import java.io.PrintWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import org.activiti.engine.RepositoryService;
/*      */ import org.activiti.engine.repository.DeploymentBuilder;
/*      */ import org.apache.log4j.Logger;
/*      */ import org.jeecgframework.core.common.controller.BaseController;
/*      */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*      */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*      */ import org.jeecgframework.core.common.model.json.ComboBox;
/*      */ import org.jeecgframework.core.common.model.json.ComboTree;
/*      */ import org.jeecgframework.core.common.model.json.DataGrid;
/*      */ import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
/*      */ import org.jeecgframework.core.util.ResourceUtil;
/*      */ import org.jeecgframework.core.util.StreamUtils;
/*      */ import org.jeecgframework.core.util.StringUtil;
/*      */ import org.jeecgframework.core.util.oConvertUtils;
/*      */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*      */ import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
/*      */ import org.jeecgframework.web.system.pojo.base.TSType;
/*      */ import org.jeecgframework.web.system.pojo.base.TSTypegroup;
/*      */ import org.jeecgframework.web.system.service.SystemService;
/*      */ import org.jeecgframework.web.system.service.UserService;
/*      */ import org.jeecgframework.workflow.common.WorkFlowGlobals;
/*      */ import org.jeecgframework.workflow.pojo.base.TPForm;
/*      */ import org.jeecgframework.workflow.pojo.base.TPFormpro;
/*      */ import org.jeecgframework.workflow.pojo.base.TPListerer;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcess;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcessListener;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcessnode;
/*      */ import org.jeecgframework.workflow.pojo.base.TPProcesspro;
/*      */ import org.jeecgframework.workflow.pojo.base.TSBusConfig;
/*      */ import org.jeecgframework.workflow.pojo.base.TSTable;
/*      */ import org.jeecgframework.workflow.service.ActivitiService;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.stereotype.Controller;
/*      */ import org.springframework.web.bind.annotation.RequestMapping;
/*      */ import org.springframework.web.bind.annotation.ResponseBody;
/*      */ import org.springframework.web.servlet.ModelAndView;
/*      */ 
/*      */ @Controller
/*      */ @RequestMapping({"/processController"})
/*      */ public class ProcessController extends BaseController
/*      */ {
/*   58 */   private static final Logger logger = Logger.getLogger(ProcessController.class);
/*      */   private UserService userService;
/*      */   private SystemService systemService;
/*      */   private String message;
/*      */   private ActivitiService activitiService;
/*      */   protected RepositoryService repositoryService;
/*      */ 
/*      */   public String getMessage()
/*      */   {
/*   66 */     return this.message;
/*      */   }
/*      */ 
/*      */   public void setMessage(String message) {
/*   70 */     this.message = message;
/*      */   }
/*      */   @Autowired
/*      */   public void setActivitiService(ActivitiService activitiService) {
/*   75 */     this.activitiService = activitiService;
/*      */   }
/*      */   @Autowired
/*      */   public void setSystemService(SystemService systemService) {
/*   80 */     this.systemService = systemService;
/*      */   }
/*      */ 
/*      */   public UserService getUserService() {
/*   84 */     return this.userService;
/*      */   }
/*      */   @Autowired
/*      */   public void setUserService(UserService userService) {
/*   89 */     this.userService = userService;
/*      */   }
/*      */   @Autowired
/*      */   public void setRepositoryService(RepositoryService repositoryService) {
/*   94 */     this.repositoryService = repositoryService;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processDesigner"})
/*      */   public ModelAndView processDesigner(HttpServletRequest request)
/*      */   {
/*  104 */     String processid = oConvertUtils.getString(request.getParameter("id"), "0");
/*  105 */     request.setAttribute("processid", processid);
/*  106 */     return new ModelAndView("designer/index");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processProperties"})
/*      */   public ModelAndView processProperties(HttpServletRequest request)
/*      */   {
/*  117 */     String turn = oConvertUtils.getString(request.getParameter("turn"));
/*  118 */     String id = oConvertUtils.getString(request.getParameter("id"));
/*  119 */     String checkbox = oConvertUtils.getString(request.getParameter("checkbox"));
/*  120 */     String processId = oConvertUtils.getString(request.getParameter("processId"));
/*  121 */     TPProcess tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "id", processId);
/*  122 */     if (tProcess != null) {
/*  123 */       request.setAttribute("processDefinitionId", tProcess.getId());
/*  124 */       if (tProcess.getTSType() != null) {
/*  125 */         request.setAttribute("typeId", tProcess.getTSType().getId());
/*      */       }
/*      */     }
/*      */ 
/*  129 */     TSTypegroup typegroup = (TSTypegroup)this.systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", "process");
/*  130 */     List proTypeList = this.systemService.findByProperty(TSType.class, "TSTypegroup.id", typegroup.getId());
/*  131 */     request.setAttribute("checkbox", checkbox);
/*  132 */     request.setAttribute("id", id);
/*  133 */     request.setAttribute("proTypeList", proTypeList);
/*  134 */     request.setAttribute("processId", processId);
/*  135 */     return new ModelAndView("designer/" + turn);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processIframe"})
/*      */   public ModelAndView processIframe(HttpServletRequest request)
/*      */   {
/*  145 */     String typeid = request.getParameter("typeid");
/*  146 */     request.setAttribute("typeid", typeid);
/*  147 */     List typegroupList = this.systemService.findByProperty(TSTypegroup.class, "typegroupcode", "process");
/*  148 */     request.setAttribute("typegroupList", typegroupList);
/*  149 */     return new ModelAndView("workflow/process/processIframe");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processComboBox"})
/*      */   @ResponseBody
/*      */   public List<ComboBox> processComboBox(HttpServletResponse response, HttpServletRequest request)
/*      */   {
/*  160 */     ComboBox comboBox = new ComboBox();
/*  161 */     comboBox.setId("typecode");
/*  162 */     comboBox.setText("typename");
/*  163 */     List comboBoxs = new ArrayList();
/*  164 */     TSTypegroup typegroup = (TSTypegroup)this.systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", "process");
/*  165 */     List proTypeList = this.systemService.findByProperty(TSType.class, "TSTypegroup.id", typegroup.getId());
/*  166 */     comboBoxs = TagUtil.getComboBox(proTypeList, null, comboBox);
/*  167 */     return comboBoxs;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processTypeTree"})
/*      */   @ResponseBody
/*      */   public List<ComboTree> processTypeTree(HttpServletRequest request, ComboTree comboTree)
/*      */   {
/*  176 */     CriteriaQuery cq = new CriteriaQuery(TSType.class);
/*  177 */     if (comboTree.getId() != null) {
/*  178 */       cq.eq("TSType.id", comboTree.getId());
/*      */     }
/*  180 */     if (comboTree.getId() == null) {
/*  181 */       cq.isNull("TSType");
/*      */     }
/*      */ 
/*  184 */     cq.add();
/*  185 */     List typeList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
/*  186 */     List comboTrees = new ArrayList();
/*  187 */     ComboTreeModel comboTreeModel = new ComboTreeModel("id", "typename", "TSTypes", "typecode");
/*  188 */     comboTrees = this.systemService.ComboTree(typeList, comboTreeModel, null,true);
/*  189 */     return comboTrees;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processList"})
/*      */   public ModelAndView processList(HttpServletRequest request)
/*      */   {
/*  199 */     String typeid = request.getParameter("typeid");
/*  200 */     request.setAttribute("typeid", typeid);
/*  201 */     return new ModelAndView("workflow/process/processList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getProcessXml"})
/*      */   @ResponseBody
/*      */   public void getProcessXml(HttpServletRequest request, HttpServletResponse response)
/*      */   {
/*  213 */     response.setContentType("text/xml;charset=UTF-8");
/*  214 */     String processId = oConvertUtils.getString(request.getParameter("processId"));
/*  215 */     TPProcess tProcess = (TPProcess)this.systemService.getEntity(TPProcess.class, processId);
/*      */     try
/*      */     {
/*  218 */       String retstr = StreamUtils.InputStreamTOString(StreamUtils.byteTOInputStream(tProcess.getProcessxml()));
/*  219 */       response.getWriter().write(retstr);
/*      */     } catch (Exception e1) {
/*  221 */       e1.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveProcess"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveProcess(HttpServletRequest request)
/*      */   {
/*  231 */     AjaxJson j = new AjaxJson();
/*  232 */     String processDefinitionId = oConvertUtils.getString(request.getParameter("processDefinitionId"));
/*  233 */     String processDescriptor = oConvertUtils.getString(request.getParameter("processDescriptor"));
/*  234 */     String processName = oConvertUtils.getString(request.getParameter("processName"));
/*  235 */     String processkey = oConvertUtils.getString(request.getParameter("processkey"));
/*  236 */     String params = oConvertUtils.getString(request.getParameter("params"));
/*  237 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/*  238 */     String nodes = oConvertUtils.getString(request.getParameter("nodes"));
/*  239 */     TSType tsType = (TSType)this.systemService.getEntity(TSType.class, typeid);
/*  240 */     TPProcess tProcess = (TPProcess)this.systemService.getEntity(TPProcess.class, processDefinitionId);
/*  241 */     if (tProcess != null) {
/*  242 */       tProcess.setProcessname(processName);
/*  243 */       tProcess.setTSType(tsType);
/*  244 */       tProcess.setProcesskey(processkey);
/*  245 */       if (!processDescriptor.equals("")) {
/*  246 */         tProcess.setProcessxml(StreamUtils.StringTObyte(processDescriptor));
/*      */       }
/*  248 */       this.systemService.updateEntitie(tProcess);
/*  249 */       j.setMsg("流程修改成功");
/*      */     } else {
/*  251 */       List processes = this.systemService.findByProperty(TPProcess.class, "processkey", processkey);
/*  252 */       if (processes.size() == 0) {
/*  253 */         tProcess = new TPProcess();
/*  254 */         tProcess.setProcessname(processName);
/*  255 */         tProcess.setProcessstate(WorkFlowGlobals.Process_Deploy_NO);
/*  256 */         tProcess.setTSType(tsType);
/*  257 */         tProcess.setProcesskey(processkey);
/*  258 */         if (!processDescriptor.equals("")) {
/*  259 */           tProcess.setProcessxml(StreamUtils.StringTObyte(processDescriptor));
/*      */         }
/*  261 */         this.systemService.save(tProcess);
/*  262 */         j.setMsg("流程创建成功");
/*      */       } else {
/*  264 */         j.setMsg("流程ID已存在");
/*      */       }
/*      */     }
/*  267 */     if ((nodes != null) && (nodes.length() > 3)) {
/*  268 */       String[] temp = nodes.split("@@@");
/*  269 */       for (int i = 0; i < temp.length; i++) {
/*  270 */         TPProcessnode tProcessnode = null;
/*  271 */         String[] fileds = temp[i].split("###");
/*  272 */         String tid = fileds[0].substring(3);
/*  273 */         String name = fileds[1].substring(9);
/*  274 */         tProcessnode = this.activitiService.getTPProcessnode(tid, processkey);
/*  275 */         if (tProcessnode == null) {
/*  276 */           tProcessnode = new TPProcessnode();
/*  277 */           tProcessnode.setProcessnodecode(tid);
/*  278 */           tProcessnode.setProcessnodename(name);
/*  279 */           tProcessnode.setTPProcess(tProcess);
/*  280 */           tProcessnode.setTPForm(null);
/*  281 */           this.systemService.save(tProcessnode);
/*      */         } else {
/*  283 */           tProcessnode.setProcessnodecode(tid);
/*  284 */           tProcessnode.setProcessnodename(name);
/*  285 */           tProcessnode.setTPProcess(tProcess);
/*  286 */           tProcessnode.setTPForm(null);
/*  287 */           this.systemService.updateEntitie(tProcessnode);
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  293 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addOrupdateVariable"})
/*      */   public ModelAndView addOrupdateVariable(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  305 */     TPProcessnode processnode = null;
/*  306 */     String processproid = ResourceUtil.getParameter("processproid");
/*  307 */     String processId = ResourceUtil.getParameter("processId");
/*  308 */     String processNode = request.getParameter("processNode");
/*  309 */     String processDefinitionId = request.getParameter("processDefinitionId");
/*  310 */     request.setAttribute("processid", processId);
/*  311 */     if (processpro.getId() != null) {
/*  312 */       processpro = (TPProcesspro)this.systemService.getEntity(TPProcesspro.class, processpro.getId());
/*  313 */       processnode = processpro.getTPProcessnode();
/*  314 */       request.setAttribute("processpro", processpro);
/*  315 */       request.setAttribute("processnode", processnode);
/*      */     }
/*  317 */     request.setAttribute("processId", processId);
/*  318 */     request.setAttribute("processNode", processNode);
/*  319 */     request.setAttribute("processDefinitionId", processDefinitionId);
/*  320 */     return new ModelAndView("designer/processpro");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveVariable"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveVariable(TPProcesspro tProcesspro, HttpServletRequest request)
/*      */   {
/*  330 */     AjaxJson j = new AjaxJson();
/*  331 */     String processproId = ResourceUtil.getParameter("processproid");
/*  332 */     String processId = ResourceUtil.getParameter("processId");
/*  333 */     String processNode = ResourceUtil.getParameter("procesnode");
/*  334 */     String processDefinitionId = ResourceUtil.getParameter("processDefinitionId");
/*  335 */     TPProcess tProcess = null;
/*  336 */     TPProcessnode tProcessnode = null;
/*  337 */     if (StringUtil.isNotEmpty(processDefinitionId)) {
/*  338 */       tProcess = (TPProcess)this.systemService.getEntity(TPProcess.class, oConvertUtils.getString(processDefinitionId));
/*      */     }
/*  340 */     else if (StringUtil.isNotEmpty(processId)) {
/*  341 */       tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", processId);
/*  342 */       if (tProcess == null) {
/*  343 */         tProcess = new TPProcess();
/*  344 */         tProcess.setProcesskey(processId);
/*  345 */         this.systemService.save(tProcess);
/*      */       } else {
/*  347 */         this.systemService.updateEntitie(tProcess);
/*      */       }
/*      */     }
/*      */ 
/*  351 */     if (StringUtil.isNotEmpty(processNode)) {
/*  352 */       tProcessnode = (TPProcessnode)this.systemService.findUniqueByProperty(TPProcessnode.class, "processnodecode", processNode);
/*  353 */       if (tProcessnode == null) {
/*  354 */         tProcessnode = new TPProcessnode();
/*  355 */         tProcessnode.setTPProcess(tProcess);
/*  356 */         tProcessnode.setTPForm(null);
/*  357 */         tProcessnode.setProcessnodecode(processNode);
/*  358 */         this.systemService.save(tProcessnode);
/*      */       } else {
/*  360 */         tProcessnode.setTPProcess(tProcess);
/*  361 */         tProcessnode.setTPForm(null);
/*  362 */         tProcessnode.setProcessnodecode(processNode);
/*  363 */         this.systemService.updateEntitie(tProcessnode);
/*      */       }
/*      */     }
/*      */ 
/*  367 */     if (StringUtil.isNotEmpty(processproId)) {
/*  368 */       tProcesspro.setTPProcess(tProcess);
/*  369 */       this.systemService.updateEntitie(tProcesspro);
/*      */     } else {
/*  371 */       tProcesspro.setTPProcess(tProcess);
/*  372 */       tProcesspro.setTPProcessnode(tProcessnode);
/*  373 */       this.systemService.save(tProcesspro);
/*      */     }
/*  375 */     j.setMsg("变量保存成功!");
/*      */ 
/*  377 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"deleteVariable"})
/*      */   @ResponseBody
/*      */   public AjaxJson deleteVariable(HttpServletRequest request)
/*      */   {
/*  389 */     AjaxJson j = new AjaxJson();
/*  390 */     String variableId = oConvertUtils.getString(request.getParameter("variableId"));
/*  391 */     this.systemService.deleteEntityById(TPProcesspro.class, variableId);
/*  392 */     j.setMsg("变量删除成功!");
/*      */ 
/*  394 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getVariables"})
/*      */   @ResponseBody
/*      */   public void getVariables(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  406 */     String processNode = oConvertUtils.getString(request.getParameter("processNode"));
/*  407 */     String processId = oConvertUtils.getString(request.getParameter("processId"));
/*  408 */     TPProcess tProcess = null;
/*  409 */     if (StringUtil.isNotEmpty(processId)) {
/*  410 */       tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", processId);
/*      */     }
/*  412 */     if (tProcess != null) {
/*  413 */       CriteriaQuery cq = new CriteriaQuery(TPProcesspro.class, dataGrid);
/*  414 */       cq.createAlias("TPProcessnode", "TPProcessnode");
/*  415 */       cq.eq("TPProcessnode.processnodecode", processNode);
/*  416 */       cq.eq("TPProcess.id", tProcess.getId());
/*  417 */       cq.add();
/*  418 */       this.systemService.getDataGridReturn(cq, true);
/*  419 */       TagUtil.datagrid(response, dataGrid);
/*      */     }
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getVariable"})
/*      */   @ResponseBody
/*      */   public void getVariable(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  432 */     String variableId = oConvertUtils.getString(request.getParameter("variableId"));
/*  433 */     CriteriaQuery cq = new CriteriaQuery(TPProcesspro.class, dataGrid);
/*  434 */     cq.eq("processproid", variableId);
/*  435 */     cq.add();
/*  436 */     this.systemService.getDataGridReturn(cq, true);
/*  437 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processTabs"})
/*      */   public ModelAndView processTabs(HttpServletRequest request)
/*      */   {
/*  447 */     String processid = request.getParameter("processid");
/*  448 */     request.setAttribute("processid", processid);
/*  449 */     return new ModelAndView("workflow/process/processTabs");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processpro"})
/*      */   public ModelAndView processpro(HttpServletRequest request)
/*      */   {
/*  459 */     String processid = request.getParameter("processid");
/*  460 */     request.setAttribute("processid", processid);
/*  461 */     return new ModelAndView("workflow/process/processproList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"busbase"})
/*      */   public ModelAndView busbase(HttpServletRequest request)
/*      */   {
/*  471 */     String processid = request.getParameter("processid");
/*  472 */     request.setAttribute("processid", processid);
/*  473 */     return new ModelAndView("workflow/process/busbaseList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processtype"})
/*      */   public ModelAndView processtype()
/*      */   {
/*  483 */     return new ModelAndView("workflow/process/processtypeList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processnode"})
/*      */   public ModelAndView processnode(HttpServletRequest request)
/*      */   {
/*  493 */     String processid = request.getParameter("processid");
/*  494 */     request.setAttribute("processid", processid);
/*  495 */     return new ModelAndView("workflow/processnode/processnodeList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processGrid"})
/*      */   public void processGrid(TPProcess tPProcess, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  504 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/*  505 */     CriteriaQuery cq = new CriteriaQuery(TPProcess.class, dataGrid);
/*  506 */     HqlGenerateUtil.installHql(cq, tPProcess, request.getParameterMap());
/*  507 */     if (StringUtil.isNotEmpty(typeid)) {
/*  508 */       cq.eq("TSType.id", typeid);
/*  509 */       cq.add();
/*      */     }
/*  511 */     this.systemService.getDataGridReturn(cq, true);
/*  512 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"processproList"})
/*      */   public void processproList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  521 */     String processid = request.getParameter("processid");
/*  522 */     CriteriaQuery cq = new CriteriaQuery(TPProcesspro.class, dataGrid);
/*  523 */     if (StringUtil.isNotEmpty(processid)) {
/*  524 */       cq.eq("TPProcess.id", processid);
/*  525 */       cq.add();
/*      */     }
/*  527 */     this.systemService.getDataGridReturn(cq, true);
/*  528 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridBus"})
/*      */   public void datagridBus(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  542 */     String processid = request.getParameter("processid");
/*  543 */     CriteriaQuery cq = new CriteriaQuery(TSBusConfig.class, dataGrid);
/*  544 */     if (StringUtil.isNotEmpty(processid)) {
/*  545 */       cq.eq("TPProcess.id", processid);
/*  546 */       cq.add();
/*      */     }
/*  548 */     this.systemService.getDataGridReturn(cq, true);
/*  549 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridtype"})
/*      */   public void datagridtype(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  563 */     CriteriaQuery cq = new CriteriaQuery(TSType.class, dataGrid);
/*  564 */     this.systemService.getDataGridReturn(cq, true);
/*  565 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridNode"})
/*      */   public void datagridNode(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  580 */     String processid = request.getParameter("processid");
/*  581 */     CriteriaQuery cq = new CriteriaQuery(TPProcessnode.class, dataGrid);
/*  582 */     if (StringUtil.isNotEmpty(processid)) {
/*  583 */       cq.eq("TPProcess.id", processid);
/*  584 */       cq.add();
/*      */     }
/*  586 */     this.systemService.getDataGridReturn(cq, true);
/*  587 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delType"})
/*      */   @ResponseBody
/*      */   public AjaxJson delType(TSType processtype, HttpServletRequest request)
/*      */   {
/*  598 */     AjaxJson j = new AjaxJson();
/*  599 */     processtype = (TSType)this.systemService.getEntity(TSType.class, processtype.getId());
/*  600 */     this.message = ("流程类别: " + processtype.getTypename() + "被删除 成功");
/*  601 */     this.systemService.delete(processtype);
/*  602 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */ 
/*  604 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delBus"})
/*      */   @ResponseBody
/*      */   public AjaxJson delBus(TSBusConfig busbase, HttpServletRequest request)
/*      */   {
/*  615 */     AjaxJson j = new AjaxJson();
/*  616 */     busbase = (TSBusConfig)this.systemService.getEntity(TSBusConfig.class, busbase.getId());
/*  617 */     this.message = ("流程类别: " + busbase.getBusname() + "被删除 成功");
/*  618 */     this.systemService.delete(busbase);
/*  619 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */ 
/*  621 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delprocess"})
/*      */   @ResponseBody
/*      */   public AjaxJson delprocess(TPProcess process, HttpServletRequest request)
/*      */   {
/*  632 */     AjaxJson j = new AjaxJson();
/*  633 */     process = (TPProcess)this.systemService.getEntity(TPProcess.class, process.getId());
/*  634 */     this.message = ("流程: " + process.getProcessname() + "被删除 成功");
/*  635 */     this.systemService.delete(process);
/*  636 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */ 
/*  638 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delPro"})
/*      */   @ResponseBody
/*      */   public AjaxJson delPro(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  649 */     AjaxJson j = new AjaxJson();
/*  650 */     processpro = (TPProcesspro)this.systemService.getEntity(TPProcesspro.class, processpro.getId());
/*  651 */     this.message = ("流程类别: " + processpro.getProcessproname() + "被删除 成功");
/*  652 */     this.systemService.delete(processpro);
/*  653 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */ 
/*  655 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delNode"})
/*      */   @ResponseBody
/*      */   public AjaxJson delNode(TPProcessnode processnode, HttpServletRequest request)
/*      */   {
/*  666 */     AjaxJson j = new AjaxJson();
/*  667 */     processnode = (TPProcessnode)this.systemService.getEntity(TPProcessnode.class, processnode.getId());
/*  668 */     this.message = ("流程节点: " + processnode.getProcessnodename() + "被删除 成功");
/*  669 */     this.systemService.delete(processnode);
/*  670 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */ 
/*  672 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveType"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveType(TSType processtype, HttpServletRequest request)
/*      */   {
/*  684 */     AjaxJson j = new AjaxJson();
/*  685 */     if (processtype.getId() != null) {
/*  686 */       this.message = ("流程类型: " + processtype.getTypename() + "被更新成功");
/*  687 */       this.userService.saveOrUpdate(processtype);
/*  688 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     } else {
/*  690 */       this.message = ("流程类型: " + processtype.getTypename() + "被添加成功");
/*  691 */       this.userService.saveOrUpdate(processtype);
/*  692 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  695 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveBus"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveBus(TSBusConfig busbase, HttpServletRequest request)
/*      */   {
/*  707 */     AjaxJson j = new AjaxJson();
/*  708 */     if (StringUtil.isNotEmpty(busbase.getId())) {
/*  709 */       this.message = ("业务参数: " + busbase.getBusname() + "被更新成功");
/*  710 */       this.userService.saveOrUpdate(busbase);
/*  711 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     } else {
/*  713 */       this.message = ("业务参数: " + busbase.getBusname() + "被添加成功");
/*  714 */       this.userService.save(busbase);
/*  715 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  718 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"savePro"})
/*      */   @ResponseBody
/*      */   public AjaxJson savePro(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  730 */     AjaxJson j = new AjaxJson();
/*  731 */     if (StringUtil.isNotEmpty(processpro.getId())) {
/*  732 */       this.message = ("流程参数: " + processpro.getProcessproname() + "被更新成功");
/*  733 */       this.userService.saveOrUpdate(processpro);
/*  734 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     } else {
/*  736 */       this.message = ("流程参数: " + processpro.getProcessproname() + "被添加成功");
/*  737 */       this.userService.save(processpro);
/*  738 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  741 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveNode"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveNode(TPProcessnode processnode, HttpServletRequest request)
/*      */   {
/*  753 */     AjaxJson j = new AjaxJson();
/*  754 */     String formid = oConvertUtils.getString(request.getAttribute("fromid"));
/*  755 */     TPForm form = (TPForm)this.systemService.getEntity(TPForm.class, formid);
/*  756 */     processnode.setTPForm(form);
/*  757 */     if (StringUtil.isNotEmpty(processnode.getId())) {
/*  758 */       this.message = ("流程节点: " + processnode.getProcessnodename() + "被更新成功");
/*  759 */       this.userService.saveOrUpdate(processnode);
/*  760 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     } else {
/*  762 */       this.message = ("流程节点: " + processnode.getProcessnodename() + "被添加成功");
/*  763 */       this.userService.save(processnode);
/*  764 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/*  767 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateType"})
/*      */   public ModelAndView addorupdateType(TSType processtype, HttpServletRequest req)
/*      */   {
/*  777 */     if (StringUtil.isNotEmpty(processtype.getId())) {
/*  778 */       processtype = (TSType)this.systemService.getEntity(TSType.class, processtype.getId());
/*  779 */       req.setAttribute("processtype", processtype);
/*      */     }
/*  781 */     return new ModelAndView("workflow/process/processtype");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateBus"})
/*      */   public ModelAndView addorupdateBus(TSBusConfig busbase, HttpServletRequest req)
/*      */   {
/*  791 */     String processid = req.getParameter("processid");
/*  792 */     TSType type = (TSType)this.systemService.findUniqueByProperty(TSType.class, "typecode", WorkFlowGlobals.DataBase_Bus);
/*  793 */     if (type != null) {
/*  794 */       List tableList = this.systemService.findByProperty(TSTable.class, "TSType.id", type.getId());
/*  795 */       req.setAttribute("tableList", tableList);
/*      */     }
/*  797 */     if (StringUtil.isNotEmpty(busbase.getId())) {
/*  798 */       busbase = (TSBusConfig)this.systemService.getEntity(TSBusConfig.class, busbase.getId());
/*  799 */       req.setAttribute("busbase", busbase);
/*      */     }
/*  801 */     req.setAttribute("processid", processid);
/*  802 */     return new ModelAndView("workflow/process/busbase");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdatePro"})
/*      */   public ModelAndView addorupdatePro(TPProcesspro processpro, HttpServletRequest request)
/*      */   {
/*  812 */     String processid = request.getParameter("processid");
/*  813 */     request.setAttribute("processid", processid);
/*  814 */     List nodeList = this.systemService.findByProperty(TPProcessnode.class, "TPProcess.id", processid);
/*  815 */     request.setAttribute("nodeList", nodeList);
/*  816 */     List typeList = this.systemService.loadAll(TSType.class);
/*  817 */     request.setAttribute("typeList", typeList);
/*  818 */     List forms = this.systemService.loadAll(TPForm.class);
/*  819 */     request.setAttribute("forms", forms);
/*  820 */     if (StringUtil.isNotEmpty(processpro.getId())) {
/*  821 */       processpro = (TPProcesspro)this.systemService.getEntity(TPProcesspro.class, processpro.getId());
/*  822 */       request.setAttribute("processpro", processpro);
/*      */     }
/*  824 */     return new ModelAndView("workflow/process/processpro");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateNode"})
/*      */   public ModelAndView addorupdateNode(TPProcessnode processnode, HttpServletRequest request)
/*      */   {
/*  834 */     String processid = request.getParameter("processid");
/*  835 */     request.setAttribute("processid", processid);
/*  836 */     List processList = this.systemService.loadAll(TPProcess.class);
/*  837 */     request.setAttribute("processList", processList);
/*  838 */     List formList = this.systemService.loadAll(TPForm.class);
/*  839 */     request.setAttribute("formList", formList);
/*  840 */     if (processnode.getId() != null) {
/*  841 */       processnode = (TPProcessnode)this.systemService.getEntity(TPProcessnode.class, processnode.getId());
/*  842 */       request.setAttribute("processnode", processnode);
/*      */     }
/*  844 */     return new ModelAndView("workflow/processnode/processnode");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"deployProcess"})
/*      */   @ResponseBody
/*      */   public AjaxJson deployProcess(HttpServletRequest request)
/*      */   {
/*  856 */     AjaxJson j = new AjaxJson();
/*  857 */     String processid = request.getParameter("processid");
/*  858 */     TPProcess process = (TPProcess)this.systemService.getEntity(TPProcess.class, processid);
/*  859 */     if (process != null) {
/*      */       try {
/*  861 */         this.repositoryService.createDeployment().addInputStream(process.getProcesskey() + ".bpmn", StreamUtils.byteTOInputStream(process.getProcessxml())).name(process.getProcesskey()).deploy();
/*  862 */         process.setProcessstate(WorkFlowGlobals.Process_Deploy_YES);
/*  863 */         this.systemService.updateEntitie(process);
/*  864 */         this.message = "发布成功";
/*      */       } catch (Exception e) {
/*  866 */         this.message = "发布失败";
/*      */       }
/*      */     }
/*  869 */     j.setMsg(this.message);
/*  870 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"form"})
/*      */   public ModelAndView form(HttpServletRequest request)
/*      */   {
/*  885 */     return new ModelAndView("workflow/form/formsList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"formpro"})
/*      */   public ModelAndView formpro(HttpServletRequest request)
/*      */   {
/*  895 */     String formid = request.getParameter("formid");
/*  896 */     TPForm form = (TPForm)this.systemService.get(TPForm.class, formid);
/*  897 */     request.setAttribute("form", form);
/*  898 */     return new ModelAndView("workflow/form/formproList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridForm"})
/*      */   public void datagridForm(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  915 */     CriteriaQuery cq = new CriteriaQuery(TPForm.class, dataGrid);
/*      */ 
/*  919 */     this.systemService.getDataGridReturn(cq, true);
/*  920 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"datagridFPro"})
/*      */   public void datagridFPro(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/*  934 */     String formid = request.getParameter("formid");
/*  935 */     CriteriaQuery cq = new CriteriaQuery(TPFormpro.class, dataGrid);
/*  936 */     if (StringUtil.isNotEmpty(formid)) {
/*  937 */       cq.eq("TPForm.id", formid);
/*  938 */       cq.add();
/*      */     }
/*  940 */     this.systemService.getDataGridReturn(cq, true);
/*  941 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateForm"})
/*      */   public ModelAndView addorupdateForm(TPForm form, HttpServletRequest req)
/*      */   {
/*  951 */     List processList = this.systemService.loadAll(TPProcess.class);
/*  952 */     req.setAttribute("processList", processList);
/*  953 */     List typeList = this.systemService.loadAll(TSType.class);
/*  954 */     req.setAttribute("typeList", typeList);
/*  955 */     if (form.getId() != null) {
/*  956 */       form = (TPForm)this.systemService.getEntity(TPForm.class, form.getId());
/*  957 */       req.setAttribute("form", form);
/*      */     }
/*  959 */     return new ModelAndView("workflow/form/form");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addorupdateFPro"})
/*      */   public ModelAndView addorupdateFPro(TPFormpro formpro, HttpServletRequest req)
/*      */   {
/*  969 */     String formid = req.getParameter("formid");
/*  970 */     req.setAttribute("formid", formid);
/*  971 */     String processid = req.getParameter("processid");
/*  972 */     req.setAttribute("processid", processid);
/*  973 */     List typeList = this.systemService.loadAll(TSType.class);
/*  974 */     req.setAttribute("typeList", typeList);
/*  975 */     if (formpro.getId() != null) {
/*  976 */       formpro = (TPFormpro)this.systemService.getEntity(TPFormpro.class, formpro.getId());
/*  977 */       req.setAttribute("formpro", formpro);
/*      */     }
/*  979 */     return new ModelAndView("workflow/form/formpro");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveForm"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveForm(TPForm form, HttpServletRequest request)
/*      */   {
/*  991 */     AjaxJson j = new AjaxJson();
/*  992 */     if (StringUtil.isNotEmpty(form.getId())) {
/*  993 */       this.message = ("表单: " + form.getFormname() + "被更新成功");
/*  994 */       this.systemService.saveOrUpdate(form);
/*  995 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     } else {
/*  997 */       this.message = ("表单: " + form.getFormname() + "被添加成功");
/*  998 */       this.userService.save(form);
/*  999 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/* 1002 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveFPro"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveFPro(TPFormpro formpro, HttpServletRequest request)
/*      */   {
/* 1014 */     AjaxJson j = new AjaxJson();
/* 1015 */     if (StringUtil.isNotEmpty(formpro.getId())) {
/* 1016 */       this.message = ("表单参数: " + formpro.getFormproname() + "被更新成功");
/* 1017 */       this.systemService.saveOrUpdate(formpro);
/* 1018 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     } else {
/* 1020 */       this.message = ("表单参数: " + formpro.getFormproname() + "被添加成功");
/* 1021 */       this.userService.save(formpro);
/* 1022 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/*      */ 
/* 1025 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delForm"})
/*      */   @ResponseBody
/*      */   public AjaxJson delForm(TPForm form, HttpServletRequest request)
/*      */   {
/* 1036 */     AjaxJson j = new AjaxJson();
/* 1037 */     form = (TPForm)this.systemService.getEntity(TPForm.class, form.getId());
/* 1038 */     this.message = ("表单: " + form.getFormname() + "被删除 成功");
/* 1039 */     this.systemService.delete(form);
/* 1040 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */ 
/* 1042 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delFPro"})
/*      */   @ResponseBody
/*      */   public AjaxJson delFPro(TPFormpro formpro, HttpServletRequest request)
/*      */   {
/* 1053 */     AjaxJson j = new AjaxJson();
/* 1054 */     formpro = (TPFormpro)this.systemService.getEntity(TPFormpro.class, formpro.getId());
/* 1055 */     this.message = ("表单参数: " + formpro.getFormproname() + "被删除 成功");
/* 1056 */     this.systemService.delete(formpro);
/* 1057 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */ 
/* 1059 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"addpro"})
/*      */   public ModelAndView addpro(HttpServletRequest request)
/*      */   {
/* 1103 */     String typeid = request.getParameter("id");
/* 1104 */     request.setAttribute("typeid", typeid);
/* 1105 */     return new ModelAndView("workflow/process/process");
/*      */   }
/*      */   @RequestMapping(params={"choosePro"})
/*      */   public ModelAndView choosePro(HttpServletRequest request) {
/* 1110 */     List formList = this.systemService.loadAll(TPForm.class);
/* 1111 */     request.setAttribute("formList", formList);
/* 1112 */     return new ModelAndView("workflow/process/process");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"chooseListener"})
/*      */   public ModelAndView chooseListener(HttpServletRequest request)
/*      */   {
/* 1123 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/* 1124 */     return new ModelAndView("designer/listenerList", "typeid", typeid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"listenerGrid"})
/*      */   @ResponseBody
/*      */   public void listenerGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/* 1136 */     Short listenerstate = oConvertUtils.getShort(request.getParameter("status"));
/* 1137 */     Short typeid = oConvertUtils.getShort(request.getParameter("typeid"));
/* 1138 */     CriteriaQuery cq = new CriteriaQuery(TPListerer.class, dataGrid);
/* 1139 */     if (StringUtil.isNotEmpty(listenerstate)) {
/* 1140 */       cq.eq("listenerstate", listenerstate);
/*      */     }
/* 1142 */     if (StringUtil.isNotEmpty(typeid)) {
/* 1143 */       cq.eq("typeid", typeid);
/*      */     }
/* 1145 */     cq.add();
/* 1146 */     this.systemService.getDataGridReturn(cq, true);
/* 1147 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveListener"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveListener(TPListerer tpListerer, HttpServletRequest request)
/*      */   {
/* 1159 */     AjaxJson j = new AjaxJson();
/* 1160 */     String event = "";
/* 1161 */     Short typeid = oConvertUtils.getShort(request.getParameter("typeid"));
/* 1162 */     if (typeid.equals(WorkFlowGlobals.Listener_Type_Excution))
/* 1163 */       event = oConvertUtils.getString(request.getParameter("executioneven"));
/*      */     else {
/* 1165 */       event = oConvertUtils.getString(request.getParameter("taskeven"));
/*      */     }
/* 1167 */     tpListerer.setListenereven(event);
/* 1168 */     if (StringUtil.isNotEmpty(tpListerer.getId())) {
/* 1169 */       this.message = ("监听: " + tpListerer.getListenername() + "更新成功");
/* 1170 */       this.userService.saveOrUpdate(tpListerer);
/* 1171 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     } else {
/* 1173 */       this.message = ("监听: " + tpListerer.getListenername() + "添加成功");
/* 1174 */       tpListerer.setListenerstate(WorkFlowGlobals.Listener_No);
/* 1175 */       this.userService.save(tpListerer);
/* 1176 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/* 1178 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delListeren"})
/*      */   @ResponseBody
/*      */   public AjaxJson delListeren(TPListerer tpListerer, HttpServletRequest request)
/*      */   {
/* 1189 */     AjaxJson j = new AjaxJson();
/* 1190 */     tpListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, tpListerer.getId());
/* 1191 */     if (tpListerer.getTProcessListeners().size() == 0) {
/* 1192 */       this.message = ("监听: " + tpListerer.getListenername() + " 删除成功");
/* 1193 */       this.systemService.delete(tpListerer);
/* 1194 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/*      */     }
/*      */     else {
/* 1197 */       this.message = ("监听: " + tpListerer.getListenername() + "已经在运行中无法删除");
/*      */     }
/*      */ 
/* 1200 */     j.setMsg(this.message);
/* 1201 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"setListeren"})
/*      */   @ResponseBody
/*      */   public AjaxJson setListeren(HttpServletRequest request)
/*      */   {
/* 1213 */     AjaxJson j = new AjaxJson();
/* 1214 */     String id = request.getParameter("id");
/* 1215 */     Short status = oConvertUtils.getShort(request.getParameter("status"));
/* 1216 */     TPListerer tpListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, id);
/* 1217 */     if (tpListerer != null) {
/* 1218 */       tpListerer.setListenerstate(status);
/* 1219 */       this.systemService.updateEntitie(tpListerer);
/* 1220 */       if (status.equals(WorkFlowGlobals.Listener_No))
/* 1221 */         j.setMsg("监听已禁用");
/*      */       else {
/* 1223 */         j.setMsg("监听已启用");
/*      */       }
/*      */     }
/* 1226 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"setProcessListener"})
/*      */   @ResponseBody
/*      */   public AjaxJson setProcessListener(HttpServletRequest request)
/*      */   {
/* 1238 */     AjaxJson j = new AjaxJson();
/* 1239 */     String id = request.getParameter("id");
/* 1240 */     TPProcessListener tpProcessListener = (TPProcessListener)this.systemService.getEntity(TPProcessListener.class, id);
/* 1241 */     if (tpProcessListener != null) {
/* 1242 */       Short status = WorkFlowGlobals.Process_Listener_NO.equals(tpProcessListener.getStatus()) ? WorkFlowGlobals.Process_Listener_YES : WorkFlowGlobals.Process_Listener_NO;
/* 1243 */       tpProcessListener.setStatus(status);
/* 1244 */       this.systemService.updateEntitie(tpProcessListener);
/* 1245 */       if (status.equals(WorkFlowGlobals.Process_Listener_NO)) {
/* 1246 */         j.setSuccess(false);
/* 1247 */         j.setMsg("监听已禁用");
/*      */       } else {
/* 1249 */         j.setMsg("监听已启用");
/*      */       }
/*      */     }
/* 1252 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"delProcesListeren"})
/*      */   @ResponseBody
/*      */   public AjaxJson delProcesListeren(TPProcessListener tpProcessListener, HttpServletRequest request)
/*      */   {
/* 1263 */     AjaxJson j = new AjaxJson();
/* 1264 */     tpProcessListener = (TPProcessListener)this.systemService.getEntity(TPProcessListener.class, tpProcessListener.getId());
/* 1265 */     this.message = ("监听: " + tpProcessListener.getTPListerer().getListenername() + " 删除成功");
/* 1266 */     this.systemService.delete(tpProcessListener);
/* 1267 */     this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_DEL, WorkFlowGlobals.Log_Leavel_INFO);
/* 1268 */     j.setMsg(this.message);
/* 1269 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"getNodelisteners"})
/*      */   @ResponseBody
/*      */   public void getNodelisteners(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/* 1281 */     String type = oConvertUtils.getString(request.getParameter("type"));
/* 1282 */     String processNode = oConvertUtils.getString(request.getParameter("processNode"));
/* 1283 */     String processkey = oConvertUtils.getString(request.getParameter("processId"));
/* 1284 */     CriteriaQuery cq = new CriteriaQuery(TPProcessListener.class, dataGrid);
/* 1285 */     if (type.equals(WorkFlowGlobals.Listener_Type_Task)) {
/* 1286 */       TPProcessnode tProcessnode = this.activitiService.getTPProcessnode(processNode, processkey);
/* 1287 */       if (tProcessnode != null)
/* 1288 */         cq.eq("TPProcessnode.id", tProcessnode.getId());
/*      */     }
/*      */     else {
/* 1291 */       cq.eq("nodename", processNode);
/*      */     }
/* 1293 */     cq.add();
/* 1294 */     this.systemService.getDataGridReturn(cq, false);
/* 1295 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"saveProcessListener"})
/*      */   @ResponseBody
/*      */   public AjaxJson saveProcessListener(HttpServletRequest request)
/*      */   {
/* 1305 */     AjaxJson j = new AjaxJson();
/* 1306 */     Short type = oConvertUtils.getShort(request.getParameter("type"));
/* 1307 */     String listenerid = oConvertUtils.getString(request.getParameter("listenerid"));
/* 1308 */     String processkey = oConvertUtils.getString(request.getParameter("processkey"));
/* 1309 */     String taskDefinitionKey = ResourceUtil.getParameter("processNode");
/* 1310 */     TPProcess tProcess = null;
/* 1311 */     TPProcessnode tProcessnode = null;
/* 1312 */     if (StringUtil.isNotEmpty(processkey)) {
/* 1313 */       tProcess = (TPProcess)this.systemService.findUniqueByProperty(TPProcess.class, "processkey", processkey);
/* 1314 */       if (tProcess == null) {
/* 1315 */         tProcess = new TPProcess();
/* 1316 */         tProcess.setProcesskey(processkey);
/* 1317 */         this.systemService.save(tProcess);
/*      */       }
/*      */     }
/* 1320 */     if ((type.equals(WorkFlowGlobals.Listener_Type_Task)) && 
/* 1321 */       (StringUtil.isNotEmpty(taskDefinitionKey))) {
/* 1322 */       tProcessnode = this.activitiService.getTPProcessnode(taskDefinitionKey, processkey);
/* 1323 */       if (tProcessnode == null) {
/* 1324 */         tProcessnode = new TPProcessnode();
/* 1325 */         tProcessnode.setTPProcess(tProcess);
/* 1326 */         tProcessnode.setTPForm(null);
/* 1327 */         tProcessnode.setProcessnodecode(taskDefinitionKey);
/* 1328 */         this.systemService.save(tProcessnode);
/*      */       } else {
/* 1330 */         tProcessnode.setTPProcess(tProcess);
/* 1331 */         tProcessnode.setTPForm(null);
/* 1332 */         tProcessnode.setProcessnodecode(taskDefinitionKey);
/* 1333 */         this.systemService.updateEntitie(tProcessnode);
/*      */       }
/*      */     }
/*      */ 
/* 1337 */     if (StringUtil.isNotEmpty(listenerid)) {
/* 1338 */       String[] listens = listenerid.split(",");
/* 1339 */       int len = listens.length;
/* 1340 */       for (int i = 0; i < len; i++) {
/* 1341 */         TPProcessListener tPProcessListener = new TPProcessListener();
/* 1342 */         TPListerer tPListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, listens[i]);
/* 1343 */         tPProcessListener.setTPListerer(tPListerer);
/* 1344 */         if (type.equals(WorkFlowGlobals.Listener_Type_Task)) {
/* 1345 */           tPProcessListener.setTPProcessnode(tProcessnode);
/*      */         }
/* 1347 */         if (type.equals(WorkFlowGlobals.Listener_Type_Excution)) {
/* 1348 */           tPProcessListener.setTPProcess(tProcess);
/* 1349 */           tPProcessListener.setNodename(taskDefinitionKey);
/*      */         }
/* 1351 */         tPProcessListener.setStatus(WorkFlowGlobals.Process_Deploy_NO);
/* 1352 */         this.systemService.save(tPProcessListener);
/*      */       }
/*      */     }
/*      */ 
/* 1356 */     return j;
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"busConfigGrid"})
/*      */   public void busConfigGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*      */   {
/* 1365 */     CriteriaQuery cq = new CriteriaQuery(TSBusConfig.class, dataGrid);
/* 1366 */     this.systemService.getDataGridReturn(cq, true);
/* 1367 */     TagUtil.datagrid(response, dataGrid);
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"listenerList"})
/*      */   public ModelAndView listenerList(HttpServletRequest request)
/*      */   {
/* 1378 */     return new ModelAndView("workflow/listener/listenerList");
/*      */   }
/*      */ 
/*      */   @RequestMapping(params={"aouListener"})
/*      */   public ModelAndView aouListener(TPListerer tpListerer, HttpServletRequest request)
/*      */   {
/* 1389 */     if (tpListerer.getId() != null) {
/* 1390 */       tpListerer = (TPListerer)this.systemService.getEntity(TPListerer.class, tpListerer.getId());
/* 1391 */       request.setAttribute("tpListerer", tpListerer);
/*      */     }
/* 1393 */     return new ModelAndView("workflow/listener/listener");
/*      */   }
/*      */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.controller.process.ProcessController
 * JD-Core Version:    0.6.0
 */