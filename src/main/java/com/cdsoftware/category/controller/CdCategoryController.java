package com.cdsoftware.category.controller;
import com.cdsoftware.category.entity.CdCategoryEntity;
import com.cdsoftware.category.service.CdCategoryServiceI;
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
 * @Description: 大类管理
 * @author onlineGenerator
 * @date 2018-03-29 15:14:54
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/cdCategoryController")
public class CdCategoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CdCategoryController.class);

	@Autowired
	private CdCategoryServiceI cdCategoryService;
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
	 * 大类管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView cdCategory(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/category/cdCategoryList");
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
	public void datagrid(CdCategoryEntity cdCategory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CdCategoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cdCategory, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cdCategoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除大类管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CdCategoryEntity cdCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cdCategory = systemService.getEntity(CdCategoryEntity.class, cdCategory.getId());
		message = "大类管理删除成功";
		try{
			cdCategoryService.delete(cdCategory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "大类管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除大类管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "大类管理删除成功";
		try{
			for(String id:ids.split(",")){
				CdCategoryEntity cdCategory = systemService.getEntity(CdCategoryEntity.class, 
				id
				);
				cdCategoryService.delete(cdCategory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "大类管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加大类管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CdCategoryEntity cdCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "大类管理添加成功";
		try{
			cdCategoryService.save(cdCategory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "大类管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新大类管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CdCategoryEntity cdCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "大类管理更新成功";
		CdCategoryEntity t = cdCategoryService.get(CdCategoryEntity.class, cdCategory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cdCategory, t);
			cdCategoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "大类管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 大类管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CdCategoryEntity cdCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdCategory.getId())) {
			cdCategory = cdCategoryService.getEntity(CdCategoryEntity.class, cdCategory.getId());
			req.setAttribute("cdCategoryPage", cdCategory);
		}
		return new ModelAndView("com/cdsoftware/category/cdCategory-add");
	}
	/**
	 * 大类管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CdCategoryEntity cdCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdCategory.getId())) {
			cdCategory = cdCategoryService.getEntity(CdCategoryEntity.class, cdCategory.getId());
			req.setAttribute("cdCategoryPage", cdCategory);
		}
		return new ModelAndView("com/cdsoftware/category/cdCategory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/category/cdCategoryUpload");
	}
	
	
}
