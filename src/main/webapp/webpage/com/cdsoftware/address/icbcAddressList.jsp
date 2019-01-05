<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="icbcAddressList" checkbox="true" fitColumns="false" title="收货地址" actionUrl="icbcAddressController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="省份"  field="provinceId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="城市"  field="cityId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="区名"  field="orgId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="name"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系电话"  field="phone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="memberId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="icbcAddressController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="icbcAddressController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="icbcAddressController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="icbcAddressController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="icbcAddressController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/address/icbcAddressList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'icbcAddressController.do?upload', "icbcAddressList");
}

//导出
function ExportXls() {
	JeecgExcelExport("icbcAddressController.do?exportXls","icbcAddressList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("icbcAddressController.do?exportXlsByT","icbcAddressList");
}
 </script>