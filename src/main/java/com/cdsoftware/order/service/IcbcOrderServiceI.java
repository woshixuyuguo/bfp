package com.cdsoftware.order.service;
import com.cdsoftware.order.entity.IcbcOrderEntity;
import com.cdsoftware.order.entity.IcbcOrderDetailEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface IcbcOrderServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(IcbcOrderEntity icbcOrder,
	        List<IcbcOrderDetailEntity> icbcOrderDetailList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(IcbcOrderEntity icbcOrder,
	        List<IcbcOrderDetailEntity> icbcOrderDetailList);
	public void delMain (IcbcOrderEntity icbcOrder);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(IcbcOrderEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(IcbcOrderEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(IcbcOrderEntity t);
}
