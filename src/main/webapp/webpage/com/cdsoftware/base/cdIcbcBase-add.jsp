<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工商银行基础参数</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="cdIcbcBaseController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${cdIcbcBasePage.id }">
					<input id="createName" name="createName" type="hidden" value="${cdIcbcBasePage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${cdIcbcBasePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cdIcbcBasePage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cdIcbcBasePage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							参数名:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">参数名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							参数值:
						</label>
					</td>
					<td class="value">
					     	 <input id="value" name="value" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">参数值</label>
						</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="describe" name="describe" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/cdsoftware/base/cdIcbcBase.js"></script>		