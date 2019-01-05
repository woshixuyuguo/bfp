<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cdMiniProgramList" checkbox="true" fitColumns="false" title="小程序管理" actionUrl="cdMiniProgramController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="小程序名称"  field="name"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="appId"  field="appid"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="appSecret"  field="appSecret"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户号"  field="mchId"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付key"  field="mchKey"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="超级管理员账号"  field="superManageAcount"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="首页路径"  field="path"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公众账号"  field="officialAccount"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="cdMiniProgramController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cdMiniProgramController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cdMiniProgramController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="cdMiniProgramController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cdMiniProgramController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/program/cdMiniProgramList.js"></script>	
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
	
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'cdMiniProgramController.do?upload', "cdMiniProgramList");
}

//导出
function ExportXls() {
	JeecgExcelExport("cdMiniProgramController.do?exportXls","cdMiniProgramList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("cdMiniProgramController.do?exportXlsByT","cdMiniProgramList");
}
 </script>