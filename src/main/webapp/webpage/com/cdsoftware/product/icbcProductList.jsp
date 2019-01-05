<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="icbcProductList" checkbox="true" fitColumns="false" title="商品管理" actionUrl="icbcProductController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="name"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="原价"  field="originalPrice"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="售价"  field="price"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否收货"  field="haveAddress"  hidden="false"  queryMode="single" dictionary="yesorno" width="120"></t:dgCol>
   <t:dgCol title="生成码"  field="haveCode"  hidden="false"  queryMode="single" dictionary="yesorno" width="120"></t:dgCol>
   <t:dgCol title="码长度"  field="codeLength"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="库存"  field="stock"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="售量"  field="saleCount"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="显示位置"  field="position"  hidden="false"  queryMode="single" dictionary="icbccate" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="icbcProductController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" width="800" height="600" url="icbcProductController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" width="800" height="600" url="icbcProductController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="icbcProductController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="icbcProductController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/product/icbcProductList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'icbcProductController.do?upload', "icbcProductList");
}

//导出
function ExportXls() {
	JeecgExcelExport("icbcProductController.do?exportXls","icbcProductList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("icbcProductController.do?exportXlsByT","icbcProductList");
}
 </script>