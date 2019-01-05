package com.cdsoftware.program.service.impl;
import com.cdsoftware.program.service.CdMiniProgramServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.cdsoftware.program.entity.CdMiniProgramEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("cdMiniProgramService")
@Transactional
public class CdMiniProgramServiceImpl extends CommonServiceImpl implements CdMiniProgramServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CdMiniProgramEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CdMiniProgramEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CdMiniProgramEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CdMiniProgramEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CdMiniProgramEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CdMiniProgramEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CdMiniProgramEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{appid}",String.valueOf(t.getAppid()));
 		sql  = sql.replace("#{app_secret}",String.valueOf(t.getAppSecret()));
 		sql  = sql.replace("#{mch_id}",String.valueOf(t.getMchId()));
 		sql  = sql.replace("#{mch_key}",String.valueOf(t.getMchKey()));
 		sql  = sql.replace("#{super_manage_acount}",String.valueOf(t.getSuperManageAcount()));
 		sql  = sql.replace("#{path}",String.valueOf(t.getPath()));
 		sql  = sql.replace("#{official_account}",String.valueOf(t.getOfficialAccount()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}