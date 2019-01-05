package com.cdsoftware.activity.controller;
import com.cdsoftware.activity.entity.CdActivityEntity;
import com.cdsoftware.activity.service.CdActivityServiceI;
import com.cdsoftware.activity.page.CdActivityPage;
import com.cdsoftware.activity.entity.TransmitWordEntity;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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


/**   
 * @Title: Controller
 * @Description: 活动管理
 * @author onlineGenerator
 * @date 2018-03-29 10:12:12
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/cdActivityController")
public class CdActivityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CdActivityController.class);

	@Autowired
	private CdActivityServiceI cdActivityService;
	@Autowired
	private SystemService systemService;

	

	/**
	 * 活动管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView cdActivity(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/activity/cdActivityList");
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
	public void datagrid(CdActivityEntity cdActivity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CdActivityEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cdActivity);
		try{
		//自定义追加查询条件
		String query_startTime_begin = request.getParameter("startTime_begin");
		String query_startTime_end = request.getParameter("startTime_end");
		if(StringUtil.isNotEmpty(query_startTime_begin)){
			cq.ge("startTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_startTime_begin));
		}
		if(StringUtil.isNotEmpty(query_startTime_end)){
			cq.le("startTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_startTime_end));
		}
		String query_endTime_begin = request.getParameter("endTime_begin");
		String query_endTime_end = request.getParameter("endTime_end");
		if(StringUtil.isNotEmpty(query_endTime_begin)){
			cq.ge("endTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_endTime_begin));
		}
		if(StringUtil.isNotEmpty(query_endTime_end)){
			cq.le("endTime", new SimpleDateFormat("yyyy-MM-dd").parse(query_endTime_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cdActivityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除活动管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CdActivityEntity cdActivity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cdActivity = systemService.getEntity(CdActivityEntity.class, cdActivity.getId());
		String message = "活动管理删除成功";
		try{
			cdActivityService.delMain(cdActivity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "活动管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除活动管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "活动管理删除成功";
		try{
			for(String id:ids.split(",")){
				CdActivityEntity cdActivity = systemService.getEntity(CdActivityEntity.class,
				id
				);
				cdActivityService.delMain(cdActivity);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "活动管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加活动管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CdActivityEntity cdActivity,CdActivityPage cdActivityPage, HttpServletRequest request) {
		List<TransmitWordEntity> transmitWordList =  cdActivityPage.getTransmitWordList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			cdActivityService.addMain(cdActivity, transmitWordList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "活动管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新活动管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CdActivityEntity cdActivity,CdActivityPage cdActivityPage, HttpServletRequest request) {
		List<TransmitWordEntity> transmitWordList =  cdActivityPage.getTransmitWordList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			cdActivityService.updateMain(cdActivity, transmitWordList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新活动管理失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 活动管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CdActivityEntity cdActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdActivity.getId())) {
			cdActivity = cdActivityService.getEntity(CdActivityEntity.class, cdActivity.getId());
			req.setAttribute("cdActivityPage", cdActivity);
		}
		return new ModelAndView("com/cdsoftware/activity/cdActivity-add");
	}
	
	/**
	 * 活动管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CdActivityEntity cdActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdActivity.getId())) {
			cdActivity = cdActivityService.getEntity(CdActivityEntity.class, cdActivity.getId());
			req.setAttribute("cdActivityPage", cdActivity);
		}
		return new ModelAndView("com/cdsoftware/activity/cdActivity-update");
	}
	
	
	/**
	 * 加载明细列表[转发字]
	 * 
	 * @return
	 */
	@RequestMapping(params = "transmitWordList")
	public ModelAndView transmitWordList(CdActivityEntity cdActivity, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		//Object aCTIVITY0 = cdActivity;
		//===================================================================================
		//查询-转发字
	    String hql0 = "from TransmitWordEntity where 1 = 1 AND activity.id = ? ";
	    try{
	    	System.err.println(cdActivity.getId());
	    	List<TransmitWordEntity> transmitWordEntityList = systemService.findHql(hql0,cdActivity.getId());
			System.out.println(transmitWordEntityList.size());
	    	
	    	req.setAttribute("transmitWordList", transmitWordEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/cdsoftware/activity/transmitWordList");
	}
	
	/**
	 * 获得图片
	 * 
	 * @return
	 */
	 @RequestMapping(params="image")
	@ResponseBody
	public void img(String path,HttpServletRequest request,HttpServletResponse response){
		 
		 
		 File filePic =new File(path);
		
		 if(filePic.exists()){
		    FileInputStream is;
			try {
				is = new FileInputStream(filePic);
			
		    int i = is.available(); // 得到文件大小  
		    byte data[] = new byte[i];  
		    is.read(data); // 读数据  
		    is.close();  
		    response.setHeader("content-disposition", "attachment;filename="  
	                + URLEncoder.encode(filePic.getName(), "UTF-8")); 
		    OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
		    toClient.write(data); // 输出数据  
		    toClient.close();  
		    
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			
	 }
	 
	
}
