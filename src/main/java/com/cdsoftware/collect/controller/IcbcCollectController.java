package com.cdsoftware.collect.controller;
import com.cdsoftware.collect.entity.IcbcCollectEntity;
import com.cdsoftware.collect.service.IcbcCollectServiceI;
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
 * @Description: 收藏
 * @author onlineGenerator
 * @date 2018-05-14 09:17:51
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/icbcCollectController")
public class IcbcCollectController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IcbcCollectController.class);

	@Autowired
	private IcbcCollectServiceI icbcCollectService;
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
	 * 收藏列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView icbcCollect(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/collect/icbcCollectList");
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
	public void datagrid(IcbcCollectEntity icbcCollect,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IcbcCollectEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, icbcCollect, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.icbcCollectService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除收藏
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(IcbcCollectEntity icbcCollect, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		icbcCollect = systemService.getEntity(IcbcCollectEntity.class, icbcCollect.getId());
		message = "收藏删除成功";
		try{
			icbcCollectService.delete(icbcCollect);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收藏删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除收藏
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "收藏删除成功";
		try{
			for(String id:ids.split(",")){
				IcbcCollectEntity icbcCollect = systemService.getEntity(IcbcCollectEntity.class, 
				id
				);
				icbcCollectService.delete(icbcCollect);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "收藏删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收藏
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IcbcCollectEntity icbcCollect, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "收藏添加成功";
		try{
			icbcCollectService.save(icbcCollect);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收藏添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新收藏
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(IcbcCollectEntity icbcCollect, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "收藏更新成功";
		IcbcCollectEntity t = icbcCollectService.get(IcbcCollectEntity.class, icbcCollect.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(icbcCollect, t);
			icbcCollectService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "收藏更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 收藏新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(IcbcCollectEntity icbcCollect, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcCollect.getId())) {
			icbcCollect = icbcCollectService.getEntity(IcbcCollectEntity.class, icbcCollect.getId());
			req.setAttribute("icbcCollectPage", icbcCollect);
		}
		return new ModelAndView("com/cdsoftware/collect/icbcCollect-add");
	}
	/**
	 * 收藏编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(IcbcCollectEntity icbcCollect, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcCollect.getId())) {
			icbcCollect = icbcCollectService.getEntity(IcbcCollectEntity.class, icbcCollect.getId());
			req.setAttribute("icbcCollectPage", icbcCollect);
		}
		return new ModelAndView("com/cdsoftware/collect/icbcCollect-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/collect/icbcCollectUpload");
	}
	
	
}
