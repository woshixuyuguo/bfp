package com.rpp.agent.service;
import com.rpp.agent.entity.RppAgentPssfilEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppAgentPssfilServiceI extends CommonService{
	
 	public void delete(RppAgentPssfilEntity entity) throws Exception;
 	
 	public Serializable save(RppAgentPssfilEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppAgentPssfilEntity entity) throws Exception;
 	
}
