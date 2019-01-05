package com.rpp.rate.service;
import com.rpp.rate.entity.RppRateEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppRateServiceI extends CommonService{
	
 	public void delete(RppRateEntity entity) throws Exception;
 	
 	public Serializable save(RppRateEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppRateEntity entity) throws Exception;
 	
}
