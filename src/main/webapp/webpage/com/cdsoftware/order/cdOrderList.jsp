<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cdOrderList" checkbox="true" fitColumns="false" title="订单管理" actionUrl="cdOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="小程序"  field="programId"  hidden="false"  queryMode="single" dictionary="cd_mini_program,id,name" width="120"></t:dgCol>
   <t:dgCol title="活动名称"  field="activityId"  hidden="false"  queryMode="single" dictionary="cd_activity,id,name" width="120"></t:dgCol>
   <t:dgCol title="单价"  field="price"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="count"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="amount"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNo"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上游订单号"  field="orderId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会员"  field="membId"  hidden="false"  queryMode="single" dictionary="cd_member,id,pet_name" width="120"></t:dgCol>
   <t:dgCol title="下单时间"  field="orderTime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付时间"  field="payTime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="state"  hidden="false"  queryMode="single" dictionary="orderstate" width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="name"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="省份"  field="province"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="城市"  field="city"  hidden="false"  queryMode="single"  width=""></t:dgCol>
   <t:dgCol title="身份证号"  field="idcard"  hidden="false"  queryMode="single"  width=""></t:dgCol>
   <t:dgCol title="银行卡号"  field="bankCard"  hidden="false"  queryMode="single"  width=""></t:dgCol>
   <t:dgCol title="快递单号"  field="trackNumber"  hidden="false"  queryMode="single"  width=""></t:dgCol>
   <t:dgCol title="有效期"  field="validity" formatter="yyyy-MM-dd hh:mm:ss" hidden="false"  queryMode="single"  width=""></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="cdOrderController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cdOrderController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cdOrderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="cdOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cdOrderController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/order/cdOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#cdOrderListtb").find("input[name='validity']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'cdOrderController.do?upload', "cdOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("cdOrderController.do?exportXls","cdOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("cdOrderController.do?exportXlsByT","cdOrderList");
}
 </script>