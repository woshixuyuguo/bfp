<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>小程序管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="cdMiniProgramController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${cdMiniProgramPage.id }">
					<input id="createName" name="createName" type="hidden" value="${cdMiniProgramPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${cdMiniProgramPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cdMiniProgramPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cdMiniProgramPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							小程序名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">小程序名称</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							appId:
						</label>
					</td>
					<td class="value">
					     	 <input id="appid" name="appid" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">appId</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							appSecret:
						</label>
					</td>
					<td class="value">
					     	 <input id="appSecret" name="appSecret" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">appSecret</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商户号:
						</label>
					</td>
					<td class="value">
					     	 <input id="mchId" name="mchId" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商户号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							支付key:
						</label>
					</td>
					<td class="value">
					     	 <input id="mchKey" name="mchKey" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付key</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							超级管理员账号:
						</label>
					</td>
					<td class="value">
					     	 <input id="superManageAcount" name="superManageAcount" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">超级管理员账号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							首页路径:
						</label>
					</td>
					<td class="value">
					     	 <input id="path" name="path" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">首页路径</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属公众账号:
						</label>
					</td>
					<td class="value">
					     	 <input id="officialAccount" name="officialAccount" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属公众账号</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/cdsoftware/program/cdMiniProgram.js"></script>		