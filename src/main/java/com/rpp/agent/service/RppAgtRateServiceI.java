package com.rpp.agent.service;
import com.rpp.agent.entity.RppAgtRateEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppAgtRateServiceI extends CommonService{
	
 	public void delete(RppAgtRateEntity entity) throws Exception;
 	
 	public Serializable save(RppAgtRateEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppAgtRateEntity entity) throws Exception;
 	
}
