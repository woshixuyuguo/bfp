package com.kingpec.transaction.service.impl;
import com.kingpec.transaction.service.KpTransactionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.kingpec.transaction.entity.KpTransactionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("kpTransactionService")
@Transactional
public class KpTransactionServiceImpl extends CommonServiceImpl implements KpTransactionServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
 	public void delete(KpTransactionEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(KpTransactionEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(KpTransactionEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(KpTransactionEntity t) throws Exception{
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(KpTransactionEntity t) throws Exception{
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(KpTransactionEntity t) throws Exception{
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	}
 	
 	private Map<String,Object> populationMap(KpTransactionEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("site_name", t.getSiteName());
		map.put("merc_no", t.getMercNo());
		map.put("term_no", t.getTermNo());
		map.put("tran_time", t.getTranTime());
		map.put("tran_code", t.getTranCode());
		map.put("tran_money", t.getTranMoney());
		map.put("tran_state", t.getTranState());
		map.put("settle_date", t.getSettleDate());
		map.put("tran_type", t.getTranType());
		map.put("tran_date", t.getTranDate());
		map.put("trans_time", t.getTransTime());
		map.put("card_no", t.getCardNo());
		map.put("store_no", t.getStoreNo());
		map.put("branch_name", t.getBranchName());
		map.put("tran_fee", t.getTranFee());
		map.put("tran_act", t.getTranAct());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,KpTransactionEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{site_name}",String.valueOf(t.getSiteName()));
 		sql  = sql.replace("#{merc_no}",String.valueOf(t.getMercNo()));
 		sql  = sql.replace("#{term_no}",String.valueOf(t.getTermNo()));
 		sql  = sql.replace("#{tran_time}",String.valueOf(t.getTranTime()));
 		sql  = sql.replace("#{tran_code}",String.valueOf(t.getTranCode()));
 		sql  = sql.replace("#{tran_money}",String.valueOf(t.getTranMoney()));
 		sql  = sql.replace("#{tran_state}",String.valueOf(t.getTranState()));
 		sql  = sql.replace("#{settle_date}",String.valueOf(t.getSettleDate()));
 		sql  = sql.replace("#{tran_type}",String.valueOf(t.getTranType()));
 		sql  = sql.replace("#{tran_date}",String.valueOf(t.getTranDate()));
 		sql  = sql.replace("#{trans_time}",String.valueOf(t.getTransTime()));
 		sql  = sql.replace("#{card_no}",String.valueOf(t.getCardNo()));
 		sql  = sql.replace("#{store_no}",String.valueOf(t.getStoreNo()));
 		sql  = sql.replace("#{branch_name}",String.valueOf(t.getBranchName()));
 		sql  = sql.replace("#{tran_fee}",String.valueOf(t.getTranFee()));
 		sql  = sql.replace("#{tran_act}",String.valueOf(t.getTranAct()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("kp_transaction",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
 	
 	private void executeSqlEnhance(String sqlEnhance,KpTransactionEntity t){
	 	Map<String,Object> data = populationMap(t);
	 	sqlEnhance = ResourceUtil.formateSQl(sqlEnhance, data);
	 	boolean isMiniDao = false;
	 	try {
	 		data = ResourceUtil.minidaoReplaceExtendSqlSysVar(data);
	 		sqlEnhance = FreemarkerParseFactory.parseTemplateContent(sqlEnhance, data);
			isMiniDao = true;
		} catch (Exception e) {
		}
	 	String [] sqls = sqlEnhance.split(";");
		for(String sql:sqls){
			if(sql == null || sql.toLowerCase().trim().equals("")){
				continue;
			}
			int num = 0;
			if(isMiniDao){
				num = namedParameterJdbcTemplate.update(sql, data);
			}else{
				num = this.executeSql(sql);
			}
		}
 	}
}