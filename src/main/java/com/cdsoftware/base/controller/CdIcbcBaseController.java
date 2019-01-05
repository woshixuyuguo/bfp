package com.cdsoftware.base.controller;
import com.cdsoftware.base.entity.CdIcbcBaseEntity;
import com.cdsoftware.base.service.CdIcbcBaseServiceI;
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
 * @Description: 工商银行基础参数
 * @author onlineGenerator
 * @date 2018-05-08 16:11:04
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/cdIcbcBaseController")
public class CdIcbcBaseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CdIcbcBaseController.class);

	@Autowired
	private CdIcbcBaseServiceI cdIcbcBaseService;
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
	 * 工商银行基础参数列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView cdIcbcBase(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/base/cdIcbcBaseList");
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
	public void datagrid(CdIcbcBaseEntity cdIcbcBase,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CdIcbcBaseEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cdIcbcBase, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cdIcbcBaseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工商银行基础参数
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CdIcbcBaseEntity cdIcbcBase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cdIcbcBase = systemService.getEntity(CdIcbcBaseEntity.class, cdIcbcBase.getId());
		message = "工商银行基础参数删除成功";
		try{
			cdIcbcBaseService.delete(cdIcbcBase);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工商银行基础参数删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工商银行基础参数
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "工商银行基础参数删除成功";
		try{
			for(String id:ids.split(",")){
				CdIcbcBaseEntity cdIcbcBase = systemService.getEntity(CdIcbcBaseEntity.class, 
				id
				);
				cdIcbcBaseService.delete(cdIcbcBase);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工商银行基础参数删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工商银行基础参数
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CdIcbcBaseEntity cdIcbcBase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工商银行基础参数添加成功";
		try{
			cdIcbcBaseService.save(cdIcbcBase);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工商银行基础参数添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工商银行基础参数
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CdIcbcBaseEntity cdIcbcBase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工商银行基础参数更新成功";
		CdIcbcBaseEntity t = cdIcbcBaseService.get(CdIcbcBaseEntity.class, cdIcbcBase.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cdIcbcBase, t);
			cdIcbcBaseService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工商银行基础参数更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 工商银行基础参数新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CdIcbcBaseEntity cdIcbcBase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdIcbcBase.getId())) {
			cdIcbcBase = cdIcbcBaseService.getEntity(CdIcbcBaseEntity.class, cdIcbcBase.getId());
			req.setAttribute("cdIcbcBasePage", cdIcbcBase);
		}
		return new ModelAndView("com/cdsoftware/base/cdIcbcBase-add");
	}
	/**
	 * 工商银行基础参数编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CdIcbcBaseEntity cdIcbcBase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdIcbcBase.getId())) {
			cdIcbcBase = cdIcbcBaseService.getEntity(CdIcbcBaseEntity.class, cdIcbcBase.getId());
			req.setAttribute("cdIcbcBasePage", cdIcbcBase);
		}
		return new ModelAndView("com/cdsoftware/base/cdIcbcBase-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/base/cdIcbcBaseUpload");
	}
	
	
}
