<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构商户清算流水</title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<link rel="stylesheet" href="plug-in/themes/naturebt/css/search-form.css">
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">
		<table id="rppAgentPssfilList"></table>  
	</div>
	<div id = "rppAgentPssfilListToolbar">
		<div class="easyui-panel toolbar-search" style="display:none" data-options="doSize:false">
			<form id="rppAgentPssfilForm" onkeydown="if(event.keyCode==13){doSearch();return false;}">
				<div class="seerch-div">
					<label>商户编码:</label>
					<div class="search-control">
						<input class="dts search-inp" type="text" name="agentCode" placeholder="请输入商户编码"/>
					</div>
				</div>
				<div class="seerch-div">
					<label>收款人:</label>
					<div class="search-control">
						<input class="dts search-inp" type="text" name="name" placeholder="请输入收款人"/>
					</div>
				</div>
				<div class="seerch-div">
					<label>银行卡号:</label>
					<div class="search-control">
						<input class="dts search-inp" type="text" name="bankCard" placeholder="请输入银行卡号"/>
					</div>
				</div>
				<div class="seerch-div">
					<label>电话:</label>
					<div class="search-control">
						<input class="dts search-inp" type="text" name="phone" placeholder="请输入电话"/>
					</div>
				</div>
				<div class="seerch-div">
					<label>状态:</label>
					<div class="search-control">
						<select name = "state" class="dts search-inp search-select"></select>
					</div>
				</div>
				<div class="seerch-div">
					<label style="visibility:hidden">查询</label>
					<div>
					<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="doSearch()">
						<i class="fa fa-search"></i>
						<span>查询</span>
					</button>
					
					<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="resetSearch()">
						<i class="fa fa-refresh"></i>
						<span>重置</span>
					</button>
					</div>
				</div>
			</form>
		</div>
		<div class="toolbar-btn">
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="add('录入','rppAgentPssfilController.do?goAdd','rppAgentPssfilList',768,500)">
				<i class="fa fa-plus"></i>
				<span>录入</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="update('编辑','rppAgentPssfilController.do?goUpdate','rppAgentPssfilList',768,500)">
				<i class="fa fa-edit"></i>
				<span>编辑</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="deleteALLSelect('批量删除','rppAgentPssfilController.do?doBatchDel','rppAgentPssfilList',null,null)">
				<i class="fa fa-trash"></i>
				<span>批量删除</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="openuploadwin('Excel导入', 'rppAgentPssfilController.do?upload', 'rppAgentPssfilList')">
				<i class="fa fa-download"></i>
				<span>导入</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="JeecgExcelExport('rppAgentPssfilController.do?exportXls','rppAgentPssfilList')">
				<i class="fa fa-upload"></i>
				<span>导出</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="JeecgExcelExport('rppAgentPssfilController.do?exportXlsByT','rppAgentPssfilList')">
				<i class="fa fa-upload"></i>
				<span>模版下载</span>
			</button>
		
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="$('.toolbar-search').slideToggle();">
				<i class="fa fa-arrow-circle-left"></i>
				<span>检索</span>
			</button>
		</div>
	</div>
</div>

<script>
var rppAgentPssfilListdictsData = {};
$(function(){
	var promiseArr = [];
	promiseArr.push(new Promise(function(resolve, reject) {
		initDictByCode(rppAgentPssfilListdictsData,"pssfilState",resolve);
	}));
	
    Promise.all(promiseArr).then(function(results) {
    	initDatagrid();
		$('#rppAgentPssfilList').datagrid('getPager').pagination({
	        beforePageText: '',
	        afterPageText: '/{pages}',
	        displayMsg: '{from}-{to}共 {total}条',
	        showPageList: true,
	        showRefresh: true
	    });
	    $('#rppAgentPssfilList').datagrid('getPager').pagination({
	        onBeforeRefresh: function(pageNumber, pageSize) {
	            $(this).pagination('loading');
	            $(this).pagination('loaded');
	        }
	    });
		loadSearchFormDicts($("#rppAgentPssfilForm").find("select[name='state']"),rppAgentPssfilListdictsData.pssfilState,"select");
	    
	}).catch(function(err) {
	 	console.log('Catch: ', err);
	});
	
});

//easyui-datagrid实例化
function initDatagrid(){
	var actionUrl = "rppAgentPssfilController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,agentId,agentCode,money,fee,moneyAct,name,bankCard,bankName,bankCode,branchBank,phone,state,remark,";
 	$('#rppAgentPssfilList').datagrid({
		url:actionUrl,
		idField: 'id', 
		title: '机构商户清算流水',
		loadMsg: '数据加载中...',
		fit:true,
		fitColumns:false,
		striped:true,
		autoRowHeight: true,
		pageSize: 10,
		pagination:true,
		singleSelect:false,
		pageList:[10,30,50,100],
		rownumbers:true,
		showFooter:true,
		toolbar: '#rppAgentPssfilListToolbar',
		frozenColumns:[[]],
		columns:[[
			{field:'ck',checkbox:true}
			,{
				field : "id",
				title : "主键",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "createName",
				title : "创建人名称",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "createBy",
				title : "创建人登录名称",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "createDate",
				title : "创建日期",
				width : 120,
				sortable: true,
				hidden:true,
				formatter : function(value, rec, index) {
					return new Date().format('yyyy-MM-dd', value);
				}
			}
			,{
				field : "updateName",
				title : "更新人名称",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "updateBy",
				title : "更新人登录名称",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "updateDate",
				title : "更新日期",
				width : 120,
				sortable: true,
				hidden:true,
				formatter : function(value, rec, index) {
					return new Date().format('yyyy-MM-dd', value);
				}
			}
			,{
				field : "sysOrgCode",
				title : "所属部门",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "sysCompanyCode",
				title : "所属公司",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "bpmStatus",
				title : "流程状态",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "agentId",
				title : "商户ID",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "agentCode",
				title : "商户编码",
				width : 120,
				sortable: true,
			}
			,{
				field : "money",
				title : "清算金额",
				width : 120,
				sortable: true,
			}
			,{
				field : "fee",
				title : "手续费",
				width : 120,
				sortable: true,
			}
			,{
				field : "moneyAct",
				title : "到账金额",
				width : 120,
				sortable: true,
			}
			,{
				field : "name",
				title : "收款人",
				width : 120,
				sortable: true,
			}
			,{
				field : "bankCard",
				title : "银行卡号",
				width : 120,
				sortable: true,
			}
			,{
				field : "bankName",
				title : "银行名称",
				width : 120,
				sortable: true,
			}
			,{
				field : "bankCode",
				title : "联行号",
				width : 120,
				sortable: true,
			}
			,{
				field : "branchBank",
				title : "支行名称",
				width : 120,
				sortable: true,
			}
			,{
				field : "phone",
				title : "电话",
				width : 120,
				sortable: true,
			}
			,{
				field : "state",
				title : "状态",
				width : 120,
				sortable: true,
				formatter : function(value, rec, index) {
					return listDictFormat(value,rppAgentPssfilListdictsData.pssfilState);
				}
			}
			,{
				field : "remark",
				title : "结果描述",
				width : 120,
				sortable: true,
			}
			,{
	            field: 'opt',title: '操作',width: 150,
	            formatter: function(value, rec, index) {
	                if (!rec.id) {
	                    return '';
	                }
	                var href = '';
	                href += "<a href='#'   class='ace_button'  onclick=delObj('rppAgentPssfilController.do?doDel&id=" + rec.id + "','rppAgentPssfilList')>  <i class=' fa fa-trash-o'></i> ";
	                href += "删除</a>&nbsp;";
	                return href;
	            }
	        }
		]],
		onLoadSuccess: function(data) {
            $("#rppAgentPssfilList").datagrid("clearSelections");
            if (!false) {
                if (data.total && data.rows.length == 0) {
                    var grid = $('#rppAgentPssfilList');
                    var curr = grid.datagrid('getPager').data("pagination").options.pageNumber;
                    grid.datagrid({
                        pageNumber: (curr - 1)
                    });
                }
            }
        }
	});
}
//easyui-datagrid重新加载
function reloadTable() {
	 $('#rppAgentPssfilList').datagrid('reload');
}
//easyui-datagrid搜索
function doSearch(){
	var queryParams = $('#rppAgentPssfilList').datagrid('options').queryParams;
	var actionUrl = "rppAgentPssfilController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,agentId,agentCode,money,fee,moneyAct,name,bankCard,bankName,bankCode,branchBank,phone,state,remark,";
	$('#rppAgentPssfilForm').find(':input').each(function() {
		if("checkbox"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getCheckboxVal($(this).attr('name'));
		}else if("radio"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getRadioVal($(this).attr('name'));
		}else{
			queryParams[$(this).attr('name')] = $(this).val();
		}
    });
	
    $('#rppAgentPssfilList').datagrid({
        url: actionUrl,
        pageNumber: 1
    });
}
//easyui-datagrid重置搜索
function resetSearch(){
    var queryParams = $('#rppAgentPssfilList').datagrid('options').queryParams;
    $('#rppAgentPssfilForm').find(':input').each(function() {
    	if("checkbox"== $(this).attr("type")){
    		$("input:checkbox[name='" + $(this).attr('name') + "']").attr('checked',false);
		}else if("radio"== $(this).attr("type")){
			$("input:radio[name='" + $(this).attr('name') + "']").attr('checked',false);
		}else{
			$(this).val("");
		}
        queryParams[$(this).attr('name')] = "";
    });
    $('#rppAgentPssfilForm').find("input[type='checkbox']").each(function() {
        $(this).attr('checked', false);
    });
    $('#rppAgentPssfilForm').find("input[type='radio']").each(function() {
        $(this).attr('checked', false);
    });
    var actionUrl = "rppAgentPssfilController.do?datagrid&field=id,id_begin,id_end,createName,createName_begin,createName_end,createBy,createBy_begin,createBy_end,createDate,createDate_begin,createDate_end,updateName,updateName_begin,updateName_end,updateBy,updateBy_begin,updateBy_end,updateDate,updateDate_begin,updateDate_end,sysOrgCode,sysOrgCode_begin,sysOrgCode_end,sysCompanyCode,sysCompanyCode_begin,sysCompanyCode_end,bpmStatus,bpmStatus_begin,bpmStatus_end,agentId,agentId_begin,agentId_end,agentCode,agentCode_begin,agentCode_end,money,money_begin,money_end,fee,fee_begin,fee_end,moneyAct,moneyAct_begin,moneyAct_end,name,name_begin,name_end,bankCard,bankCard_begin,bankCard_end,bankName,bankName_begin,bankName_end,bankCode,bankCode_begin,bankCode_end,branchBank,branchBank_begin,branchBank_end,phone,phone_begin,phone_end,state,state_begin,state_end,remark,remark_begin,remark_end,";
    $('#rppAgentPssfilList').datagrid({
        url: actionUrl,
        pageNumber: 1
    });
}

//加载字典数据
function initDictByCode(dictObj,code,callback){
	if(!dictObj[code]){
		jQuery.ajax({
            url: "systemController.do?typeListJson&typeGroupName="+code,
    		type:"GET",
       		dataType:"JSON",
            success: function (back) {
               if(back.success){
            	   dictObj[code]= back.obj;
            	  
               }
               callback();
             }
         });
	}
}
//加载form查询数据字典项
function loadSearchFormDicts(obj,arr,type,name){
	var html = "";
	for(var a = 0;a < arr.length;a++){
		if("select"== type){
			html+="<option value = '"+arr[a].typecode+"'>"+arr[a].typename+"</option>";
		}else{
			if(!arr[a].typecode){
			}else{
				html+="<input name = '"+name+"' type='"+type+"' value = '"+arr[a].typecode+"'>"+arr[a].typename +"&nbsp;&nbsp;";
			}
			
		}
    }
	obj.html(html);
}
//获取Checkbox的值
function getCheckboxVal(name){
    var result = new Array();
    $("input[name='" + name + "']:checkbox").each(function() {
        if ($(this).is(":checked")) {
            result.push($(this).attr("value"));
        }
    });
    return result.join(",");
}
//获取radio的值
function getRadioVal(name){
	var v = $('input:radio[name="'+name+'"]:checked').val();
	if(!v){
		v ="";
	}
	return v;
}
//列表数据字典项格式化
function listDictFormat(value,dicts){
	if (!value) return '';
    var valArray = value.split(',');
    var showVal = '';
    if (valArray.length > 1) {
    	for (var k = 0; k < valArray.length; k++) {
           if(dicts && dicts.length>0){
        	   for(var a = 0;a < dicts.length;a++){
                	if(dicts[a].typecode ==valArray[k]){
                		showVal = showVal + dicts[a].typename + ',';
                		 break;
                	}
                }
           }
        }
        showVal=showVal.substring(0, showVal.length - 1);
    }else{
    	if(dicts && dicts.length>0){
    	   for(var a = 0;a < dicts.length;a++){
            	if(dicts[a].typecode == value){
            		showVal =  dicts[a].typename;
            		 break;
            	}
            }
       }
    }
    return showVal;
}

//列表文件图片 列格式化方法
function listFileImgFormat(value,type){
	var href='';
	if(value==null || value.length==0){
		return href;
	}
	var value1 = "systemController/showOrDownByurl.do?dbPath="+value;
	if("image"==type){
 		href+="<img src='"+value1+"' width=30 height=30  onmouseover='tipImg(this)' onmouseout='moveTipImg()' style='vertical-align:middle'/>";
	}else{
 		if(value.indexOf(".jpg")>-1 || value.indexOf(".gif")>-1 || value.indexOf(".png")>-1){
 			href+="<img src='"+value1+"' onmouseover='tipImg(this)' onmouseout='moveTipImg()' width=30 height=30 style='vertical-align:middle'/>";
 		}else{
 			var value2 = "systemController/showOrDownByurl.do?down=1&dbPath="+value;
 			href+="<a href='"+value2+"' class='ace_button' style='text-decoration:none;' target=_blank><u><i class='fa fa-download'></i>点击下载</u></a>";
 		}
	}
	return href;
}
</script>
</body>
</html>