<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="icbcOrderList" checkbox="true" fitColumns="false" title="工商订单" actionUrl="icbcOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品"  field="productId"  hidden="false"  queryMode="single" dictionary="icbc_product,id,name" width="120"></t:dgCol>
   <t:dgCol title="数量"  field="count"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="price"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总价"  field="amount"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="优惠券"  field="couponId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实际付款"  field="realAmount"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="state"  hidden="false"  queryMode="single" dictionary="orderstate" width="120"></t:dgCol>
   <t:dgCol title="省份"  field="provinceId"  hidden="false"  queryMode="single" dictionary="t_s_region,id,name" width="120"></t:dgCol>
   <t:dgCol title="城市"  field="cityId"  hidden="false"  queryMode="single" dictionary="t_s_region,id,name" width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流单号"  field="expressNo"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流名称"  field="expressName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="icbcOrderController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="icbcOrderController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="icbcOrderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="icbcOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="icbcOrderController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/order/icbcOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'icbcOrderController.do?upload', "icbcOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("icbcOrderController.do?exportXls","icbcOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("icbcOrderController.do?exportXlsByT","icbcOrderList");
}
 </script>