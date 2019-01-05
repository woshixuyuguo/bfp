/*     */ package org.jeecgframework.workflow.controller.process;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.common.DBTable;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSType;
/*     */ import org.jeecgframework.web.system.pojo.base.TSTypegroup;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.jeecgframework.workflow.common.WorkFlowGlobals;
/*     */ import org.jeecgframework.workflow.pojo.base.TSTable;
/*     */ import org.jeecgframework.workflow.service.ActivitiService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/dbController"})
/*     */ public class DataBaseController extends BaseController
/*     */ {
/*  40 */   private static final Logger logger = Logger.getLogger(DataBaseController.class);
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   private ActivitiService activitiService;
/*     */   private String message;
/*     */ 
/*     */   public String getMessage()
/*     */   {
/*  47 */     return this.message;
/*     */   }
/*     */ 
/*     */   public void setMessage(String message) {
/*  51 */     this.message = message;
/*     */   }
/*     */   @Autowired
/*     */   public void setSystemService(SystemService systemService) {
/*  56 */     this.systemService = systemService;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"tableTypeTabs"})
/*     */   public ModelAndView tableTypeTabs(HttpServletRequest request)
/*     */   {
/*  66 */     loadTables(request);
/*  67 */     return new ModelAndView("workflow/database/tableTypeTabs");
/*     */   }
/*     */ 
/*     */   private void loadTables(HttpServletRequest request)
/*     */   {
/*  75 */     Integer tableSize = this.systemService.getAllDbTableSize();
/*  76 */     List tsTableList = this.systemService.loadAll(TSTable.class);
/*  77 */     if (tableSize.intValue() > tsTableList.size()) {
/*  78 */       TSTypegroup tsTypegroup = this.systemService.getTypeGroup(WorkFlowGlobals.TypeGroup_Database, "数据表");
/*  79 */       TSType actType = this.systemService.getType(WorkFlowGlobals.DataBase_Activiti, "工作流引擎表", tsTypegroup);
/*  80 */       TSType baseType = this.systemService.getType(WorkFlowGlobals.DataBase_Base, "系统基础表", tsTypegroup);
/*  81 */       TSType busType = this.systemService.getType(WorkFlowGlobals.DataBase_Bus, "业务表", tsTypegroup);
/*  82 */       TSType proType = this.systemService.getType(WorkFlowGlobals.DataBase_Process, "自定义引擎表", tsTypegroup);
/*  83 */       List<DBTable> dbTables = this.systemService.getAllDbTableName();
/*  84 */       for (DBTable dbTable : dbTables) {
/*  85 */         TSTable tsTable = new TSTable();
/*  86 */         Class c = dbTable.getClass();
/*  87 */         tsTable.setTableTitle(dbTable.getTableTitle());
/*  88 */         tsTable.setEntityName(dbTable.getEntityName());
/*  89 */         tsTable.setTableName(dbTable.getTableName());
/*  90 */         tsTable.setEntityKey(dbTable.getTableName().substring(dbTable.getTableName().lastIndexOf("_") + 1));
/*  91 */         if (dbTable.getTableName().indexOf(WorkFlowGlobals.DataBase_Activiti) >= 0) {
/*  92 */           tsTable.setTSType(actType);
/*     */         }
/*  94 */         if (dbTable.getTableName().indexOf(WorkFlowGlobals.DataBase_Base) >= 0) {
/*  95 */           tsTable.setTSType(baseType);
/*     */         }
/*  97 */         if (dbTable.getTableName().indexOf(WorkFlowGlobals.DataBase_Bus) >= 0) {
/*  98 */           tsTable.setTSType(busType);
/*     */         }
/* 100 */         if (dbTable.getTableName().indexOf(WorkFlowGlobals.DataBase_Process) >= 0) {
/* 101 */           tsTable.setTSType(proType);
/*     */         }
/* 103 */         TSTable oldTable = (TSTable)this.systemService.findUniqueByProperty(TSTable.class, "tableName", dbTable.getTableName());
/* 104 */         if (oldTable == null) {
/* 105 */           this.systemService.save(tsTable);
/*     */         }
/*     */       }
/*     */     }
/* 109 */     TSTypegroup tsTypeList = (TSTypegroup)this.systemService.findUniqueByProperty(TSTypegroup.class, "typegroupcode", WorkFlowGlobals.TypeGroup_Database);
/* 110 */     request.setAttribute("tsTypeList", tsTypeList.getTSTypes());
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"reloadData"})
/*     */   @ResponseBody
/*     */   public AjaxJson reloadData(HttpServletRequest request)
/*     */   {
/* 121 */     AjaxJson json = new AjaxJson();
/* 122 */     this.activitiService.batchDelete(TSTable.class);
/* 123 */     loadTables(request);
/* 124 */     json.setMsg("重新加载成功");
/* 125 */     json.setSuccess(true);
/* 126 */     return json;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"tableList"})
/*     */   public ModelAndView tableList(HttpServletRequest request)
/*     */   {
/* 136 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/* 137 */     TSType type = (TSType)this.systemService.getEntity(TSType.class, typeid);
/* 138 */     request.setAttribute("type", type);
/* 139 */     return new ModelAndView("workflow/database/tableList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"aouTable"})
/*     */   public ModelAndView aouTable(TSTable table, HttpServletRequest req)
/*     */   {
/* 148 */     table = (TSTable)this.systemService.getEntity(TSTable.class, table.getId());
/* 149 */     req.setAttribute("table", table);
/* 150 */     return new ModelAndView("workflow/database/datatable");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"saveTable"})
/*     */   @ResponseBody
/*     */   public AjaxJson saveTable(TSTable table, HttpServletRequest request)
/*     */   {
/* 161 */     AjaxJson j = new AjaxJson();
/* 162 */     if (StringUtil.isNotEmpty(table.getId())) {
/* 163 */       this.message = ("数据表: " + table.getTableName() + "更新成功");
/* 164 */       this.systemService.saveOrUpdate(table);
/* 165 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_UPDATE, 
/* 166 */         WorkFlowGlobals.Log_Leavel_INFO);
/*     */     } else {
/* 168 */       this.message = ("数据表: " + table.getTableName() + "添加成功");
/* 169 */       this.systemService.save(table);
/* 170 */       this.systemService.addLog(this.message, WorkFlowGlobals.Log_Type_INSERT, 
/* 171 */         WorkFlowGlobals.Log_Leavel_INFO);
/*     */     }
/* 173 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"tableGrid"})
/*     */   public void tableGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 181 */     String typeid = oConvertUtils.getString(request.getParameter("typeid"));
/* 182 */     CriteriaQuery cq = new CriteriaQuery(TSTable.class, dataGrid);
/* 183 */     cq.eq("TSType.id", typeid);
/* 184 */     cq.add();
/* 185 */     this.systemService.getDataGridReturn(cq, true);
/* 186 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.controller.process.DataBaseController
 * JD-Core Version:    0.6.0
 */