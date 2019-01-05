package com.cdsoftware.activity.service;
import com.cdsoftware.activity.entity.CdActivityEntity;
import com.cdsoftware.activity.entity.TransmitWordEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface CdActivityServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(CdActivityEntity cdActivity,
	        List<TransmitWordEntity> transmitWordList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(CdActivityEntity cdActivity,
	        List<TransmitWordEntity> transmitWordList);
	public void delMain (CdActivityEntity cdActivity);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CdActivityEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CdActivityEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CdActivityEntity t);
 	
 	public void resetToken();
}
