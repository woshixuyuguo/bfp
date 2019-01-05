package com.cdsoftware.member.service.impl;
import com.cdsoftware.member.service.CdMemberServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.cdsoftware.member.entity.CdMemberEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("cdMemberService")
@Transactional
public class CdMemberServiceImpl extends CommonServiceImpl implements CdMemberServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CdMemberEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CdMemberEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CdMemberEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CdMemberEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CdMemberEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CdMemberEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CdMemberEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{pet_name}",String.valueOf(t.getPetName()));
 		sql  = sql.replace("#{open_id}",String.valueOf(t.getOpenId()));
 		sql  = sql.replace("#{img_url}",String.valueOf(t.getImgUrl()));
 		sql  = sql.replace("#{state}",String.valueOf(t.getState()));
 		sql  = sql.replace("#{session_key}",String.valueOf(t.getSessionKey()));
 		sql  = sql.replace("#{session_id}",String.valueOf(t.getSessionId()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{money}",String.valueOf(t.getMoney()));
 		sql  = sql.replace("#{lng}",String.valueOf(t.getLng()));
 		sql  = sql.replace("#{lat}",String.valueOf(t.getLat()));
 		sql  = sql.replace("#{program_id}",String.valueOf(t.getProgram().getId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}