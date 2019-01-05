package com.rpp.agent.service;
import com.rpp.agent.entity.RppAgentTransactionEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppAgentTransactionServiceI extends CommonService{
	
 	public void delete(RppAgentTransactionEntity entity) throws Exception;
 	
 	public Serializable save(RppAgentTransactionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppAgentTransactionEntity entity) throws Exception;
 	
}
