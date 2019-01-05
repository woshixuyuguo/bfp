package com.cdsoftware.transaction.controller;
import com.cdsoftware.transaction.entity.CdTransactionEntity;
import com.cdsoftware.transaction.service.CdTransactionServiceI;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 消费流水
 * @author onlineGenerator
 * @date 2018-03-27 13:51:52
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/cdTransactionController")
public class CdTransactionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CdTransactionController.class);

	@Autowired
	private CdTransactionServiceI cdTransactionService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 消费流水列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView cdTransaction(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/transaction/cdTransactionList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(CdTransactionEntity cdTransaction,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CdTransactionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cdTransaction, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cdTransactionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除消费流水
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CdTransactionEntity cdTransaction, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cdTransaction = systemService.getEntity(CdTransactionEntity.class, cdTransaction.getId());
		message = "消费流水删除成功";
		try{
			cdTransactionService.delete(cdTransaction);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "消费流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除消费流水
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "消费流水删除成功";
		try{
			for(String id:ids.split(",")){
				CdTransactionEntity cdTransaction = systemService.getEntity(CdTransactionEntity.class, 
				id
				);
				cdTransactionService.delete(cdTransaction);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "消费流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加消费流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CdTransactionEntity cdTransaction, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "消费流水添加成功";
		try{
			cdTransactionService.save(cdTransaction);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "消费流水添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新消费流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CdTransactionEntity cdTransaction, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "消费流水更新成功";
		CdTransactionEntity t = cdTransactionService.get(CdTransactionEntity.class, cdTransaction.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cdTransaction, t);
			cdTransactionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "消费流水更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 消费流水新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CdTransactionEntity cdTransaction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdTransaction.getId())) {
			cdTransaction = cdTransactionService.getEntity(CdTransactionEntity.class, cdTransaction.getId());
			req.setAttribute("cdTransactionPage", cdTransaction);
		}
		return new ModelAndView("com/cdsoftware/transaction/cdTransaction-add");
	}
	/**
	 * 消费流水编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CdTransactionEntity cdTransaction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdTransaction.getId())) {
			cdTransaction = cdTransactionService.getEntity(CdTransactionEntity.class, cdTransaction.getId());
			req.setAttribute("cdTransactionPage", cdTransaction);
		}
		return new ModelAndView("com/cdsoftware/transaction/cdTransaction-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/transaction/cdTransactionUpload");
	}
	
	
}
