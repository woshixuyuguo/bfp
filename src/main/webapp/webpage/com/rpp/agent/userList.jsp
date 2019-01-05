<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
<t:datagrid name="userList" title="商户管理" actionUrl="agtController.do?datagrid" fit="true" fitColumns="false" idField="id" queryMode="group" >
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.username" sortable="false" field="userName" query="true" width="100"></t:dgCol>
	<t:dgCol title="商户名称" sortable="false" field="mercName" query="true" width="100"></t:dgCol>
	<t:dgCol title="联系人"   sortable="false" field="realName" query="true" width="100"></t:dgCol>
	<t:dgCol title="商户简称" sortable="false" field="shortName" query="true" width="100"></t:dgCol>
	<t:dgCol title="商户余额" sortable="false" field="money" query="false" width="100"></t:dgCol>
	<t:dgCol title="common.createby" field="createBy" hidden="true" width="100"></t:dgCol>
	<t:dgCol title="common.createtime" field="createDate" formatter="yyyy-MM-dd"  width="100" hidden="false"></t:dgCol>
	<t:dgCol title="common.updateby" field="updateBy" hidden="true"></t:dgCol>
	<t:dgCol title="common.updatetime" field="updateDate" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
	<t:dgCol title="common.status" sortable="true" field="status" width="50" replace="common.active_1,common.inactive_0,super.admin_-1" ></t:dgCol>
	
	<t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
	<t:dgFunOpt funname="deleteDialog(id)" title="common.delete" urlclass="ace_button"  urlfont="fa-trash-o"></t:dgFunOpt>
	<t:dgToolBar title="common.add.param" langArg="common.user" icon="icon-add" url="agtController.do?addorupdate" funname="add" height="420"></t:dgToolBar>
	<t:dgToolBar title="common.edit.param" langArg="common.user" icon="icon-edit" url="agtController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.password.reset" icon="icon-edit" url="agtController.do?changepasswordforuser" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.lock.user" icon="icon-edit" url="agtController.do?lock&lockvalue=0" funname="lockObj"></t:dgToolBar>
	<t:dgToolBar title="common.unlock.user" icon="icon-edit" url="agtController.do?lock&lockvalue=1" funname="unlockObj"></t:dgToolBar>
	<t:dgToolBar title="excelImport" icon="icon-put" funname="ImportXls"></t:dgToolBar>
	<t:dgToolBar title="excelOutput" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
	<t:dgToolBar title="templateDownload" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
</t:datagrid>
</div>
</div>
<script>
    $(function() {
        var datagrid = $("#userListtb");
		datagrid.find("div[name='searchColums']").find("form#userListForm").append($("#realNameSearchColums div[name='searchColumsRealName']").html());
		$("#realNameSearchColums").html('');
        datagrid.find("div[name='searchColums']").find("form#userListForm").append($("#tempSearchColums div[name='searchColums']").html());
        $("#tempSearchColums").html('');
	});
</script>
<script type="text/javascript">
function deleteDialog(id){
	var url = "agtController.do?deleteDialog&id=" + id
	createwindow("删除模式", url, 200, 100);
}
function lockObj(title,url, id) {

	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('<t:mutiLang langKey="common.lock.user.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}
function unlockObj(title,url, id) {
	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
		return;
	}

    if(rowsData[0].status == 1){
        tip('<t:mutiLang langKey="common.please.select.user.status.inactive"/>');
        return;
    }

		url += '&id='+rowsData[0].id;

	$.dialog.confirm('<t:mutiLang langKey="common.unlock.user.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}


function lockuploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			var msg = d.msg;
				tip(msg);
				reloadTable();
			}
		}
	});
}
</script>

<script type="text/javascript">
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'agtController.do?upload', "userList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("agtController.do?exportXls", "userList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("agtController.do?exportXlsByT", "userList");
	}
</script>