package com.cdsoftware.order.controller;
import com.cdsoftware.order.entity.IcbcOrderEntity;
import com.cdsoftware.order.service.IcbcOrderServiceI;
import com.cdsoftware.order.page.IcbcOrderPage;
import com.cdsoftware.order.entity.IcbcOrderDetailEntity;
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
 * @Description: 工商订单
 * @author onlineGenerator
 * @date 2018-04-12 15:21:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/icbcOrderController")
public class IcbcOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IcbcOrderController.class);

	@Autowired
	private IcbcOrderServiceI icbcOrderService;
	@Autowired
	private SystemService systemService;


	/**
	 * 工商订单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView icbcOrder(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/order/icbcOrderList");
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
	public void datagrid(IcbcOrderEntity icbcOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IcbcOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, icbcOrder);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.icbcOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工商订单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(IcbcOrderEntity icbcOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		icbcOrder = systemService.getEntity(IcbcOrderEntity.class, icbcOrder.getId());
		String message = "工商订单删除成功";
		try{
			icbcOrderService.delMain(icbcOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工商订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除工商订单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "工商订单删除成功";
		try{
			for(String id:ids.split(",")){
				IcbcOrderEntity icbcOrder = systemService.getEntity(IcbcOrderEntity.class,
				id
				);
				icbcOrderService.delMain(icbcOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工商订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加工商订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IcbcOrderEntity icbcOrder,IcbcOrderPage icbcOrderPage, HttpServletRequest request) {
		List<IcbcOrderDetailEntity> icbcOrderDetailList =  icbcOrderPage.getIcbcOrderDetailList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			icbcOrderService.addMain(icbcOrder, icbcOrderDetailList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工商订单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新工商订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(IcbcOrderEntity icbcOrder,IcbcOrderPage icbcOrderPage, HttpServletRequest request) {
		List<IcbcOrderDetailEntity> icbcOrderDetailList =  icbcOrderPage.getIcbcOrderDetailList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			icbcOrderService.updateMain(icbcOrder, icbcOrderDetailList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新工商订单失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工商订单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(IcbcOrderEntity icbcOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcOrder.getId())) {
			icbcOrder = icbcOrderService.getEntity(IcbcOrderEntity.class, icbcOrder.getId());
			req.setAttribute("icbcOrderPage", icbcOrder);
		}
		return new ModelAndView("com/cdsoftware/order/icbcOrder-add");
	}
	
	/**
	 * 工商订单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(IcbcOrderEntity icbcOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcOrder.getId())) {
			icbcOrder = icbcOrderService.getEntity(IcbcOrderEntity.class, icbcOrder.getId());
			req.setAttribute("icbcOrderPage", icbcOrder);
		}
		return new ModelAndView("com/cdsoftware/order/icbcOrder-update");
	}
	
	
	/**
	 * 加载明细列表[订单明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "icbcOrderDetailList")
	public ModelAndView icbcOrderDetailList(IcbcOrderEntity icbcOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = icbcOrder.getId();
		//===================================================================================
		//查询-订单明细
	    String hql0 = "from IcbcOrderDetailEntity where 1 = 1 AND oRDER_ID = ? ";
	    try{
	    	List<IcbcOrderDetailEntity> icbcOrderDetailEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("icbcOrderDetailList", icbcOrderDetailEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/cdsoftware/order/icbcOrderDetailList");
	}
	
}
