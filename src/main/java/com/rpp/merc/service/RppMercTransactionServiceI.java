package com.rpp.merc.service;
import com.rpp.merc.entity.RppMercTransactionEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppMercTransactionServiceI extends CommonService{
	
 	public void delete(RppMercTransactionEntity entity) throws Exception;
 	
 	public Serializable save(RppMercTransactionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppMercTransactionEntity entity) throws Exception;
 	
}
