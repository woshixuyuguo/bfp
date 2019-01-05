package com.cdsoftware.picture.controller;
import com.cdsoftware.picture.entity.IcbcPictureEntity;
import com.cdsoftware.picture.service.IcbcPictureServiceI;
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
 * @Description: 轮播图片
 * @author onlineGenerator
 * @date 2018-04-12 14:44:09
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/icbcPictureController")
public class IcbcPictureController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IcbcPictureController.class);

	@Autowired
	private IcbcPictureServiceI icbcPictureService;
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
	 * 轮播图片列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView icbcPicture(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/picture/icbcPictureList");
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
	public void datagrid(IcbcPictureEntity icbcPicture,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IcbcPictureEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, icbcPicture, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.icbcPictureService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除轮播图片
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(IcbcPictureEntity icbcPicture, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		icbcPicture = systemService.getEntity(IcbcPictureEntity.class, icbcPicture.getId());
		message = "轮播图片删除成功";
		try{
			icbcPictureService.delete(icbcPicture);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "轮播图片删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除轮播图片
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "轮播图片删除成功";
		try{
			for(String id:ids.split(",")){
				IcbcPictureEntity icbcPicture = systemService.getEntity(IcbcPictureEntity.class, 
				id
				);
				icbcPictureService.delete(icbcPicture);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "轮播图片删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加轮播图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IcbcPictureEntity icbcPicture, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "轮播图片添加成功";
		try{
			icbcPictureService.save(icbcPicture);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "轮播图片添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新轮播图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(IcbcPictureEntity icbcPicture, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "轮播图片更新成功";
		IcbcPictureEntity t = icbcPictureService.get(IcbcPictureEntity.class, icbcPicture.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(icbcPicture, t);
			icbcPictureService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "轮播图片更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 轮播图片新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(IcbcPictureEntity icbcPicture, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcPicture.getId())) {
			icbcPicture = icbcPictureService.getEntity(IcbcPictureEntity.class, icbcPicture.getId());
			req.setAttribute("icbcPicturePage", icbcPicture);
		}
		return new ModelAndView("com/cdsoftware/picture/icbcPicture-add");
	}
	/**
	 * 轮播图片编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(IcbcPictureEntity icbcPicture, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcPicture.getId())) {
			icbcPicture = icbcPictureService.getEntity(IcbcPictureEntity.class, icbcPicture.getId());
			req.setAttribute("icbcPicturePage", icbcPicture);
		}
		return new ModelAndView("com/cdsoftware/picture/icbcPicture-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/picture/icbcPictureUpload");
	}
	
	
}
