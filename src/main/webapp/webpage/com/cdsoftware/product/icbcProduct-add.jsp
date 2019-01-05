<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品管理</title>
  <t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="icbcProductController.do?doAdd">
					<input id="id" name="id" type="hidden" value="${icbcProductPage.id }">
					<input id="createName" name="createName" type="hidden" value="${icbcProductPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${icbcProductPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${icbcProductPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${icbcProductPage.updateDate }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">名称:</label>
			</td>
			<td class="value">
		     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">名称</label>
			</td>
			</tr>
			<tr>
			<td align="right">
				<label class="Validform_label">原价:</label>
			</td>
			<td class="value">
		     	 <input id="originalPrice" name="originalPrice" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原价</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">售价:</label>
			</td>
			<td class="value">
		     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">售价</label>
			</td>
			</tr>
			<tr>
			<td align="right">
				<label class="Validform_label">基本描述:</label>
			</td>
			<td class="value">
				 <textarea id="describe" style="width:600px;" class="inputxt" rows="6" name="describe"></textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">基本描述</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">是否收货:</label>
			</td>
			<td class="value">
					<t:dictSelect field="haveAddress" type="list"
						typeGroupCode="yesorno"  hasLabel="false"  title="是否收货"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否收货</label>
			</td>
			</tr>
			<tr>
			<td align="right">
				<label class="Validform_label">生成码:</label>
			</td>
			<td class="value">
					<t:dictSelect field="haveCode" type="list"
						typeGroupCode="yesorno"  hasLabel="false"  title="生成码"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生成码</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">码长度:</label>
			</td>
			<td class="value">
		     	 <input id="codeLength" name="codeLength" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">码长度</label>
			</td>
			</tr>
			<tr>
			<td align="right">
				<label class="Validform_label">库存:</label>
			</td>
			<td class="value">
		     	 <input id="stock" name="stock" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">库存</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">售量:</label>
			</td>
			<td class="value">
		     	 <input id="saleCount" name="saleCount" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">售量</label>
			</td>
			</tr>
			<tr>
			<td align="right">
				<label class="Validform_label">显示位置:</label>
			</td>
			<td class="value">
		     	<t:dictSelect field="position" type="position"
						typeGroupCode="icbcCate"  hasLabel="false" defaultVal="3"  title="显示位置"></t:dictSelect>  
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">显示位置</label>
			</td>
		</tr>
		<tr>
			
			<td align="right">
				<label class="Validform_label">规则:</label>
			</td>
			<td class="value">
			<t:ckeditor name="rule" isfinder="true" value="" type="width:750"></t:ckeditor>
								<!-- <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
						    	<textarea name="rule" id="rule" style="width: 650px;height:300px"></textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('rule');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动规则</label> -->
						</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">规格参数:</label>
			</td>
			<td class="value">
			<t:ckeditor name="parameter" isfinder="true" value=""  type="width:750"></t:ckeditor>
								<!-- <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
						    	<textarea name="parameter" id="parameter" style="width: 650px;height:300px"></textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('parameter');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">规格参数</label> -->
						</td>
			</tr>
			<tr>
			<td align="right">
				<label class="Validform_label">售后服务:</label>
			</td>
			<td class="value">
			<t:ckeditor name="service" isfinder="true" value=""  type="width:750"></t:ckeditor>
								<!-- <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
						    	<textarea name="service" id="service" style="width: 650px;height:300px"></textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('service');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">售后服务</label> -->
						</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="icbcProductController.do?icbcProductPictureList&id=${icbcProductPage.id}" icon="icon-search" title="商品图片" id="icbcProductPicture"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_icbcProductPicture_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
							<t:dictSelect field="icbcProductPictureList[#index#].isMain" type="list"
										typeGroupCode="yesorno" defaultVal="" hasLabel="false"  title="是否主图"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否主图</label>
				  </td>
				  <td align="left">
										<input type="hidden" id="icbcProductPictureList[#index#].address" name="icbcProductPictureList[#index#].address" />
										<a  target="_blank" id="icbcProductPictureList[#index#].address_href">未上传</a>
									  <br>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(commonUploadDefaultCallBack,'icbcProductPictureList\\[#index#\\]\\.address')"/>
					  <label class="Validform_label" style="display: none;">图片地址</label>
				  </td>
				  <td align="left">
					  	<input name="icbcProductPictureList[#index#].url" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">指向地址</label>
				  </td>
				  <td align="left">
					  	<input name="icbcProductPictureList[#index#].productId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">产品</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/cdsoftware/product/icbcProduct.js"></script>	