package com.rpp.merc.service;
import com.rpp.merc.entity.RppMercRateEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppMercRateServiceI extends CommonService{
	
 	public void delete(RppMercRateEntity entity) throws Exception;
 	
 	public Serializable save(RppMercRateEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppMercRateEntity entity) throws Exception;
 	
}
