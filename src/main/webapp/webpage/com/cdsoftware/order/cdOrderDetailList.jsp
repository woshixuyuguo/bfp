<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addCdOrderDetailBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delCdOrderDetailBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addCdOrderDetailBtn').bind('click', function(){   
 		 var tr =  $("#add_cdOrderDetail_table_template tr").clone();
	 	 $("#add_cdOrderDetail_table").append(tr);
	 	 resetTrNum('add_cdOrderDetail_table');
	 	 return false;
    });  
	$('#delCdOrderDetailBtn').bind('click', function(){   
      	$("#add_cdOrderDetail_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_cdOrderDetail_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#cdOrderDetail_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addCdOrderDetailBtn" href="#">添加</a> <a id="delCdOrderDetailBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="cdOrderDetail_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">
						订单
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						兑换码
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						条形码
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						二维码
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						购买人
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						有效期
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						状态
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						小程序
				  </td>
	</tr>
	<tbody id="add_cdOrderDetail_table">	
	<c:if test="${fn:length(cdOrderDetailList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="cdOrderDetailList[0].id" type="hidden"/>
					<input name="cdOrderDetailList[0].createName" type="hidden"/>
					<input name="cdOrderDetailList[0].createDate" type="hidden"/>
					<input name="cdOrderDetailList[0].updateName" type="hidden"/>
					<input name="cdOrderDetailList[0].updateDate" type="hidden"/>
				  <td align="left">
					  	<input name="cdOrderDetailList[0].orderId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">订单</label>
					</td>
				  <td align="left">
					  	<input name="cdOrderDetailList[0].exchangeCode" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">兑换码</label>
					</td>
				  <td align="left">
					  	<input name="cdOrderDetailList[0].barCode" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">条形码</label>
					</td>
				  <td align="left">
					  	<input name="cdOrderDetailList[0].qrCode" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">二维码</label>
					</td>
				  <td align="left">
					  	<input name="cdOrderDetailList[0].membId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">购买人</label>
					</td>
				  <td align="left">
					  	<input name="cdOrderDetailList[0].validity" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">有效期</label>
					</td>
				  <td align="left">
							<t:dictSelect field="state" type="list"
										dictTable="po.dictTable" dictField="" dictText="" defaultVal="${cdOrderDetailPage.state}" hasLabel="false"  title="状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">状态</label>
					</td>
				  <td align="left">
					  	<input name="cdOrderDetailList[0].programId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">小程序</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(cdOrderDetailList)  > 0 }">
		<c:forEach items="${cdOrderDetailList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="cdOrderDetailList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="cdOrderDetailList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="cdOrderDetailList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="cdOrderDetailList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="cdOrderDetailList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
				   <td align="left">
					  	<input name="cdOrderDetailList[${stuts.index }].orderId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.orderId }">
					  <label class="Validform_label" style="display: none;">订单</label>
				   </td>
				   <td align="left">
					  	<input name="cdOrderDetailList[${stuts.index }].exchangeCode" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.exchangeCode }">
					  <label class="Validform_label" style="display: none;">兑换码</label>
				   </td>
				   <td align="left">
					  	<input name="cdOrderDetailList[${stuts.index }].barCode" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.barCode }">
					  <label class="Validform_label" style="display: none;">条形码</label>
				   </td>
				   <td align="left">
					  	<input name="cdOrderDetailList[${stuts.index }].qrCode" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.qrCode }">
					  <label class="Validform_label" style="display: none;">二维码</label>
				   </td>
				   <td align="left">
					  	<input name="cdOrderDetailList[${stuts.index }].membId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.membId }">
					  <label class="Validform_label" style="display: none;">购买人</label>
				   </td>
				   <td align="left">
					  	<input name="cdOrderDetailList[${stuts.index }].validity" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.validity }">
					  <label class="Validform_label" style="display: none;">有效期</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="cdOrderDetailList[${stuts.index }].state" type="list"
										dictTable="ordestate" dictField="" dictText="" defaultVal="${poVal.state }" hasLabel="false"  title="状态"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">状态</label>
				   </td>
				   <td align="left">
					  	<input name="cdOrderDetailList[${stuts.index }].programId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.programId }">
					  <label class="Validform_label" style="display: none;">小程序</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
