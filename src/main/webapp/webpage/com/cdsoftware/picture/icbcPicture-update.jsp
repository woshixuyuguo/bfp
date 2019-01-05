<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>轮播图片</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="icbcPictureController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${icbcPicturePage.id }">
					<input id="createName" name="createName" type="hidden" value="${icbcPicturePage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${icbcPicturePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${icbcPicturePage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${icbcPicturePage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否主图片:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="isMain" type="list"
										typeGroupCode="yesorno" defaultVal="${icbcPicturePage.isMain}" hasLabel="false"  title="是否主图片"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否主图片</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								图片地址:
							</label>
						</td>
						<td class="value">
									  <input type="hidden" id="address" name="address" value='${icbcPicturePage.address}'/>
										<c:if test="${empty icbcPicturePage.address}">
											<a   target="_blank" id="address_href">暂时未上传文件</a>
										</c:if>
										<c:if test="${!empty icbcPicturePage.address}">
											<a href="${icbcPicturePage.address}"  target="_blank" id="address_href" href="${icbcPicturePage.address}">下载</a>
										</c:if>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(addressCallback)"/>
						      			<script type="text/javascript">
											function addressCallback(url,name){
												$("#address_href").attr('href',url).html('下载');
												$("#address").val(url);
											}
										</script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								指向地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="url" name="url" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${icbcPicturePage.url}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">指向地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								对应产品:
							</label>
						</td>
						<td class="value">
						     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${icbcPicturePage.productId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">对应产品</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/cdsoftware/picture/icbcPicture.js"></script>		