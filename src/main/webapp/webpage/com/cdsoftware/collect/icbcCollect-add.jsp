<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>收藏</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="icbcCollectController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${icbcCollectPage.id }">
					<input id="createName" name="createName" type="hidden" value="${icbcCollectPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${icbcCollectPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${icbcCollectPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${icbcCollectPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品:
						</label>
					</td>
					<td class="value">
					     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
					     	 <input id="membId" name="membId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/cdsoftware/collect/icbcCollect.js"></script>		