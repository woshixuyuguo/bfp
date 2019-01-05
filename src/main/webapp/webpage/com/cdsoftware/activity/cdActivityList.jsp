<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cdActivityList" checkbox="true" fitColumns="false" title="活动管理" actionUrl="cdActivityController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动名称"  field="name"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关联小程序"  field="program.name"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="startTime" formatter="yyyy-MM-dd hh:mm:ss" hidden="false" query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="endTime" formatter="yyyy-MM-dd hh:mm:ss" hidden="false" query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="活动类型"  field="type"  hidden="false" query="true" queryMode="single" dictionary="act_type" width="120"></t:dgCol>
   <t:dgCol title="转发字数"  field="wordCount"  hidden="false"  queryMode="single" dictionary="wordcount" width="120"></t:dgCol>
   <t:dgCol title="转发次数"  field="transmitCount"  hidden="false"  queryMode="single" dictionary="transcount" width="120"></t:dgCol>
   <t:dgCol title="转发限制"  field="transmitLimit"  hidden="false"  queryMode="single" dictionary="translimit" width="120"></t:dgCol>
   <t:dgCol title="原价"  field="originalPrice"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动价"  field="price"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主办方"  field="sponsor"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="linkMan"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系电话"  field="linkPhone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否允许重复购买"  field="allowRepeat"  hidden="false"  queryMode="single" dictionary="yesorno" width="120"></t:dgCol>
   <t:dgCol title="活动描述"  field="describe"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动主题图片"  field="img"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="一次购买多个"  field="allowBuyMany"  hidden="false"  queryMode="single" dictionary="yesorno" width="120"></t:dgCol>
   <t:dgCol title="是否生成兑换码"  field="generateExchangeCode"  hidden="false"  queryMode="single" dictionary="yesorno" width="120"></t:dgCol>
   <t:dgCol title="兑换码长度"  field="exchangeCodeLength"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期"  field="validity"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="兑换码图片"  field="exchangeCodeImg"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="周期"  field="validityUnit"  hidden="false"  queryMode="single" dictionary="validtype" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="cdActivityController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cdActivityController.do?goAdd"  width="1200" height="800"    funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cdActivityController.do?goUpdate"  width="1200" height="800" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="cdActivityController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cdActivityController.do?goUpdate"  width="1200" height="800" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/cdsoftware/activity/cdActivityList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#cdActivityListtb").find("input[name='startTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#cdActivityListtb").find("input[name='startTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#cdActivityListtb").find("input[name='endTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#cdActivityListtb").find("input[name='endTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'cdActivityController.do?upload', "cdActivityList");
}

//导出
function ExportXls() {
	JeecgExcelExport("cdActivityController.do?exportXls","cdActivityList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("cdActivityController.do?exportXlsByT","cdActivityList");
}
 </script>