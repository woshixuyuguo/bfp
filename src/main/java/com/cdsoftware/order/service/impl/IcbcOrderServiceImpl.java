package com.cdsoftware.order.service.impl;
import com.cdsoftware.order.service.IcbcOrderServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.cdsoftware.order.entity.IcbcOrderEntity;
import com.cdsoftware.order.entity.IcbcOrderDetailEntity;

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


@Service("icbcOrderService")
@Transactional
public class IcbcOrderServiceImpl extends CommonServiceImpl implements IcbcOrderServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((IcbcOrderEntity)entity);
 	}
	
	public void addMain(IcbcOrderEntity icbcOrder,
	        List<IcbcOrderDetailEntity> icbcOrderDetailList){
			//保存主信息
			this.save(icbcOrder);
		
			/**保存-订单明细*/
			for(IcbcOrderDetailEntity icbcOrderDetail:icbcOrderDetailList){
				//外键设置
				icbcOrderDetail.setOrderId(icbcOrder.getId());
				this.save(icbcOrderDetail);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(icbcOrder);
	}

	
	public void updateMain(IcbcOrderEntity icbcOrder,
	        List<IcbcOrderDetailEntity> icbcOrderDetailList) {
		//保存主表信息
		this.saveOrUpdate(icbcOrder);
		//===================================================================================
		//获取参数
		Object id0 = icbcOrder.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-订单明细
	    String hql0 = "from IcbcOrderDetailEntity where 1 = 1 AND oRDER_ID = ? ";
	    List<IcbcOrderDetailEntity> icbcOrderDetailOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-订单明细
		for(IcbcOrderDetailEntity oldE:icbcOrderDetailOldList){
			boolean isUpdate = false;
				for(IcbcOrderDetailEntity sendE:icbcOrderDetailList){
					//需要更新的明细数据-订单明细
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-订单明细
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-订单明细
			for(IcbcOrderDetailEntity icbcOrderDetail:icbcOrderDetailList){
				if(oConvertUtils.isEmpty(icbcOrderDetail.getId())){
					//外键设置
					icbcOrderDetail.setOrderId(icbcOrder.getId());
					this.save(icbcOrderDetail);
				}
			}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(icbcOrder);
	}

	
	public void delMain(IcbcOrderEntity icbcOrder) {
		//删除主表信息
		this.delete(icbcOrder);
		//===================================================================================
		//获取参数
		Object id0 = icbcOrder.getId();
		//===================================================================================
		//删除-订单明细
	    String hql0 = "from IcbcOrderDetailEntity where 1 = 1 AND oRDER_ID = ? ";
	    List<IcbcOrderDetailEntity> icbcOrderDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(icbcOrderDetailOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(IcbcOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(IcbcOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(IcbcOrderEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,IcbcOrderEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{product_id}",String.valueOf(t.getProductId()));
 		sql  = sql.replace("#{count}",String.valueOf(t.getCount()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{amount}",String.valueOf(t.getAmount()));
 		sql  = sql.replace("#{coupon_id}",String.valueOf(t.getCouponId()));
 		sql  = sql.replace("#{real_amount}",String.valueOf(t.getRealAmount()));
 		sql  = sql.replace("#{state}",String.valueOf(t.getState()));
 		sql  = sql.replace("#{province_id}",String.valueOf(t.getProvinceId()));
 		sql  = sql.replace("#{city_id}",String.valueOf(t.getCityId()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{express_no}",String.valueOf(t.getExpressNo()));
 		sql  = sql.replace("#{express_name}",String.valueOf(t.getExpressName()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}