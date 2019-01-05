package com.kingpec.account.service;
import com.kingpec.account.entity.KpAccountTransactionEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface KpAccountTransactionServiceI extends CommonService{
	
 	public void delete(KpAccountTransactionEntity entity) throws Exception;
 	
 	public Serializable save(KpAccountTransactionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(KpAccountTransactionEntity entity) throws Exception;
 	
}
