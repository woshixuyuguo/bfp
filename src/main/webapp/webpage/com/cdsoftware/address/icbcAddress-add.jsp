<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>收货地址</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="icbcAddressController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${icbcAddressPage.id }">
					<input id="createName" name="createName" type="hidden" value="${icbcAddressPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${icbcAddressPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${icbcAddressPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${icbcAddressPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							省份:
						</label>
					</td>
					<td class="value">
					     	 <input id="provinceId" name="provinceId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">省份</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							城市:
						</label>
					</td>
					<td class="value">
					     	 <input id="cityId" name="cityId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">城市</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							区名:
						</label>
					</td>
					<td class="value">
					     	 <input id="orgId" name="orgId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">区名</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
					     	 <input id="memberId" name="memberId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/cdsoftware/address/icbcAddress.js"></script>		