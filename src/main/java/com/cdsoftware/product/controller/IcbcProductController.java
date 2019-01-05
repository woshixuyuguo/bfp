package com.cdsoftware.product.controller;
import com.cdsoftware.product.entity.IcbcProductEntity;
import com.cdsoftware.product.service.IcbcProductServiceI;
import com.cdsoftware.product.page.IcbcProductPage;
import com.cdsoftware.product.entity.IcbcProductPictureEntity;
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
 * @Description: 商品管理
 * @author onlineGenerator
 * @date 2018-04-12 15:37:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/icbcProductController")
public class IcbcProductController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IcbcProductController.class);

	@Autowired
	private IcbcProductServiceI icbcProductService;
	@Autowired
	private SystemService systemService;


	/**
	 * 商品管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView icbcProduct(HttpServletRequest request) {
		return new ModelAndView("com/cdsoftware/product/icbcProductList");
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
	public void datagrid(IcbcProductEntity icbcProduct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IcbcProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, icbcProduct);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.icbcProductService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(IcbcProductEntity icbcProduct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		icbcProduct = systemService.getEntity(IcbcProductEntity.class, icbcProduct.getId());
		String message = "商品管理删除成功";
		try{
			icbcProductService.delMain(icbcProduct);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除商品管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "商品管理删除成功";
		try{
			for(String id:ids.split(",")){
				IcbcProductEntity icbcProduct = systemService.getEntity(IcbcProductEntity.class,
				id
				);
				icbcProductService.delMain(icbcProduct);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加商品管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(IcbcProductEntity icbcProduct,IcbcProductPage icbcProductPage, HttpServletRequest request) {
		List<IcbcProductPictureEntity> icbcProductPictureList =  icbcProductPage.getIcbcProductPictureList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			icbcProductService.addMain(icbcProduct, icbcProductPictureList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新商品管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(IcbcProductEntity icbcProduct,IcbcProductPage icbcProductPage, HttpServletRequest request) {
		List<IcbcProductPictureEntity> icbcProductPictureList =  icbcProductPage.getIcbcProductPictureList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		if (icbcProductPictureList.size()==0) {
			message = "至少有一张图片";
		}else{
		try{
			icbcProductService.updateMain(icbcProduct, icbcProductPictureList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新商品管理失败";
			throw new BusinessException(e.getMessage());
		}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 商品管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(IcbcProductEntity icbcProduct, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcProduct.getId())) {
			icbcProduct = icbcProductService.getEntity(IcbcProductEntity.class, icbcProduct.getId());
			req.setAttribute("icbcProductPage", icbcProduct);
		}
		return new ModelAndView("com/cdsoftware/product/icbcProduct-add");
	}
	
	/**
	 * 商品管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(IcbcProductEntity icbcProduct, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icbcProduct.getId())) {
			icbcProduct = icbcProductService.getEntity(IcbcProductEntity.class, icbcProduct.getId());
			req.setAttribute("icbcProductPage", icbcProduct);
		}
		return new ModelAndView("com/cdsoftware/product/icbcProduct-update");
	}
	
	
	/**
	 * 加载明细列表[商品图片]
	 * 
	 * @return
	 */
	@RequestMapping(params = "icbcProductPictureList")
	public ModelAndView icbcProductPictureList(IcbcProductEntity icbcProduct, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = icbcProduct.getId();
		//===================================================================================
		//查询-商品图片
	    String hql0 = "from IcbcProductPictureEntity where 1 = 1 AND pRODUCT_ID = ? ";
	    try{
	    	List<IcbcProductPictureEntity> icbcProductPictureEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("icbcProductPictureList", icbcProductPictureEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/cdsoftware/product/icbcProductPictureList");
	}
	
}
