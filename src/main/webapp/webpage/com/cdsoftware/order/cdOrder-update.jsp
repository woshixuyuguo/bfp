<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单管理</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="cdOrderController.do?doUpdate">
					<input id="name" name="name" type="hidden" value="${cdOrderPage.name }">
					<input id="phone" name="phone" type="hidden" value="${cdOrderPage.phone }">
					<input id="address" name="address" type="hidden" value="${cdOrderPage.address }">
					<input id="province" name="province" type="hidden" value="${cdOrderPage.province }">
					<input id="city" name="city" type="hidden" value="${cdOrderPage.city }">
					<input id="idcard" name="idcard" type="hidden" value="${cdOrderPage.idcard }">
					<input id="bankCard" name="bankCard" type="hidden" value="${cdOrderPage.bankCard }">
					<input id="trackNumber" name="trackNumber" type="hidden" value="${cdOrderPage.trackNumber }">
					<input id="validity" name="validity" type="hidden" value="${cdOrderPage.validity }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">主键:</label>
			</td>
			<td class="value">
		     	 <input id="id" name="id" type="text" style="width: 150px" class="inputxt"  
					               datatype="*"
					                value='${cdOrderPage.id}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">主键</label>
			</td>
			<td align="right">
				<label class="Validform_label">创建人名称:</label>
			</td>
			<td class="value">
		     	 <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.createName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">创建人名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">创建日期:</label>
			</td>
			<td class="value">
		     	 <input id="createDate" name="createDate" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.createDate}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">创建日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">修改人名称:</label>
			</td>
			<td class="value">
		     	 <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.updateName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">修改人名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">修改日期:</label>
			</td>
			<td class="value">
		     	 <input id="updateDate" name="updateDate" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.updateDate}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">修改日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">小程序:</label>
			</td>
			<td class="value">
		     	 <input id="programId" name="programId" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.programId}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">小程序</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">活动名称:</label>
			</td>
			<td class="value">
		     	 <input id="activityId" name="activityId" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.activityId}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">活动名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">单价:</label>
			</td>
			<td class="value">
		     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.price}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">数量:</label>
			</td>
			<td class="value">
		     	 <input id="count" name="count" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.count}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">金额:</label>
			</td>
			<td class="value">
		     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.amount}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">订单号:</label>
			</td>
			<td class="value">
		     	 <input id="orderNo" name="orderNo" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.orderNo}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">上游订单号:</label>
			</td>
			<td class="value">
		     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.orderId}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">上游订单号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">会员:</label>
			</td>
			<td class="value">
		     	 <input id="membId" name="membId" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.membId}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">会员</label>
			</td>
			<td align="right">
				<label class="Validform_label">下单时间:</label>
			</td>
			<td class="value">
		     	 <input id="orderTime" name="orderTime" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.orderTime}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下单时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">支付时间:</label>
			</td>
			<td class="value">
		     	 <input id="payTime" name="payTime" type="text" style="width: 150px" class="inputxt"  
					               
					                value='${cdOrderPage.payTime}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">支付时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="state" type="list"
						typeGroupCode="orderstate" defaultVal="${cdOrderPage.state}" hasLabel="false"  title="状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="cdOrderController.do?cdOrderDetailList&id=${cdOrderPage.id}" icon="icon-search" title="订单明细" id="cdOrderDetail"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_cdOrderDetail_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="cdOrderDetailList[#index#].orderId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">订单</label>
				  </td>
				  <td align="left">
					  	<input name="cdOrderDetailList[#index#].exchangeCode" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">兑换码</label>
				  </td>
				  <td align="left">
					  	<input name="cdOrderDetailList[#index#].barCode" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">条形码</label>
				  </td>
				  <td align="left">
					  	<input name="cdOrderDetailList[#index#].qrCode" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">二维码</label>
				  </td>
				  <td align="left">
					  	<input name="cdOrderDetailList[#index#].membId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">购买人</label>
				  </td>
				  <td align="left">
					  	<input name="cdOrderDetailList[#index#].validity" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">有效期</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="cdOrderDetailList[#index#].state" type="list"
										dictTable="ordestate" dictField="" dictText="" defaultVal="" hasLabel="false"  title="状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">状态</label>
				  </td>
				  <td align="left">
					  	<input name="cdOrderDetailList[#index#].programId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">小程序</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/cdsoftware/order/cdOrder.js"></script>	