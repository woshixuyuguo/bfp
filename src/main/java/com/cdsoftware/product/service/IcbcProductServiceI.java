package com.cdsoftware.product.service;
import com.cdsoftware.product.entity.IcbcProductEntity;
import com.cdsoftware.product.entity.IcbcProductPictureEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface IcbcProductServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(IcbcProductEntity icbcProduct,
	        List<IcbcProductPictureEntity> icbcProductPictureList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(IcbcProductEntity icbcProduct,
	        List<IcbcProductPictureEntity> icbcProductPictureList);
	public void delMain (IcbcProductEntity icbcProduct);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(IcbcProductEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(IcbcProductEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(IcbcProductEntity t);
}
