package com.rpp.org.service;
import com.rpp.org.entity.RppOrgEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppOrgServiceI extends CommonService{
	
 	public void delete(RppOrgEntity entity) throws Exception;
 	
 	public Serializable save(RppOrgEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppOrgEntity entity) throws Exception;
 	
}
