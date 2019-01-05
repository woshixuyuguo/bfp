<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工商订单</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="icbcOrderController.do?doAdd">
					<input id="id" name="id" type="hidden" value="${icbcOrderPage.id }">
					<input id="createName" name="createName" type="hidden" value="${icbcOrderPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${icbcOrderPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${icbcOrderPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${icbcOrderPage.updateDate }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">商品:</label>
			</td>
			<td class="value">
		     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">商品</label>
			</td>
			<td align="right">
				<label class="Validform_label">数量:</label>
			</td>
			<td class="value">
		     	 <input id="count" name="count" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">单价:</label>
			</td>
			<td class="value">
		     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单价</label>
			</td>
			<td align="right">
				<label class="Validform_label">总价:</label>
			</td>
			<td class="value">
		     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总价</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">优惠券:</label>
			</td>
			<td class="value">
		     	 <input id="couponId" name="couponId" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">优惠券</label>
			</td>
			<td align="right">
				<label class="Validform_label">实际付款:</label>
			</td>
			<td class="value">
		     	 <input id="realAmount" name="realAmount" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际付款</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
		     	 <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">省份:</label>
			</td>
			<td class="value">
		     	 <input id="provinceId" name="provinceId" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">省份</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">城市:</label>
			</td>
			<td class="value">
		     	 <input id="cityId" name="cityId" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">城市</label>
			</td>
			<td align="right">
				<label class="Validform_label">地址:</label>
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
				<label class="Validform_label">物流单号:</label>
			</td>
			<td class="value">
		     	 <input id="expressNo" name="expressNo" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">物流名称:</label>
			</td>
			<td class="value">
		     	 <input id="expressName" name="expressName" type="text" style="width: 150px" class="inputxt"  
					               
					               >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流名称</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="icbcOrderController.do?icbcOrderDetailList&id=${icbcOrderPage.id}" icon="icon-search" title="订单明细" id="icbcOrderDetail"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_icbcOrderDetail_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="icbcOrderDetailList[#index#].orderId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">订单</label>
				  </td>
				  <td align="left">
					  	<input name="icbcOrderDetailList[#index#].code" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">兑换码</label>
				  </td>
				  <td align="left">
					  	<input name="icbcOrderDetailList[#index#].state" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">状态</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/cdsoftware/order/icbcOrder.js"></script>	