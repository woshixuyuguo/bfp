package com.rpp.merc.service;
import com.rpp.merc.entity.RppMercPssfilEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface RppMercPssfilServiceI extends CommonService{
	
 	public void delete(RppMercPssfilEntity entity) throws Exception;
 	
 	public Serializable save(RppMercPssfilEntity entity) throws Exception;
 	
 	public void saveOrUpdate(RppMercPssfilEntity entity) throws Exception;
 	
}
