<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cdTransactionList" checkbox="true" fitColumns="false" title="消费流水" actionUrl="cdTransactionController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会员"  field="membId"  hidden="false"  queryMode="single" dictionary="cd_member,id,pet_name" width="120"></t:dgCol>
   <t:dgCol title="金额"  field="amount"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="小程序"  field="programId"  hidden="false"  queryMode="single" dictionary="cd_mini_program,id,name" width="120"></t:dgCol>
   <t:dgCol title="类型"  field="type"  hidden="false"  queryMode="single" dictionary="tramtype" width="120"></t:dgCol>
   <t:dgCol title="地点"  field="address"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="cdTransactionController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cdTransactionController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cdTransactionController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="cdTransactionController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cdTransactionController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/transaction/cdTransactionList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'cdTransactionController.do?upload', "cdTransactionList");
}

//导出
function ExportXls() {
	JeecgExcelExport("cdTransactionController.do?exportXls","cdTransactionList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("cdTransactionController.do?exportXlsByT","cdTransactionList");
}
 </script>