package com.cdsoftware.order.service.impl;
import com.cdsoftware.order.service.CdOrderServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.cdsoftware.order.entity.CdOrderEntity;
import com.cdsoftware.order.entity.CdOrderDetailEntity;

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


@Service("cdOrderService")
@Transactional
public class CdOrderServiceImpl extends CommonServiceImpl implements CdOrderServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CdOrderEntity)entity);
 	}
	
	public void addMain(CdOrderEntity cdOrder,
	        List<CdOrderDetailEntity> cdOrderDetailList){
			//保存主信息
			this.save(cdOrder);
		
			/**保存-订单明细*/
			for(CdOrderDetailEntity cdOrderDetail:cdOrderDetailList){
				//外键设置
				cdOrderDetail.setOrder(cdOrder);
				this.save(cdOrderDetail);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(cdOrder);
	}

	
	public void updateMain(CdOrderEntity cdOrder,
	        List<CdOrderDetailEntity> cdOrderDetailList) {
		//保存主表信息
		this.saveOrUpdate(cdOrder);
		//===================================================================================
		//获取参数
		Object id0 = cdOrder.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-订单明细
	    String hql0 = "from CdOrderDetailEntity where 1 = 1 AND oRDER_ID = ? ";
	    List<CdOrderDetailEntity> cdOrderDetailOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-订单明细
		for(CdOrderDetailEntity oldE:cdOrderDetailOldList){
			boolean isUpdate = false;
				for(CdOrderDetailEntity sendE:cdOrderDetailList){
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
			for(CdOrderDetailEntity cdOrderDetail:cdOrderDetailList){
				if(oConvertUtils.isEmpty(cdOrderDetail.getId())){
					//外键设置
					cdOrderDetail.setOrder(cdOrder);
					this.save(cdOrderDetail);
				}
			}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(cdOrder);
	}

	
	public void delMain(CdOrderEntity cdOrder) {
		//删除主表信息
		this.delete(cdOrder);
		//===================================================================================
		//获取参数
		Object id0 = cdOrder.getId();
		//===================================================================================
		//删除-订单明细
	    String hql0 = "from CdOrderDetailEntity where 1 = 1 AND oRDER_ID = ? ";
	    List<CdOrderDetailEntity> cdOrderDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(cdOrderDetailOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CdOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CdOrderEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CdOrderEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CdOrderEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{program_id}",String.valueOf(t.getProgram().getId()));
 		sql  = sql.replace("#{activity_id}",String.valueOf(t.getActivity().getId()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{count}",String.valueOf(t.getCount()));
 		sql  = sql.replace("#{amount}",String.valueOf(t.getAmount()));
 		sql  = sql.replace("#{order_no}",String.valueOf(t.getOrderNo()));
 		sql  = sql.replace("#{order_id}",String.valueOf(t.getOrderId()));
 		sql  = sql.replace("#{memb_id}",String.valueOf(t.getMemb().getId()));
 		sql  = sql.replace("#{order_time}",String.valueOf(t.getOrderTime()));
 		sql  = sql.replace("#{pay_time}",String.valueOf(t.getPayTime()));
 		sql  = sql.replace("#{state}",String.valueOf(t.getState()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{province}",String.valueOf(t.getProvince()));
 		sql  = sql.replace("#{city}",String.valueOf(t.getCity()));
 		sql  = sql.replace("#{idcard}",String.valueOf(t.getIdcard()));
 		sql  = sql.replace("#{bank_card}",String.valueOf(t.getBankCard()));
 		sql  = sql.replace("#{track_number}",String.valueOf(t.getTrackNumber()));
 		sql  = sql.replace("#{validity}",String.valueOf(t.getValidity()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}