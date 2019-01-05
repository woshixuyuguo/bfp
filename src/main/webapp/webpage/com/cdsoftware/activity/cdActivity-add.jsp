<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="cdActivityController.do?doAdd">
			<input id="id" name="id" type="hidden" value="${cdActivityPage.id }">
					<input id="createName" name="createName" type="hidden" value="${cdActivityPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${cdActivityPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cdActivityPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cdActivityPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							关联小程序:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="program.id" type="list"
									dictTable="cd_mini_program" dictField="id" dictText="name" defaultVal="${cdActivityPage.programId}" hasLabel="false"  title="关联小程序"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联小程序</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
							   <input id="startTime" name="startTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								                
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开始时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
							   <input id="endTime" name="endTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								                
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结束时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="type" type="list"
									typeGroupCode="act_type" defaultVal="${cdActivityPage.type}" hasLabel="false"  title="活动类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							转发字数:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="wordCount" type="list"
									typeGroupCode="wordcount" defaultVal="${cdActivityPage.wordCount}" hasLabel="false"  title="转发字数"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">转发字数</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							转发次数:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="transmitCount" type="list"
									typeGroupCode="transcount" defaultVal="${cdActivityPage.transmitCount}" hasLabel="false"  title="转发次数"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">转发次数</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							转发限制:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="transmitLimit" type="list"
									typeGroupCode="translimit" defaultVal="${cdActivityPage.transmitLimit}" hasLabel="false"  title="转发限制"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">转发限制</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							原价:
						</label>
					</td>
					<td class="value">
					     	 <input id="originalPrice" name="originalPrice" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">原价</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							活动价:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动价</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							主办方:
						</label>
					</td>
					<td class="value">
					     	 <input id="sponsor" name="sponsor" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主办方</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							联系人:
						</label>
					</td>
					<td class="value">
					     	 <input id="linkMan" name="linkMan" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="linkPhone" name="linkPhone" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否允许重复购买:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="allowRepeat" type="radio"
									typeGroupCode="yesorno" defaultVal="${cdActivityPage.allowRepeat}" hasLabel="false"  title="是否允许重复购买"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否允许重复购买</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动描述:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="describe" name="describe"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动描述</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							活动主题图片:
						</label>
					</td>
					<td class="value">
								<input type="hidden" id="img" name="img" />
								<a  target="_blank"  id="img_href">暂时未上传文件</a>
								<input class="ui-button" type="button" value="上传附件"
												onclick="commonUpload(imgCallback)"/>
								<script type="text/javascript">
								function imgCallback(url,name){
									$("#img_href").attr('href','cdActivityController.do?image&path='+url).html('下载');
									$("#img").val(url);
								}
								</script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动主题图片</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							一次购买多个:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="allowBuyMany" type="radio"
									typeGroupCode="yesorno" defaultVal="${cdActivityPage.allowBuyMany}" hasLabel="false"  title="一次购买多个"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">一次购买多个</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否生成兑换码:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="generateExchangeCode" type="radio"
									typeGroupCode="yesorno" defaultVal="${cdActivityPage.generateExchangeCode}" hasLabel="false"  title="是否生成兑换码"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否生成兑换码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							兑换码长度:
						</label>
					</td>
					<td class="value">
					     	 <input id="exchangeCodeLength" name="exchangeCodeLength" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">兑换码长度</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							有效期:
						</label>
					</td>
					<td class="value">
					     	 <input id="validity" name="validity" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							兑换码图片:
						</label>
					</td>
					<td class="value">
					     	 <input id="exchangeCodeImg" name="exchangeCodeImg" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">兑换码图片</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							周期:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="validityUnit" type="list"
									typeGroupCode="validtype" defaultVal="${cdActivityPage.validityUnit}" hasLabel="false"  title="周期"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">周期</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							活动大类:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="category" type="list"
									typeGroupCode="category" defaultVal="${cdActivityPage.category}" hasLabel="false"  title="活动类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动大类</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否需要绑定手机:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isBindPhone" type="radio"
									typeGroupCode="yesorno" defaultVal="${cdActivityPage.isBindPhone}" hasLabel="false"  title="一次购买多个"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否需要绑定手机号</label>
						</td>

					</tr>
					
					<tr>
					<td align="right">
						<label class="Validform_label">
							活动状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="state" type="list"
									typeGroupCode="actstate" defaultVal="${cdActivityPage.state}" hasLabel="false"  title="活动状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动状态</label>
						</td>
				<td align="right">
						<label class="Validform_label">
							是否轮播:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="carousel" type="radio"
									typeGroupCode="yesorno" defaultVal="${cdActivityPage.carousel}" hasLabel="false"  title="是否允许重复购买"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否轮播</label>
						</td>
							

					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							活动规则:
						</label>
					</td>
					<td class="value">
					<t:ckeditor name="rule" isfinder="true" value="" type="width:750"></t:ckeditor>
								<%-- <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
						    	<textarea name="rule" id="rule" style="width: 650px;height:300px">${cdActivityPage.rule}</textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('rule');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动规则</label> --%>
						</td>
				</tr>
				<tr>
				<td align="right">
						<label class="Validform_label">
							关联小程序:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="otherProgram.id" type="list"
									dictTable="cd_mini_program" dictField="id" dictText="name" defaultVal="${cdActivityPage.programId}" hasLabel="false"  title="关联小程序"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联小程序</label>
						</td></tr>
				
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="cdActivityController.do?transmitWordList&aCTIVITY=${cdActivityPage.aCTIVITY}" icon="icon-search" title="转发字" id="transmitWord"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_transmitWord_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				 
				  <td align="left">
					  	<input name="transmitWordList[#index#].word" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">转发字</label>
				  </td>
				  <td align="left">
					  	<input name="transmitWordList[#index#].orderNumber" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">顺序</label>
				  </td>
				  <td align="left">
					  	<input name="transmitWordList[#index#].count" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">总字数</label>
				  </td>
				  <td align="left">
					  	<input name="transmitWordList[#index#].haveCount" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">已发字数</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/cdsoftware/activity/cdActivity.js"></script>	