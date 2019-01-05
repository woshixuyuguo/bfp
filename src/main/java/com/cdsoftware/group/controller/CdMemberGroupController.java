package com.cdsoftware.group.controller;
import com.cdsoftware.group.entity.CdMemberGroupEntity;
import com.cdsoftware.group.service.CdMemberGroupServiceI;
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
 * @Description: 转发记录
 * @author onlineGenerator
 * @date 2018-03-29 13:34:56
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/cdMemberGroupController")
public class CdMemberGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CdMemberGroupController.class);

	@Autowired
	private CdMemberGroupServiceI cdMemberGroupService;
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
	 * 转发记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView cdMemberGroup(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/group/cdMemberGroupList");
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
	public void datagrid(CdMemberGroupEntity cdMemberGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CdMemberGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cdMemberGroup, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cdMemberGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除转发记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CdMemberGroupEntity cdMemberGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cdMemberGroup = systemService.getEntity(CdMemberGroupEntity.class, cdMemberGroup.getId());
		message = "转发记录删除成功";
		try{
			cdMemberGroupService.delete(cdMemberGroup);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "转发记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除转发记录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "转发记录删除成功";
		try{
			for(String id:ids.split(",")){
				CdMemberGroupEntity cdMemberGroup = systemService.getEntity(CdMemberGroupEntity.class, 
				id
				);
				cdMemberGroupService.delete(cdMemberGroup);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "转发记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加转发记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CdMemberGroupEntity cdMemberGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "转发记录添加成功";
		try{
			cdMemberGroupService.save(cdMemberGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "转发记录添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新转发记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CdMemberGroupEntity cdMemberGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "转发记录更新成功";
		CdMemberGroupEntity t = cdMemberGroupService.get(CdMemberGroupEntity.class, cdMemberGroup.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cdMemberGroup, t);
			cdMemberGroupService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "转发记录更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 转发记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CdMemberGroupEntity cdMemberGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdMemberGroup.getId())) {
			cdMemberGroup = cdMemberGroupService.getEntity(CdMemberGroupEntity.class, cdMemberGroup.getId());
			req.setAttribute("cdMemberGroupPage", cdMemberGroup);
		}
		return new ModelAndView("com/cdsoftware/group/cdMemberGroup-add");
	}
	/**
	 * 转发记录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CdMemberGroupEntity cdMemberGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdMemberGroup.getId())) {
			cdMemberGroup = cdMemberGroupService.getEntity(CdMemberGroupEntity.class, cdMemberGroup.getId());
			req.setAttribute("cdMemberGroupPage", cdMemberGroup);
		}
		return new ModelAndView("com/cdsoftware/group/cdMemberGroup-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/group/cdMemberGroupUpload");
	}
	
	
}
