package com.kingpec.transaction.controller;
import com.kingpec.site.entity.KpSiteManageEntity;
import com.kingpec.transaction.entity.KpTransactionEntity;
import com.kingpec.transaction.service.KpTransactionServiceI;
import com.sun.star.bridge.oleautomation.Date;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import static org.hamcrest.CoreMatchers.nullValue;

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
 * @Description: 交易流水
 * @author onlineGenerator
 * @date 2018-06-25 11:43:00
 * @version V1.0   
 *
 */
@Api(value="KpTransaction",description="交易流水",tags="kpTransactionController")
@Controller
@RequestMapping("/kpTransactionController")
public class KpTransactionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(KpTransactionController.class);

	@Autowired
	private KpTransactionServiceI kpTransactionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 交易流水列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/kingpec/transaction/kpTransactionList");
	}
	
	/* 
	 * 统计页面
	 * */
	@RequestMapping(params = "statistics")
	public ModelAndView statistics(HttpServletRequest request) {
		return new ModelAndView("com/kingpec/transaction/kpStatistics");
	}
	/**
	 * 交易流水列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list2Show")
	public ModelAndView list2Show(HttpServletRequest request) {
		return new ModelAndView("com/kingpec/transaction/kpTransactionList2Show");
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
	public void datagrid(KpTransactionEntity kpTransaction,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(KpTransactionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, kpTransaction, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.kpTransactionService.getDataGridReturn(cq, true);
		dataGrid.setFooter("tranMoney");
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	@RequestMapping(params = "datagridtotal")
	@ResponseBody
	public AjaxJson datagridtotal(KpTransactionEntity kpTransaction,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		AjaxJson j = new AjaxJson();
		 DecimalFormat dFormat=new DecimalFormat("#.00");
		CriteriaQuery cq = new CriteriaQuery(KpTransactionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, kpTransaction, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.kpTransactionService.getDataGridReturn(cq, true);
		//TagUtil.datagrid(response, dataGrid);
		double total=0;
		List<KpTransactionEntity> list=dataGrid.getResults();
		for (KpTransactionEntity kpTransactionEntity : list) {
			total=total+kpTransactionEntity.getTranMoney();
		}
		System.err.println(dFormat.format(total));
		j.setObj(dFormat.format(total));
		return j;
	}
	
	@RequestMapping(params = "totalDeal")
	@ResponseBody
	public AjaxJson totalDeal() {
		AjaxJson j = new AjaxJson();
		List<Map<String, Object>> list=kpTransactionService.findForJdbc("select sum(tran_money) as money from kp_transaction");
		Map map=list.get(0);
		System.out.println(map);
		j.setObj(map.get("money")==null?"0.00":map.get("money"));
		
		
		return j;
	}
	
	@RequestMapping(params = "totalDealYesterday")
	@ResponseBody
	public AjaxJson totalDealYesterday() {
		AjaxJson j = new AjaxJson();
		List<Map<String, Object>> list=kpTransactionService.findForJdbc("select sum(tran_money) as money from kp_transaction where tran_date=to_char(sysdate-1,'yyyyMMdd') ");
		Map map=list.get(0);
		System.out.println(map);
		j.setObj(map.get("money")==null?"0.00":map.get("money"));
		
		
		return j;
	}
	
	/**
	 * 删除交易流水
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(KpTransactionEntity kpTransaction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		kpTransaction = systemService.getEntity(KpTransactionEntity.class, kpTransaction.getId());
		message = "交易流水删除成功";
		try{
			kpTransactionService.delete(kpTransaction);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "交易流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除交易流水
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "交易流水删除成功";
		try{
			for(String id:ids.split(",")){
				KpTransactionEntity kpTransaction = systemService.getEntity(KpTransactionEntity.class, 
				id
				);
				kpTransactionService.delete(kpTransaction);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "交易流水删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加交易流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(KpTransactionEntity kpTransaction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "交易流水添加成功";
		try{
			kpTransactionService.save(kpTransaction);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "交易流水添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新交易流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(KpTransactionEntity kpTransaction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "交易流水更新成功";
		KpTransactionEntity t = kpTransactionService.get(KpTransactionEntity.class, kpTransaction.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(kpTransaction, t);
			kpTransactionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "交易流水更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 交易流水新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(KpTransactionEntity kpTransaction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(kpTransaction.getId())) {
			kpTransaction = kpTransactionService.getEntity(KpTransactionEntity.class, kpTransaction.getId());
			req.setAttribute("kpTransactionPage", kpTransaction);
		}
		return new ModelAndView("com/kingpec/transaction/kpTransaction-add");
	}
	/**
	 * 交易流水编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(KpTransactionEntity kpTransaction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(kpTransaction.getId())) {
			kpTransaction = kpTransactionService.getEntity(KpTransactionEntity.class, kpTransaction.getId());
			req.setAttribute("kpTransactionPage", kpTransaction);
		}
		return new ModelAndView("com/kingpec/transaction/kpTransaction-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","kpTransactionController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(KpTransactionEntity kpTransaction,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(KpTransactionEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, kpTransaction, request.getParameterMap());
		List<KpTransactionEntity> kpTransactions = this.kpTransactionService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"交易流水");
		modelMap.put(NormalExcelConstants.CLASS,KpTransactionEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("交易流水列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,kpTransactions);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(KpTransactionEntity kpTransaction,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"交易流水");
    	modelMap.put(NormalExcelConstants.CLASS,KpTransactionEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("交易流水列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<KpTransactionEntity> listKpTransactionEntitys = ExcelImportUtil.importExcel(file.getInputStream(),KpTransactionEntity.class,params);
				for (KpTransactionEntity kpTransaction : listKpTransactionEntitys) {
					//根据终端号获得站点信息
					KpSiteManageEntity kpSiteManageEntity=systemService.findUniqueByProperty(KpSiteManageEntity.class, "termNo", kpTransaction.getTermNo().replaceAll("\t", ""));
					kpTransaction.setSiteName(kpSiteManageEntity.getSiteName());
					kpTransaction.setMercNo(kpSiteManageEntity.getMercNo());
					kpTransaction.setTranState("交易成功");
					kpTransaction.setTranCode(kpTransaction.getTranDate().replaceAll("\t", "")+kpTransaction.getTransTime().replaceAll("\t", "")+kpTransaction.getTermNo().replaceAll("\t", ""));
					SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
					SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date date=simpleDateFormat.parse(kpTransaction.getTranDate().replaceAll("\t", "")+kpTransaction.getTransTime().replaceAll("\t", ""));
					kpTransaction.setTranTime(simpleDateFormat2.format(date));
					kpTransactionService.save(kpTransaction);
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
	@ApiOperation(value="交易流水列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<KpTransactionEntity>> list() {
		List<KpTransactionEntity> listKpTransactions=kpTransactionService.getList(KpTransactionEntity.class);
		return Result.success(listKpTransactions);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取交易流水信息",notes="根据ID获取交易流水信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		KpTransactionEntity task = kpTransactionService.get(KpTransactionEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取交易流水信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建交易流水")
	public ResponseMessage<?> create(@ApiParam(name="交易流水对象")@RequestBody KpTransactionEntity kpTransaction, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<KpTransactionEntity>> failures = validator.validate(kpTransaction);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			kpTransactionService.save(kpTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("交易流水信息保存失败");
		}
		return Result.success(kpTransaction);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新交易流水",notes="更新交易流水")
	public ResponseMessage<?> update(@ApiParam(name="交易流水对象")@RequestBody KpTransactionEntity kpTransaction) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<KpTransactionEntity>> failures = validator.validate(kpTransaction);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			kpTransactionService.saveOrUpdate(kpTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新交易流水信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新交易流水信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除交易流水")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			kpTransactionService.deleteEntityById(KpTransactionEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("交易流水删除失败");
		}

		return Result.success();
	}
}
