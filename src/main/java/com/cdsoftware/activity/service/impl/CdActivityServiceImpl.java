package com.cdsoftware.activity.service.impl;
import com.cdsoftware.activity.service.CdActivityServiceI;

import net.sf.json.JSONObject;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.cdsoftware.activity.entity.CdActivityEntity;
import com.cdsoftware.activity.entity.TransmitWordEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.HttpRequest;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.io.Serializable;


@Service("cdActivityService")
@Transactional
public class CdActivityServiceImpl extends CommonServiceImpl implements CdActivityServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CdActivityEntity)entity);
 	}
	
	public void addMain(CdActivityEntity cdActivity,
	        List<TransmitWordEntity> transmitWordList){
			//保存主信息
			this.save(cdActivity);
		
			/**保存-转发字*/
			for(TransmitWordEntity transmitWord:transmitWordList){
				//外键设置
				transmitWord.setActivity(cdActivity);
				this.save(transmitWord);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(cdActivity);
	}

	
	public void updateMain(CdActivityEntity cdActivity,
	        List<TransmitWordEntity> transmitWordList) {
		//保存主表信息
		this.saveOrUpdate(cdActivity);
		//===================================================================================
		//获取参数
		Object aCTIVITY0 = cdActivity.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-转发字
	    String hql0 = "from TransmitWordEntity where 1 = 1 AND aCTIVITY = ? ";
	    List<TransmitWordEntity> transmitWordOldList = this.findHql(hql0,aCTIVITY0);
		//2.筛选更新明细数据-转发字
		for(TransmitWordEntity oldE:transmitWordOldList){
			boolean isUpdate = false;
				for(TransmitWordEntity sendE:transmitWordList){
					//需要更新的明细数据-转发字
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-转发字
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-转发字
			for(TransmitWordEntity transmitWord:transmitWordList){
				if(oConvertUtils.isEmpty(transmitWord.getId())){
					//外键设置
					transmitWord.setActivity(cdActivity);
					this.save(transmitWord);
				}
			}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(cdActivity);
	}

	
	public void delMain(CdActivityEntity cdActivity) {
		//删除主表信息
		this.delete(cdActivity);
		//===================================================================================
		//获取参数
		Object aCTIVITY0 = cdActivity;
		//===================================================================================
		//删除-转发字
	    String hql0 = "from TransmitWordEntity where 1 = 1 AND aCTIVITY = ? ";
	    List<TransmitWordEntity> transmitWordOldList = this.findHql(hql0,aCTIVITY0);
		this.deleteAllEntitie(transmitWordOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CdActivityEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CdActivityEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CdActivityEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CdActivityEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{program_id}",String.valueOf(t.getProgram().getId()));
 		sql  = sql.replace("#{start_time}",String.valueOf(t.getStartTime()));
 		sql  = sql.replace("#{end_time}",String.valueOf(t.getEndTime()));
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{word_count}",String.valueOf(t.getWordCount()));
 		sql  = sql.replace("#{transmit_count}",String.valueOf(t.getTransmitCount()));
 		sql  = sql.replace("#{transmit_limit}",String.valueOf(t.getTransmitLimit()));
 		sql  = sql.replace("#{original_price}",String.valueOf(t.getOriginalPrice()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{sponsor}",String.valueOf(t.getSponsor()));
 		sql  = sql.replace("#{link_man}",String.valueOf(t.getLinkMan()));
 		sql  = sql.replace("#{link_phone}",String.valueOf(t.getLinkPhone()));
 		sql  = sql.replace("#{allow_repeat}",String.valueOf(t.getAllowRepeat()));
 		sql  = sql.replace("#{describe}",String.valueOf(t.getDescribe()));
 		sql  = sql.replace("#{img}",String.valueOf(t.getImg()));
 		sql  = sql.replace("#{allow_buy_many}",String.valueOf(t.getAllowBuyMany()));
 		sql  = sql.replace("#{generate_exchange_code}",String.valueOf(t.getGenerateExchangeCode()));
 		sql  = sql.replace("#{exchange_code_length}",String.valueOf(t.getExchangeCodeLength()));
 		sql  = sql.replace("#{validity}",String.valueOf(t.getValidity()));
 		sql  = sql.replace("#{exchange_code_img}",String.valueOf(t.getExchangeCodeImg()));
 		sql  = sql.replace("#{validity_unit}",String.valueOf(t.getValidityUnit()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	@Test
	public void resetToken() {
		String url="http://tsv2.juyouhx.com/manager/ajaxtoken";
		
		List<CdActivityEntity> cdActivityEntities=this.findByProperty(CdActivityEntity.class, "tokenFlag", "Y");
	    CdActivityEntity cdActivityEntity=new CdActivityEntity();
		for (int i = 0; i < cdActivityEntities.size(); i++) {
			cdActivityEntity = cdActivityEntities.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", cdActivityEntity.getUserName());
			map.put("password", cdActivityEntity.getPassword());
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			String result = HttpRequest.sendGet(url, sb.toString());

			JSONObject jsonObject = JSONObject.fromObject(result);
			//重置token
			/*{
			  "token": "e1ba6174d4094554b05aaa4a10055a12",
			  "errmsg": "成功！",
			  "errcode": 0
			}*/
			if ("0".equals(jsonObject.get("errcode"))) {
				cdActivityEntity.setToken(jsonObject.getString("token"));
				this.saveOrUpdate(cdActivityEntity);
			}else{
				LogUtil.info("重置失败，原因是："+jsonObject.getString("errmsg"));
			}
		}
	}
	
	public static void main(String[] args) {
		String url="http://tsv2.juyouhx.com/manager/ajaxtoken";
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "L0099");
		map.put("password", "000000");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		String result = HttpRequest.sendGet(url, sb.toString());

		JSONObject jsonObject = JSONObject.fromObject(result);
		System.out.println(jsonObject.toString());
	}
	
}