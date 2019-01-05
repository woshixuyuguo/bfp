<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工商银行会员</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="icbcMemberController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${icbcMemberPage.id }">
					<input id="createName" name="createName" type="hidden" value="${icbcMemberPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${icbcMemberPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${icbcMemberPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${icbcMemberPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								账号:
							</label>
						</td>
						<td class="value">
						     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${icbcMemberPage.userId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								会话:
							</label>
						</td>
						<td class="value">
						     	 <input id="sessionId" name="sessionId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${icbcMemberPage.sessionId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">会话</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/cdsoftware/member/icbcMember.js"></script>		