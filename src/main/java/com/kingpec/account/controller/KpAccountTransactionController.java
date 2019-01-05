package com.kingpec.account.controller;
import com.kingpec.account.entity.KpAccountTransactionEntity;
import com.kingpec.account.service.KpAccountTransactionServiceI;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import java.math.BigDecimal;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 资金变动流水
 * @author onlineGenerator
 * @date 2018-06-25 14:55:24
 * @version V1.0   
 *
 */
@Api(value="KpAccountTransaction",description="资金变动流水",tags="kpAccountTransactionController")
@Controller
@RequestMapping("/kpAccountTransactionController")
public class KpAccountTransactionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(KpAccountTransactionController.class);

	@Autowired
	private KpAccountTransactionServiceI kpAccountTransactionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 资金变动流水列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/kingpec/account/kpAccountTransactionList");
	}
	
	@RequestMapping(params = "list2Show")
	public ModelAndView list2Show(HttpServletRequest request) {
		return new ModelAndView("com/kingpec/account/kpAccountTransactionList2Show");
	}

	
	/**
	 * 
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(KpAccountTransactionEntity kpAccountTransaction,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(KpAccountTransactionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, kpAccountTransaction, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.kpAccountTransactionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	//获得资金汇总
	@RequestMapping(params = "total")
	@ResponseBody
	public AjaxJson totalDeal() {
		AjaxJson j = new AjaxJson();
		Map map=new HashMap<>();
		//获得所有入账金额
		List<Map<String, Object>> list=systemService.findForJdbc("select sum(money) as money from kp_account_transaction where type='1'");
		Map map1=list.get(0);
		map.put("income", map1.get("money")==null?"0.00":map1.get("money"));
		Object object=map1.get("money");
		double in=Double.parseDouble(object.toString());
		list=systemService.findForJdbc("select sum(money) as money from kp_account_transaction where type='2'");
		 map1=list.get(0);
		  object=map1.get("money");
		 double out=Double.parseDouble(object.toString());
		 map.put("out", map1.get("money")==null?"0.00":map1.get("money"));
		 map.put("dif", in-out);
		//获得所有出账金额
		j.setObj(map);
		
		
		return j;
	}
	
	
	//获得资金汇总
	@RequestMapping(params = "totalBranchCompany")
	@ResponseBody
	public AjaxJson totalBranchCompany() {
		AjaxJson j = new AjaxJson();
		Map map=new HashMap<>();
		//获取所有的分公司账户
		List<Map<String, Object>> company=systemService.findForJdbc("select branch_Company from kp_account_transaction group by branch_Company");
	/*	for (int i = 0; i < a.length; i++) {
			
		}*/
		//获得所有入账金额
		List<Map<String, Object>> list=systemService.findForJdbc("select branchCompany as money from kp_account_transaction where type='1'");
		Map map1=list.get(0);
		map.put("income", map1.get("money")==null?"0.00":map1.get("money"));
		Object object=map1.get("money");
		double in=Double.parseDouble(object.toString());
		list=systemService.findForJdbc("select sum(money) as money from kp_account_transaction where type='2'");
		 map1=list.get(0);
		  object=map1.get("money");
		 double out=Double.parseDouble(object.toString());
		 map.put("out", map1.get("money")==null?"0.00":map1.get("money"));
		 map.put("dif", in-out);
		//获得所有出账金额
		j.setObj(map);
		
		
		return j;
	}
	
	/**
	 * 删除资金变动流水
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(KpAccountTransactionEntity kpAccountTransaction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		kpAccountTransaction = systemService.getEntity(KpAccountTransactionEntity.class, kpAccountTransaction.getId());
		message = "资金变动流水删除成功";
		try{
			kpAccountTransactionService.delete(kpAccountTransaction);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资金变动流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除资金变动流水
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金变动流水删除成功";
		try{
			for(String id:ids.split(",")){
				KpAccountTransactionEntity kpAccountTransaction = systemService.getEntity(KpAccountTransactionEntity.class, 
				id
				);
				kpAccountTransactionService.delete(kpAccountTransaction);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "资金变动流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加资金变动流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(KpAccountTransactionEntity kpAccountTransaction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金变动流水添加成功";
		try{
			kpAccountTransactionService.save(kpAccountTransaction);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资金变动流水添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新资金变动流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(KpAccountTransactionEntity kpAccountTransaction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金变动流水更新成功";
		KpAccountTransactionEntity t = kpAccountTransactionService.get(KpAccountTransactionEntity.class, kpAccountTransaction.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(kpAccountTransaction, t);
			kpAccountTransactionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "资金变动流水更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 资金变动流水新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(KpAccountTransactionEntity kpAccountTransaction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(kpAccountTransaction.getId())) {
			kpAccountTransaction = kpAccountTransactionService.getEntity(KpAccountTransactionEntity.class, kpAccountTransaction.getId());
			req.setAttribute("kpAccountTransactionPage", kpAccountTransaction);
		}
		return new ModelAndView("com/kingpec/account/kpAccountTransaction-add");
	}
	/**
	 * 资金变动流水编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(KpAccountTransactionEntity kpAccountTransaction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(kpAccountTransaction.getId())) {
			kpAccountTransaction = kpAccountTransactionService.getEntity(KpAccountTransactionEntity.class, kpAccountTransaction.getId());
			req.setAttribute("kpAccountTransactionPage", kpAccountTransaction);
		}
		return new ModelAndView("com/kingpec/account/kpAccountTransaction-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","kpAccountTransactionController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(KpAccountTransactionEntity kpAccountTransaction,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(KpAccountTransactionEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, kpAccountTransaction, request.getParameterMap());
		List<KpAccountTransactionEntity> kpAccountTransactions = this.kpAccountTransactionService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"资金变动流水");
		modelMap.put(NormalExcelConstants.CLASS,KpAccountTransactionEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资金变动流水列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,kpAccountTransactions);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(KpAccountTransactionEntity kpAccountTransaction,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"资金变动流水");
    	modelMap.put(NormalExcelConstants.CLASS,KpAccountTransactionEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资金变动流水列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<KpAccountTransactionEntity> listKpAccountTransactionEntitys = ExcelImportUtil.importExcel(file.getInputStream(),KpAccountTransactionEntity.class,params);
				for (KpAccountTransactionEntity kpAccountTransaction : listKpAccountTransactionEntitys) {
					kpAccountTransactionService.save(kpAccountTransaction);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="资金变动流水列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<KpAccountTransactionEntity>> list() {
		List<KpAccountTransactionEntity> listKpAccountTransactions=kpAccountTransactionService.getList(KpAccountTransactionEntity.class);
		return Result.success(listKpAccountTransactions);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取资金变动流水信息",notes="根据ID获取资金变动流水信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		KpAccountTransactionEntity task = kpAccountTransactionService.get(KpAccountTransactionEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取资金变动流水信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建资金变动流水")
	public ResponseMessage<?> create(@ApiParam(name="资金变动流水对象")@RequestBody KpAccountTransactionEntity kpAccountTransaction, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<KpAccountTransactionEntity>> failures = validator.validate(kpAccountTransaction);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			kpAccountTransactionService.save(kpAccountTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资金变动流水信息保存失败");
		}
		return Result.success(kpAccountTransaction);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新资金变动流水",notes="更新资金变动流水")
	public ResponseMessage<?> update(@ApiParam(name="资金变动流水对象")@RequestBody KpAccountTransactionEntity kpAccountTransaction) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<KpAccountTransactionEntity>> failures = validator.validate(kpAccountTransaction);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			kpAccountTransactionService.saveOrUpdate(kpAccountTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新资金变动流水信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新资金变动流水信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除资金变动流水")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			kpAccountTransactionService.deleteEntityById(KpAccountTransactionEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资金变动流水删除失败");
		}

		return Result.success();
	}
}
