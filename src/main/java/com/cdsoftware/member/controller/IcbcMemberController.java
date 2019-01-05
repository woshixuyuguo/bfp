package com.cdsoftware.member.controller;
import com.cdsoftware.member.entity.IcbcMemberEntity;
import com.cdsoftware.member.service.IcbcMemberServiceI;
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
 * @Description: 工商银行会员
 * @author onlineGenerator
 * @date 2018-04-12 14:38:28
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/icbcMemberController")
public class IcbcMemberController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IcbcMemberController.class);

	@Autowired
	private IcbcMemberServiceI icbcMemberService;
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
	 * 工商银行会员列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView icbcMember(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/member/icbcMemberList");
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
	public void datagrid(IcbcMemberEntity icbcMember,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IcbcMemberEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, icbcMember, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.icbcMemberService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工商银行会员
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(IcbcMemberEntity icbcMember, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		icbcMember = systemService.getEntity(IcbcMemberEntity.class, icbcMember.getId());
		message = "工商银行会员删除成功";
		try{
			icbcMemberService.delete(icbcMember);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工商银行会员删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工商银行会员
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "工商银行会员删除成功";
		try{
			for(String id:ids.split(",")){
				IcbcMemberEntity icbcMember = systemService.getEntity(IcbcMemberEntity.class, 
				id
				);
				icbcMemberService.delete(icbcMember);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工商银行会员删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工商银行会员
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IcbcMemberEntity icbcMember, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工商银行会员添加成功";
		try{
			icbcMemberService.save(icbcMember);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工商银行会员添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工商银行会员
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(IcbcMemberEntity icbcMember, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "工商银行会员更新成功";
		IcbcMemberEntity t = icbcMemberService.get(IcbcMemberEntity.class, icbcMember.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(icbcMember, t);
			icbcMemberService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工商银行会员更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 工商银行会员新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(IcbcMemberEntity icbcMember, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcMember.getId())) {
			icbcMember = icbcMemberService.getEntity(IcbcMemberEntity.class, icbcMember.getId());
			req.setAttribute("icbcMemberPage", icbcMember);
		}
		return new ModelAndView("com/cdsoftware/member/icbcMember-add");
	}
	/**
	 * 工商银行会员编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(IcbcMemberEntity icbcMember, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcMember.getId())) {
			icbcMember = icbcMemberService.getEntity(IcbcMemberEntity.class, icbcMember.getId());
			req.setAttribute("icbcMemberPage", icbcMember);
		}
		return new ModelAndView("com/cdsoftware/member/icbcMember-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/cdsoftware/member/icbcMemberUpload");
	}
	
	
}
