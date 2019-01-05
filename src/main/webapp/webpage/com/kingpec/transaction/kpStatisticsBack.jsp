<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计</title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<link rel="stylesheet" href="plug-in/themes/naturebt/css/search-form.css">
<link rel="stylesheet" href="webpage/com/kingpec/transaction/statistics.css">
<script type="text/javascript" src="plug-in/echart/echarts.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px;">
		<table id="kpTransactionList" style=""></table>  
	</div>
	<div id = "kpTransactionListToolbar">
		<div class="easyui-panel toolbar-search"  data-options="doSize:false">
			<form id="kpTransactionForm" onkeydown="if(event.keyCode==13){doSearch();return false;}">
				<div class="seerch-div">
					<label>站点名称:</label>
					<div class="search-control" style="width:30%;">
						<input class="dts search-inp" type="text" name="siteName" placeholder="请输入站点名称"/>
					</div>
				</div>
				<div class="seerch-div">
					<label>交易时间:</label>
					<div class="search-control">
						<input type="text" name="tranTime_begin" class="dts search-inp Wdate search-group-inp"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" placeholder="请选择开始时间"/>
						<span class="dts search-group-span">至</span>
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
		</div>
		
	
	<div class="info">
			<div class="info-item">
				<div class="info-title">
					<p>总交易金额</p>
					<span style="background-color:#56a7fe">单位： 元</span>
				</div>
				<div class="info-content" id="totalDeal">0.00</div>
			</div>
			<div class="info-item">
				<div class="info-title">
					<p>搜索日期内交易金额</p>
					<span style="background-color:#13ba96">单位： 元</span>
				</div>
				<div class="info-content" id="datagridTotal">0.00</div>
			</div>
			<div class="info-item">
				<div class="info-title">
					<p>昨日交易金额</p>
					<span style="background-color:#829beb">单位： 元</span>
				</div>
				<div class="info-content" id="totalDealYesterday">0.00</div>
			</div>
		</div>
		<!-- 线性图部分 -->
		<div class="count">
			<div  id="line"></div>
			<div id="bar"></div>
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
	    datagridTotal();
	}).catch(function(err) {
	 	console.log('Catch: ', err);
	});
	//获得总交易金额
	totalDeal();
	//昨天的总交易额
	totalDealYesterday();
	
});

//当前筛选条件总金额
function datagridTotal(){
	var queryParams = $('#kpTransactionList').datagrid('options').queryParams;
	console.log(queryParams);
	$('#kpTransactionForm').find(':input').each(function() {
		if("checkbox"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getCheckboxVal($(this).attr('name'));
		}else if("radio"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getRadioVal($(this).attr('name'));
		}else{
			queryParams[$(this).attr('name')] = $(this).val();
		}
    });
	var url="kpTransactionController.do?datagridtotal&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,siteName,mercNo,termNo,tranTime,tranCode,tranMoney,tranState,settleDate,tranType,tranDate,transTime,cardNo,storeNo,branchName,tranFee,tranAct,";
	$.ajax({
		url : url,
		type : 'post',
		data : queryParams,
		cache : false,
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				console.log(d)
				$('#datagridTotal').html(d.obj);
				
			}
		}
	});
}
//获得总交易金额
function totalDeal(){
	var url="kpTransactionController.do?totalDeal";
	$.ajax({
		url : url,
		type : 'post',
		data : {
		},
		cache : false,
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				$('#totalDeal').html(d.obj);
				
			}
		}
	});
}


//获得昨天总交易金额
function totalDealYesterday(){
	var url="kpTransactionController.do?totalDealYesterday";
	$.ajax({
		url : url,
		type : 'post',
		data : {
		},
		cache : false,
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				$('#totalDealYesterday').html(d.obj);
				
			}
		}
	});
}
//easyui-datagrid实例化
function initDatagrid(){
	var actionUrl = "kpTransactionController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,siteName,mercNo,termNo,tranTime,tranCode,tranMoney,tranState,settleDate,tranType,tranDate,transTime,cardNo,storeNo,branchName,tranFee,tranAct,";
 	$('#kpTransactionList').datagrid({
		url:actionUrl,
		idField: 'id', 
		title: '统计',
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
	 datagridTotal();
}
//easyui-datagrid搜索
function doSearch(){
	var queryParams = $('#kpTransactionList').datagrid('options').queryParams;
	console.log("查询条件"+queryParams);
	var actionUrl = "kpTransactionController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,siteName,mercNo,termNo,tranTime,tranCode,tranMoney,tranState,settleDate,tranType,tranDate,transTime,cardNo,storeNo,branchName,tranFee,tranAct,";
	$('#kpTransactionForm').find(':input').each(function() {
		if("checkbox"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getCheckboxVal($(this).attr('name'));
		}else if("radio"== $(this).attr("type")){
			queryParams[$(this).attr('name')] = getRadioVal($(this).attr('name'));
		}else{
			queryParams[$(this).attr('name')] = $(this).val();
		}
    });
	
    $('#kpTransactionList').datagrid({
        url: actionUrl,
        pageNumber: 1
    });
    datagridTotal();
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
    datagridTotal();
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



//折线图
var echartsWarp= document.getElementById('line');     
var resizeWorldMapContainer = function () {//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽  
    echartsWarp.style.width = 47+'%';  
    echartsWarp.style.height = 350+'px';  
    echartsWarp.style.padding = '15px';  
    echartsWarp.style.marginRight = '1%'; 
};        
resizeWorldMapContainer ();//设置容器高宽  
var myChart = echarts.init(echartsWarp);

option = {
		"animation": false,
		"title": {
			text: '总交易金额折线图',
	        subtext: '（单位：万元）'
		},
		
		"xAxis": [{
			"data": ["6.1", "6.2", "6.3", "6.4", "6.5", "6.6", "6.7"],
			"type": "category"
		}],
		"yAxis": [{
			"type": "value",
			"splitArea": {
				"show": true
			}
		}],
		"series": [{
			itemStyle : { normal: {label : {show: true, position: 'top'}}},
			"data": [12, 5, 4, 10, 15, 7, 13],
			"name": "金额",
			"type": "line",
			"smooth": true,
			color:'#26b997'
		}]
	}
myChart.setOption(option);

//条形图
  var echartsWarpbar= document.getElementById('bar');     
    var resizeWorldMapContainerbar = function () {//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽  
        echartsWarpbar.style.width = 47+'%';  
        echartsWarpbar.style.height = 350+'px';  
        echartsWarpbar.style.padding = '15px';  
    };        
    resizeWorldMapContainerbar ();//设置容器高宽  
    var myChartbar = echarts.init(echartsWarpbar);

    optionbar = {
    	    title : {
    	        text: '站点交易金额条形图',
    	        subtext: '（单位：万元）'
    	    },
    	  
    	    
    	    xAxis : [
    	        {
    	            type : 'category',
    	            data : ['合肥路店','山东路店','福州南路店','福州北路店']
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value'
    	        }
    	    ],
    	    series : [
    	        {
    	        	  itemStyle : { normal: {label : {show: true, position: 'top'}}},
    	            name:'金额',
    	            type:'bar',
    	            data:[2.0, 4.9, 7.0, 23.2],
    				color:'#5aaafb'
    	           
    	        }
    	    ]
    	};
   
    myChartbar.setOption(optionbar);
</script>
</body>
</html>