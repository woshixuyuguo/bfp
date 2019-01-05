package com.cdsoftware.program.controller;
import com.cdsoftware.program.entity.CdMiniProgramEntity;
import com.cdsoftware.program.service.CdMiniProgramServiceI;
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
 * @Description: 小程序管理
 * @author onlineGenerator
 * @date 2018-03-27 13:47:08
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/cdMiniProgramController")
public class CdMiniProgramController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CdMiniProgramController.class);

	@Autowired
	private CdMiniProgramServiceI cdMiniProgramService;
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
	 * 小程序管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView cdMiniProgram(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/program/cdMiniProgramList");
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
	public void datagrid(CdMiniProgramEntity cdMiniProgram,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CdMiniProgramEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cdMiniProgram, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cdMiniProgramService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除小程序管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CdMiniProgramEntity cdMiniProgram, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cdMiniProgram = systemService.getEntity(CdMiniProgramEntity.class, cdMiniProgram.getId());
		message = "小程序管理删除成功";
		try{
			cdMiniProgramService.delete(cdMiniProgram);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "小程序管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除小程序管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "小程序管理删除成功";
		try{
			for(String id:ids.split(",")){
				CdMiniProgramEntity cdMiniProgram = systemService.getEntity(CdMiniProgramEntity.class, 
				id
				);
				cdMiniProgramService.delete(cdMiniProgram);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "小程序管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加小程序管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CdMiniProgramEntity cdMiniProgram, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "小程序管理添加成功";
		try{
			cdMiniProgramService.save(cdMiniProgram);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "小程序管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新小程序管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CdMiniProgramEntity cdMiniProgram, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "小程序管理更新成功";
		CdMiniProgramEntity t = cdMiniProgramService.get(CdMiniProgramEntity.class, cdMiniProgram.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cdMiniProgram, t);
			cdMiniProgramService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "小程序管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 小程序管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CdMiniProgramEntity cdMiniProgram, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdMiniProgram.getId())) {
			cdMiniProgram = cdMiniProgramService.getEntity(CdMiniProgramEntity.class, cdMiniProgram.getId());
			req.setAttribute("cdMiniProgramPage", cdMiniProgram);
		}
		return new ModelAndView("com/cdsoftware/program/cdMiniProgram-add");
	}
	/**
	 * 小程序管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CdMiniProgramEntity cdMiniProgram, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdMiniProgram.getId())) {
			cdMiniProgram = cdMiniProgramService.getEntity(CdMiniProgramEntity.class, cdMiniProgram.getId());
			req.setAttribute("cdMiniProgramPage", cdMiniProgram);
		}
		return new ModelAndView("com/cdsoftware/program/cdMiniProgram-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/program/cdMiniProgramUpload");
	}
	

}
