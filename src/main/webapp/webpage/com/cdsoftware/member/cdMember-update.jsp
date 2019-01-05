<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会员管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="cdMemberController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${cdMemberPage.id }">
					<input id="createName" name="createName" type="hidden" value="${cdMemberPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${cdMemberPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cdMemberPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cdMemberPage.updateDate }">
					<input id="sessionId" name="sessionId" type="hidden" value="${cdMemberPage.sessionId }">
					<input id="money" name="money" type="hidden" value="${cdMemberPage.money }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								昵称:
							</label>
						</td>
						<td class="value">
						     	 <input id="petName" name="petName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${cdMemberPage.petName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">昵称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								标志码:
							</label>
						</td>
						<td class="value">
						     	 <input id="openId" name="openId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${cdMemberPage.openId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标志码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								头像:
							</label>
						</td>
						<td class="value">
									  <input type="hidden" id="imgUrl" name="imgUrl" value='${cdMemberPage.imgUrl}'/>
										<c:if test="${empty cdMemberPage.imgUrl}">
											<a   target="_blank" id="imgUrl_href">暂时未上传文件</a>
										</c:if>
										<c:if test="${!empty cdMemberPage.imgUrl}">
											<a href="${cdMemberPage.imgUrl}"  target="_blank" id="imgUrl_href" href="${cdMemberPage.imgUrl}">下载</a>
										</c:if>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(imgUrlCallback)"/>
						      			<script type="text/javascript">
											function imgUrlCallback(url,name){
												$("#imgUrl_href").attr('href',url).html('下载');
												$("#imgUrl").val(url);
											}
										</script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">头像</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${cdMemberPage.state}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								sessionKey:
							</label>
						</td>
						<td class="value">
						     	 <input id="sessionKey" name="sessionKey" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${cdMemberPage.sessionKey}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">sessionKey</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								手机号:
							</label>
						</td>
						<td class="value">
						     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${cdMemberPage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								经度:
							</label>
						</td>
						<td class="value">
						     	 <input id="lng" name="lng" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${cdMemberPage.lng}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">经度</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								纬度:
							</label>
						</td>
						<td class="value">
						     	 <input id="lat" name="lat" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${cdMemberPage.lat}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">纬度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								归属小程序:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="program.id" type="list"
										dictTable="cd_mini_program" dictField="id" dictText="name" defaultVal="${cdMemberPage.program.id}" hasLabel="false"  title="关联小程序"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联小程序</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/cdsoftware/member/cdMember.js"></script>		