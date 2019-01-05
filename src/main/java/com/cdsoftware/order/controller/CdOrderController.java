package com.cdsoftware.order.controller;
import com.cdsoftware.order.entity.CdOrderEntity;
import com.cdsoftware.order.service.CdOrderServiceI;
import com.cdsoftware.order.page.CdOrderPage;
import com.cdsoftware.order.entity.CdOrderDetailEntity;
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


/**   
 * @Title: Controller
 * @Description: 订单管理
 * @author onlineGenerator
 * @date 2018-03-27 13:51:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/cdOrderController")
public class CdOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CdOrderController.class);

	@Autowired
	private CdOrderServiceI cdOrderService;
	@Autowired
	private SystemService systemService;


	/**
	 * 订单管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView cdOrder(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/order/cdOrderList");
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
	public void datagrid(CdOrderEntity cdOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CdOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cdOrder);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cdOrderService.getDataGridReturn(cq, true);
		dataGrid.setFooter("");
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CdOrderEntity cdOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cdOrder = systemService.getEntity(CdOrderEntity.class, cdOrder.getId());
		String message = "订单管理删除成功";
		try{
			cdOrderService.delMain(cdOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除订单管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "订单管理删除成功";
		try{
			for(String id:ids.split(",")){
				CdOrderEntity cdOrder = systemService.getEntity(CdOrderEntity.class,
				id
				);
				cdOrderService.delMain(cdOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订单管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加订单管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CdOrderEntity cdOrder,CdOrderPage cdOrderPage, HttpServletRequest request) {
		List<CdOrderDetailEntity> cdOrderDetailList =  cdOrderPage.getCdOrderDetailList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			cdOrderService.addMain(cdOrder, cdOrderDetailList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新订单管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CdOrderEntity cdOrder,CdOrderPage cdOrderPage, HttpServletRequest request) {
		List<CdOrderDetailEntity> cdOrderDetailList =  cdOrderPage.getCdOrderDetailList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			cdOrderService.updateMain(cdOrder, cdOrderDetailList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新订单管理失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 订单管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CdOrderEntity cdOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdOrder.getId())) {
			cdOrder = cdOrderService.getEntity(CdOrderEntity.class, cdOrder.getId());
			req.setAttribute("cdOrderPage", cdOrder);
		}
		return new ModelAndView("com/cdsoftware/order/cdOrder-add");
	}
	
	/**
	 * 订单管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CdOrderEntity cdOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cdOrder.getId())) {
			cdOrder = cdOrderService.getEntity(CdOrderEntity.class, cdOrder.getId());
			req.setAttribute("cdOrderPage", cdOrder);
		}
		return new ModelAndView("com/cdsoftware/order/cdOrder-update");
	}
	
	
	/**
	 * 加载明细列表[订单明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "cdOrderDetailList")
	public ModelAndView cdOrderDetailList(CdOrderEntity cdOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = cdOrder.getId();
		//===================================================================================
		//查询-订单明细
	    String hql0 = "from CdOrderDetailEntity where 1 = 1 AND oRDER_ID = ? ";
	    try{
	    	List<CdOrderDetailEntity> cdOrderDetailEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("cdOrderDetailList", cdOrderDetailEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/cdsoftware/order/cdOrderDetailList");
	}
	
}
