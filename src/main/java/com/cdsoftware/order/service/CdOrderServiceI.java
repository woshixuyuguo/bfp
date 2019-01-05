package com.cdsoftware.order.service;
import com.cdsoftware.order.entity.CdOrderEntity;
import com.cdsoftware.order.entity.CdOrderDetailEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface CdOrderServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(CdOrderEntity cdOrder,
	        List<CdOrderDetailEntity> cdOrderDetailList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(CdOrderEntity cdOrder,
	        List<CdOrderDetailEntity> cdOrderDetailList);
	public void delMain (CdOrderEntity cdOrder);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CdOrderEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CdOrderEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CdOrderEntity t);
}
