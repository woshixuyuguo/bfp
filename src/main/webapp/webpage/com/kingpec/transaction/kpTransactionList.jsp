<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易流水</title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<link rel="stylesheet" href="plug-in/themes/naturebt/css/search-form.css">
<link rel="stylesheet"
	href="webpage/com/kingpec/transaction/statistics.css">
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">
		<table id="kpTransactionList"></table>  
	</div>
	<div id = "kpTransactionListToolbar">
		<!-- <div class="easyui-panel toolbar-search" style="display:none" data-options="doSize:false">
			<form id="kpTransactionForm" onkeydown="if(event.keyCode==13){doSearch();return false;}">
				<div class="seerch-div">
					<label>站点名称:</label>
					<div class="search-control">
						<input class="dts search-inp" type="text" name="siteName" placeholder="请输入站点名称"/>
					</div>
				</div>
				<div class="seerch-div">
					<label>交易时间:</label>
					<div class="search-control">
						<input type="text" name="tranTime_begin" class="dts search-inp Wdate search-group-inp"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" placeholder="请选择开始时间"/>
						<span class="dts search-group-span">~</span>
						<input type="text" name="tranTime_end" class="dts search-inp Wdate search-group-inp" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" placeholder="请选择结束时间"/>
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
		</div> -->
			<!-- 搜索部分 -->
		<div class="nav-search">
			<form id="kpTransactionForm"
				onkeydown="if(event.keyCode==13){doSearch();return false;}"
				style="display: flex; justify-content: space-between;">
				<div class="bth-left">
					<div class="search_input">
						<input type="text" name="siteName" placeholder="搜索关键字"> <img
							src="images/search.png" alt="">
					</div>
					<div class="date-search">
						<div class="begin-date">
							<input type="text" value="" name="tranTime_begin"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
								placeholder="请选择开始时间" /> <img src="images/date.png" alt="">
						</div>
						<span>至</span>
						<div class="end-date">
							<input type="text" value="" name="tranTime_end"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
								placeholder="请选择开始时间" /> <img src="images/date.png" alt="">
						</div>
					</div>
					<ul>
						<li class='select'>昨日</li>
						<li>近3日</li>
						<li>近7日</li>
						<li>近30日</li>
					</ul>
				</div>

				<div>
					<div class="bth-right" onclick="datagridTotalSearch()">
					<img src="images/search_s.png" alt="">
					查看
					</div>
				</div>
			</form>
		</div>
		<div class="toolbar-btn">
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="add('录入','kpTransactionController.do?goAdd','kpTransactionList',768,500)">
				<i class="fa fa-plus"></i>
				<span>录入</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="update('编辑','kpTransactionController.do?goUpdate','kpTransactionList',768,500)">
				<i class="fa fa-edit"></i>
				<span>编辑</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="deleteALLSelect('批量删除','kpTransactionController.do?doBatchDel','kpTransactionList',null,null)">
				<i class="fa fa-trash"></i>
				<span>批量删除</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="openuploadwin('Excel导入', 'kpTransactionController.do?upload', 'kpTransactionList')">
				<i class="fa fa-download"></i>
				<span>导入</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="JeecgExcelExport('kpTransactionController.do?exportXls','kpTransactionList')">
				<i class="fa fa-upload"></i>
				<span>导出</span>
			</button>
			
			<button type="button" class="tool-btn tool-btn-default tool-btn-xs" onclick="JeecgExcelExport('kpTransactionController.do?exportXlsByT','kpTransactionList')">
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
var kpTransactionListdictsData = {};
$(function(){
	var promiseArr = [];
	
    Promise.all(promiseArr).then(function(results) {
    	initDatagrid();
		$('#kpTransactionList').datagrid('getPager').pagination({
	        beforePageText: '',
	        afterPageText: '/{pages}',
	        displayMsg: '{from}-{to}共 {total}条',
	        showPageList: true,
	        showRefresh: true
	    });
	    $('#kpTransactionList').datagrid('getPager').pagination({
	        onBeforeRefresh: function(pageNumber, pageSize) {
	            $(this).pagination('loading');
	            $(this).pagination('loaded');
	        }
	    });
	    
	}).catch(function(err) {
	 	console.log('Catch: ', err);
	});
	
});

//easyui-datagrid实例化
function initDatagrid(){
	var actionUrl = "kpTransactionController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,siteName,mercNo,termNo,tranTime,tranCode,tranMoney,tranState,settleDate,tranType,tranDate,transTime,cardNo,storeNo,branchName,tranFee,tranAct,";
 	$('#kpTransactionList').datagrid({
		url:actionUrl,
		idField: 'id', 
		title: '交易流水',
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
		toolbar: '#kpTransactionListToolbar',
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
				field : "siteName",
				title : "站点名称",
				width : 120,
				sortable: true,
			}
			,{
				field : "mercNo",
				title : "商户号",
				width : 120,
				sortable: true,
			}
			,{
				field : "termNo",
				title : "终端号",
				width : 120,
				sortable: true,
			}
			,{
				field : "tranTime",
				title : "交易时间",
				width : 120,
				sortable: true,
				formatter : function(value, rec, index) {
					return new Date().format('yyyy-MM-dd hh:mm:ss', value);
				}
			}
			,{
				field : "tranCode",
				title : "交易码",
				width : 120,
				sortable: true,
			}
			,{
				field : "tranMoney",
				title : "交易金额",
				width : 120,
				sortable: true,
			}
			,{
				field : "tranState",
				title : "交易状态",
				width : 120,
				sortable: true,
			}
			,{
				field : "settleDate",
				title : "结算日期",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "tranType",
				title : "交易类型",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "tranDate",
				title : "交易日期",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "transTime",
				title : "交易时间",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "cardNo",
				title : "交易卡号",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "storeNo",
				title : "总店号",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "branchName",
				title : "分店号",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "tranFee",
				title : "手续费",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "tranAct",
				title : "入账金额",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
	            field: 'opt',title: '操作',width: 150,
	            formatter: function(value, rec, index) {
	                if (!rec.id) {
	                    return '';
	                }
	                var href = '';
	                href += "<a href='#'   class='ace_button'  onclick=delObj('kpTransactionController.do?doDel&id=" + rec.id + "','kpTransactionList')>  <i class=' fa fa-trash-o'></i> ";
	                href += "删除</a>&nbsp;";
	                return href;
	            }
	        }
		]],
		onLoadSuccess: function(data) {
            $("#kpTransactionList").datagrid("clearSelections");
            if (!false) {
                if (data.total && data.rows.length == 0) {
                    var grid = $('#kpTransactionList');
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
	 $('#kpTransactionList').datagrid('reload');
}
//easyui-datagrid搜索
function doSearch(){
	var queryParams = $('#kpTransactionList').datagrid('options').queryParams;
	var actionUrl = "kpTransactionController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,siteName,mercNo,termNo,tranTime,tranCode,tranMoney,tranState,settleDate,tranType,tranDate,transTime,cardNo,storeNo,branchName,tranFee,tranAct,";
	$('#kpTransactionForm').find(':input').each(function() {
		if("checkbox"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getCheckboxVal($(this).attr('name'));
		}else if("radio"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getRadioVal($(this).attr('name'));
		}else{
			console.log($(this).val());
			queryParams[$(this).attr('name')] = $(this).val();
		}
    });
	
    $('#kpTransactionList').datagrid({
        url: actionUrl,
        pageNumber: 1
    });
}
//easyui-datagrid重置搜索
function resetSearch(){
    var queryParams = $('#kpTransactionList').datagrid('options').queryParams;
    $('#kpTransactionForm').find(':input').each(function() {
    	if("checkbox"== $(this).attr("type")){
    		$("input:checkbox[name='" + $(this).attr('name') + "']").attr('checked',false);
		}else if("radio"== $(this).attr("type")){
			$("input:radio[name='" + $(this).attr('name') + "']").attr('checked',false);
		}else{
			$(this).val("");
		}
        queryParams[$(this).attr('name')] = "";
    });
    $('#kpTransactionForm').find("input[type='checkbox']").each(function() {
        $(this).attr('checked', false);
    });
    $('#kpTransactionForm').find("input[type='radio']").each(function() {
        $(this).attr('checked', false);
    });
    var actionUrl = "kpTransactionController.do?datagrid&field=id,id_begin,id_end,createName,createName_begin,createName_end,createBy,createBy_begin,createBy_end,createDate,createDate_begin,createDate_end,updateName,updateName_begin,updateName_end,updateBy,updateBy_begin,updateBy_end,updateDate,updateDate_begin,updateDate_end,sysOrgCode,sysOrgCode_begin,sysOrgCode_end,sysCompanyCode,sysCompanyCode_begin,sysCompanyCode_end,bpmStatus,bpmStatus_begin,bpmStatus_end,siteName,siteName_begin,siteName_end,mercNo,mercNo_begin,mercNo_end,termNo,termNo_begin,termNo_end,tranTime,tranTime_begin,tranTime_end,tranCode,tranCode_begin,tranCode_end,tranMoney,tranMoney_begin,tranMoney_end,tranState,tranState_begin,tranState_end,settleDate,settleDate_begin,settleDate_end,tranType,tranType_begin,tranType_end,tranDate,tranDate_begin,tranDate_end,transTime,transTime_begin,transTime_end,cardNo,cardNo_begin,cardNo_end,storeNo,storeNo_begin,storeNo_end,branchName,branchName_begin,branchName_end,tranFee,tranFee_begin,tranFee_end,tranAct,tranAct_begin,tranAct_end,";
    $('#kpTransactionList').datagrid({
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