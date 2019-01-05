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
<div class="admin-manange">
		<!-- 搜索部分 -->
		<div class="nav-search">
			<form id="kpTransactionForm" onkeydown="if(event.keyCode==13){doSearch();return false;}" style="    display: flex;justify-content: space-between;">
				<div class="bth-left">
					<div class="search_input">
						<input type="text"  name="siteName" placeholder="搜索关键字">
						<img src="images/search.png" alt="">
					</div>
					<div class="date-search">
						<div class="begin-date">
							<input type="text" value=""  name="tranTime_begin" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" placeholder="请选择开始时间"/>
							<img src="images/date.png" alt="">
						</div>
						<span>至</span>
						<div class="end-date">
							<input type="text" value=""   name="tranTime_end"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" placeholder="请选择开始时间"/>
							<img src="images/date.png" alt="">
						</div>
					</div>
					<ul>
						<li class='select'>昨日</li>
						<li>近3日</li>
						<li>近7日</li>
						<li>近30日</li>
					</ul>
				</div>
				
				<div><div class="bth-right">查看</div></div>
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
		<!-- table -->
		<div class="table">
			<div class="table-title">详细报表</div>
			<table align='center' style="border-collapse:collapse;">
			  <tr style="background-color: #f7f7f7;color:#999">
			    <th>站点名称</th>
			    <th>交易金额</th>
			    <th>站点名称</th>
			    <th>交易金额</th>
			  </tr>
			  <tr>
			    <td>金盾浮山石化站</td>
			    <td>48,900.00</td>
			    <td>金盾山东路站</td>
			    <td>48,900.00</td>
			  </tr>
			   <tr>
			    <td>金盾浮山石化站</td>
			    <td>48,900.00</td>
			    <td>金盾山东路站</td>
			    <td>48,900.00</td>
			  </tr>
			   <tr>
			    <td>金盾浮山石化站</td>
			    <td>48,900.00</td>
			    <td>金盾山东路站</td>
			    <td>48,900.00</td>
			  </tr>
			   <tr>
			    <td>金盾浮山石化站</td>
			    <td>48,900.00</td>
			    <td>金盾山东路站</td>
			    <td>48,900.00</td>
			  </tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	
	$(function(){
		datagridTotal();
		//获得总交易金额
		totalDeal();
		//昨天的总交易额
		totalDealYesterday();
		
	});
	
	$('.bth-left ul li').click(function(){
		$(this).addClass('select').siblings().removeClass('select')			
	})
	
	//当前筛选条件总金额
	function datagridTotal(){
		
		var url="kpTransactionController.do?datagridtotal&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,siteName,mercNo,termNo,tranTime,tranCode,tranMoney,tranState,settleDate,tranType,tranDate,transTime,cardNo,storeNo,branchName,tranFee,tranAct,";
		$.ajax({
			url : url,
			type : 'post',
			data : {},
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
	
	//当前筛选条件总金额
	function datagridTotalSearch(){
		var queryParams = {};
		
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
		console.log(queryParams);
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
					$('#datagridTotal').html(d.obj==0?0.00:d.obj);
					
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
	//折线图
	var echartsWarp= document.getElementById('line');     
	var resizeWorldMapContainer = function () {//用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽  
	    echartsWarp.style.width = 47+'%';  
	    echartsWarp.style.height = 350+'px';  
	    echartsWarp.style.padding = '15px';  
	    echartsWarp.style.marginRight = '1%'; 
	    echartsWarp.style.marginTop = '10px'; 
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
	        echartsWarpbar.style.marginTop = '10px'; 
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