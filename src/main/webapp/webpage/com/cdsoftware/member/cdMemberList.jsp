<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cdMemberList" checkbox="true" fitColumns="false" title="会员管理" actionUrl="cdMemberController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="petName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标志码"  field="openId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="头像" image="true" imageSize="30,30" field="imgUrl"  hidden="false"  queryMode="single"  width="30"></t:dgCol>
   <t:dgCol title="状态"  field="state"  hidden="false"  queryMode="single" dictionary="mem_state" width="120"></t:dgCol>
   <t:dgCol title="sessionKey"  field="sessionKey"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会话"  field="sessionId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账户余额"  field="money"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="经度"  field="lng"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="纬度"  field="lat"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="归属小程序"  field="program.name"  hidden="false"  queryMode="single" dictionary="cd_mini_program,id,name" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="cdMemberController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cdMemberController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cdMemberController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="cdMemberController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cdMemberController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/member/cdMemberList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'cdMemberController.do?upload', "cdMemberList");
}

//导出
function ExportXls() {
	JeecgExcelExport("cdMemberController.do?exportXls","cdMemberList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("cdMemberController.do?exportXlsByT","cdMemberList");
}
 </script>