package com.kingpec.transaction.service;
import com.kingpec.transaction.entity.KpTransactionEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface KpTransactionServiceI extends CommonService{
	
 	public void delete(KpTransactionEntity entity) throws Exception;
 	
 	public Serializable save(KpTransactionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(KpTransactionEntity entity) throws Exception;
 	
}
