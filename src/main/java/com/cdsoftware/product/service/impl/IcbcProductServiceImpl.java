package com.cdsoftware.product.service.impl;
import com.cdsoftware.product.service.IcbcProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.cdsoftware.product.entity.IcbcProductEntity;
import com.cdsoftware.product.entity.IcbcProductPictureEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("icbcProductService")
@Transactional
public class IcbcProductServiceImpl extends CommonServiceImpl implements IcbcProductServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((IcbcProductEntity)entity);
 	}
	
	public void addMain(IcbcProductEntity icbcProduct,
	        List<IcbcProductPictureEntity> icbcProductPictureList){
			//保存主信息
			this.save(icbcProduct);
		
			/**保存-商品图片*/
			int i=0;
			for(IcbcProductPictureEntity icbcProductPicture:icbcProductPictureList){
				//外键设置
				icbcProductPicture.setProductId(icbcProduct.getId());
				/*if (i==0) {
					icbcProduct.setImg(icbcProductPicture.getAddress());
					this.saveOrUpdate(icbcProduct);
				}else if ("Y".equals(icbcProductPicture.getIsMain())) {
					icbcProduct.setImg(icbcProductPicture.getAddress());
					this.saveOrUpdate(icbcProduct);
				}
				i++;*/
				
				this.save(icbcProductPicture);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(icbcProduct);
	}

	
	public void updateMain(IcbcProductEntity icbcProduct,
	        List<IcbcProductPictureEntity> icbcProductPictureList) {
		//保存主表信息
		this.saveOrUpdate(icbcProduct);
		//保存主图片
		/**保存-商品图片*/
		int i=0;
		for(IcbcProductPictureEntity icbcProductPicture:icbcProductPictureList){
			//外键设置
			icbcProductPicture.setProductId(icbcProduct.getId());
			/*if (i==0) {
				icbcProduct.setImg(icbcProductPicture.getAddress());
				this.saveOrUpdate(icbcProduct);
			}else if ("Y".equals(icbcProductPicture.getIsMain())) {
				icbcProduct.setImg(icbcProductPicture.getAddress());
				this.saveOrUpdate(icbcProduct);
			}
			i++;*/
			
		}
		//===================================================================================
		//获取参数
		Object id0 = icbcProduct.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-商品图片
	    String hql0 = "from IcbcProductPictureEntity where 1 = 1 AND pRODUCT_ID = ? ";
	    List<IcbcProductPictureEntity> icbcProductPictureOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-商品图片
		for(IcbcProductPictureEntity oldE:icbcProductPictureOldList){
			boolean isUpdate = false;
				for(IcbcProductPictureEntity sendE:icbcProductPictureList){
					//需要更新的明细数据-商品图片
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-商品图片
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-商品图片
			for(IcbcProductPictureEntity icbcProductPicture:icbcProductPictureList){
				if(oConvertUtils.isEmpty(icbcProductPicture.getId())){
					//外键设置
					icbcProductPicture.setProductId(icbcProduct.getId());
					this.save(icbcProductPicture);
				}
			}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(icbcProduct);
	}

	
	public void delMain(IcbcProductEntity icbcProduct) {
		//删除主表信息
		this.delete(icbcProduct);
		//===================================================================================
		//获取参数
		Object id0 = icbcProduct.getId();
		//===================================================================================
		//删除-商品图片
	    String hql0 = "from IcbcProductPictureEntity where 1 = 1 AND pRODUCT_ID = ? ";
	    List<IcbcProductPictureEntity> icbcProductPictureOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(icbcProductPictureOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(IcbcProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(IcbcProductEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(IcbcProductEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,IcbcProductEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{original_price}",String.valueOf(t.getOriginalPrice()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{describe}",String.valueOf(t.getDescribe()));
 		sql  = sql.replace("#{have_address}",String.valueOf(t.getHaveAddress()));
 		sql  = sql.replace("#{have_code}",String.valueOf(t.getHaveCode()));
 		sql  = sql.replace("#{code_length}",String.valueOf(t.getCodeLength()));
 		sql  = sql.replace("#{stock}",String.valueOf(t.getStock()));
 		sql  = sql.replace("#{sale_count}",String.valueOf(t.getSaleCount()));
 		sql  = sql.replace("#{rule}",String.valueOf(t.getRule()));
 		sql  = sql.replace("#{parameter}",String.valueOf(t.getParameter()));
 		sql  = sql.replace("#{service}",String.valueOf(t.getService()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}