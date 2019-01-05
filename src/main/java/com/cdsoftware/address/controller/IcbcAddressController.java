package com.cdsoftware.address.controller;
import com.cdsoftware.address.entity.IcbcAddressEntity;
import com.cdsoftware.address.service.IcbcAddressServiceI;
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
 * @Description: 收货地址
 * @author onlineGenerator
 * @date 2018-05-14 09:18:06
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/icbcAddressController")
public class IcbcAddressController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IcbcAddressController.class);

	@Autowired
	private IcbcAddressServiceI icbcAddressService;
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
	 * 收货地址列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView icbcAddress(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/address/icbcAddressList");
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
	public void datagrid(IcbcAddressEntity icbcAddress,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IcbcAddressEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, icbcAddress, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.icbcAddressService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除收货地址
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(IcbcAddressEntity icbcAddress, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		icbcAddress = systemService.getEntity(IcbcAddressEntity.class, icbcAddress.getId());
		message = "收货地址删除成功";
		try{
			icbcAddressService.delete(icbcAddress);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收货地址删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除收货地址
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "收货地址删除成功";
		try{
			for(String id:ids.split(",")){
				IcbcAddressEntity icbcAddress = systemService.getEntity(IcbcAddressEntity.class, 
				id
				);
				icbcAddressService.delete(icbcAddress);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "收货地址删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收货地址
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IcbcAddressEntity icbcAddress, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "收货地址添加成功";
		try{
			icbcAddressService.save(icbcAddress);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收货地址添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新收货地址
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(IcbcAddressEntity icbcAddress, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "收货地址更新成功";
		IcbcAddressEntity t = icbcAddressService.get(IcbcAddressEntity.class, icbcAddress.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(icbcAddress, t);
			icbcAddressService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "收货地址更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 收货地址新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(IcbcAddressEntity icbcAddress, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcAddress.getId())) {
			icbcAddress = icbcAddressService.getEntity(IcbcAddressEntity.class, icbcAddress.getId());
			req.setAttribute("icbcAddressPage", icbcAddress);
		}
		return new ModelAndView("com/cdsoftware/address/icbcAddress-add");
	}
	/**
	 * 收货地址编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(IcbcAddressEntity icbcAddress, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcAddress.getId())) {
			icbcAddress = icbcAddressService.getEntity(IcbcAddressEntity.class, icbcAddress.getId());
			req.setAttribute("icbcAddressPage", icbcAddress);
		}
		return new ModelAndView("com/cdsoftware/address/icbcAddress-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/address/icbcAddressUpload");
	}
	
	
}
