package com.rpp.trade.service;
import com.rpp.trade.entity.RppTransactionEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppTransactionServiceI extends CommonService{
	
 	public void delete(RppTransactionEntity entity) throws Exception;
 	
 	public Serializable save(RppTransactionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppTransactionEntity entity) throws Exception;
 	
}
