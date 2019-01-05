<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金变动流水</title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<link rel="stylesheet" href="plug-in/themes/naturebt/css/search-form.css">
<link rel="stylesheet" href="webpage/com/kingpec/account/accountTransaction.css">
</head>
<body>
<div class="account">
		<div class="my-account">
			<h2>我的账户</h2>
			<div class="account-detail">
				<div style="padding: 30px 10px 0; width:17%;height:45%;">
					<img class="left-img" src="images/jd_img.png" alt="">
				</div>
				<div class="sum">
					<div class="current-sum">
						<img src="images/jd_money.png" alt="">
						<div>
							<p>当前余额</p>
							<p id="dif">${user.money }  <span>元</span></p>
						</div>
					</div>
					<ul>
						<li>
							<p>累计充值金额</p>
							<p id="in">0.00 <span>元</span></p>
					   </li>
						<li>
							<p>累计消费金额</p>
							<p id="out">0.00 <span>元</span></p>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="list">
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">
		<table id="rppTransactionList"></table>  
	</div>
</div>
	</div>
	</div>
<script>
var rppTransactionListdictsData = {};
$(function(){
	var promiseArr = [];
	
    Promise.all(promiseArr).then(function(results) {
    	initDatagrid();
		$('#rppTransactionList').datagrid('getPager').pagination({
	        beforePageText: '',
	        afterPageText: '/{pages}',
	        displayMsg: '{from}-{to}共 {total}条',
	        showPageList: true,
	        showRefresh: true
	    });
	    $('#rppTransactionList').datagrid('getPager').pagination({
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
	var actionUrl = "rppTransactionController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,agentCode,mercCode,orgCode,rateCode,rate,orgRate,money,moneyFee,moneyAct,state,enter,outOrderNo,orderNo,orderId,notifyUrl,sysnNotifyUrl,notifyFlag,notifyTime,";
 	$('#rppTransactionList').datagrid({
		url:actionUrl,
		idField: 'id', 
		title: '交易',
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
		toolbar: '#rppTransactionListToolbar',
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
				title : "订单时间",
				width : 120,
				sortable: true,
				hidden:false,
				formatter : function(value, rec, index) {
					return new Date().format('yyyy-MM-dd hh24:mm:ss', value);
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
				field : "agentCode",
				title : "机构商户",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "mercCode",
				title : "商户",
				width : 120,
				sortable: true,
				
			}
			,{
				field : "orgCode",
				title : "通道",
				width : 120,
				sortable: true,
			}
			,{
				field : "rateCode",
				title : "费率代码",
				width : 120,
				sortable: true,
			}
			,{
				field : "rate",
				title : "交易费率",
				width : 120,
				sortable: true,
				
			}
			
			,{
				field : "money",
				title : "交易金额",
				width : 120,
				sortable: true,
			}
			,{
				field : "moneyFee",
				title : "手续费",
				width : 120,
				sortable: true,
			}
			,{
				field : "moneyAct",
				title : "入账金额",
				width : 120,
				sortable: true,
			}
			,{
				field : "state",
				title : "交易状态",
				width : 120,
				sortable: true,
			}
			,{
				field : "enter",
				title : "入账状态",
				width : 120,
				sortable: true,
			}
			,{
				field : "outOrderNo",
				title : "下游订单号",
				width : 120,
				sortable: true,
			}
			,{
				field : "orderNo",
				title : "我方订单号",
				width : 120,
				sortable: true,
			}
			,{
				field : "orderId",
				title : "上游订单号",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "notifyUrl",
				title : "异步通知地址",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "sysnNotifyUrl",
				title : "同步跳转页面",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "notifyFlag",
				title : "通知结果",
				width : 120,
				sortable: true,
				hidden:true,
			}
			,{
				field : "notifyTime",
				title : "通知次数",
				width : 120,
				sortable: true,
				hidden:true,
			}
			/* ,{
	            field: 'opt',title: '操作',width: 150,
	            formatter: function(value, rec, index) {
	                if (!rec.id) {
	                    return '';
	                }
	                var href = '';
	                href += "<a href='#'   class='ace_button'  onclick=delObj('rppTransactionController.do?doDel&id=" + rec.id + "','rppTransactionList')>  <i class=' fa fa-trash-o'></i> ";
	                href += "删除</a>&nbsp;";
	                return href;
	            } 
	        }*/
		]],
		onLoadSuccess: function(data) {
            $("#rppTransactionList").datagrid("clearSelections");
            if (!false) {
                if (data.total && data.rows.length == 0) {
                    var grid = $('#rppTransactionList');
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
	 $('#rppTransactionList').datagrid('reload');
}
//easyui-datagrid搜索
function doSearch(){
	var queryParams = $('#rppTransactionList').datagrid('options').queryParams;
	var actionUrl = "rppTransactionController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,agentCode,mercCode,orgCode,rateCode,rate,orgRate,money,moneyFee,moneyAct,state,enter,outOrderNo,orderNo,orderId,notifyUrl,sysnNotifyUrl,notifyFlag,notifyTime,";
	$('#rppTransactionForm').find(':input').each(function() {
		if("checkbox"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getCheckboxVal($(this).attr('name'));
		}else if("radio"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getRadioVal($(this).attr('name'));
		}else{
			queryParams[$(this).attr('name')] = $(this).val();
		}
    });
	
    $('#rppTransactionList').datagrid({
        url: actionUrl,
        pageNumber: 1
    });
}
//easyui-datagrid重置搜索
function resetSearch(){
    var queryParams = $('#rppTransactionList').datagrid('options').queryParams;
    $('#rppTransactionForm').find(':input').each(function() {
    	if("checkbox"== $(this).attr("type")){
    		$("input:checkbox[name='" + $(this).attr('name') + "']").attr('checked',false);
		}else if("radio"== $(this).attr("type")){
			$("input:radio[name='" + $(this).attr('name') + "']").attr('checked',false);
		}else{
			$(this).val("");
		}
        queryParams[$(this).attr('name')] = "";
    });
    $('#rppTransactionForm').find("input[type='checkbox']").each(function() {
        $(this).attr('checked', false);
    });
    $('#rppTransactionForm').find("input[type='radio']").each(function() {
        $(this).attr('checked', false);
    });
    var actionUrl = "rppTransactionController.do?datagrid&field=id,id_begin,id_end,createName,createName_begin,createName_end,createBy,createBy_begin,createBy_end,createDate,createDate_begin,createDate_end,updateName,updateName_begin,updateName_end,updateBy,updateBy_begin,updateBy_end,updateDate,updateDate_begin,updateDate_end,sysOrgCode,sysOrgCode_begin,sysOrgCode_end,sysCompanyCode,sysCompanyCode_begin,sysCompanyCode_end,bpmStatus,bpmStatus_begin,bpmStatus_end,agentCode,agentCode_begin,agentCode_end,mercCode,mercCode_begin,mercCode_end,orgCode,orgCode_begin,orgCode_end,rateCode,rateCode_begin,rateCode_end,rate,rate_begin,rate_end,orgRate,orgRate_begin,orgRate_end,money,money_begin,money_end,moneyFee,moneyFee_begin,moneyFee_end,moneyAct,moneyAct_begin,moneyAct_end,state,state_begin,state_end,enter,enter_begin,enter_end,outOrderNo,outOrderNo_begin,outOrderNo_end,orderNo,orderNo_begin,orderNo_end,orderId,orderId_begin,orderId_end,notifyUrl,notifyUrl_begin,notifyUrl_end,sysnNotifyUrl,sysnNotifyUrl_begin,sysnNotifyUrl_end,notifyFlag,notifyFlag_begin,notifyFlag_end,notifyTime,notifyTime_begin,notifyTime_end,";
    $('#rppTransactionList').datagrid({
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