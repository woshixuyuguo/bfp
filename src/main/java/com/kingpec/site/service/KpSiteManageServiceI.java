package com.kingpec.site.service;
import com.kingpec.site.entity.KpSiteManageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface KpSiteManageServiceI extends CommonService{
	
 	public void delete(KpSiteManageEntity entity) throws Exception;
 	
 	public Serializable save(KpSiteManageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(KpSiteManageEntity entity) throws Exception;
 	
}
