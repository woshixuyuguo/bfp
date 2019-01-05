<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addIcbcOrderDetailBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delIcbcOrderDetailBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addIcbcOrderDetailBtn').bind('click', function(){   
 		 var tr =  $("#add_icbcOrderDetail_table_template tr").clone();
	 	 $("#add_icbcOrderDetail_table").append(tr);
	 	 resetTrNum('add_icbcOrderDetail_table');
	 	 return false;
    });  
	$('#delIcbcOrderDetailBtn').bind('click', function(){   
      	$("#add_icbcOrderDetail_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_icbcOrderDetail_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#icbcOrderDetail_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addIcbcOrderDetailBtn" href="#">添加</a> <a id="delIcbcOrderDetailBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="icbcOrderDetail_table">
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
						状态
				  </td>
	</tr>
	<tbody id="add_icbcOrderDetail_table">	
	<c:if test="${fn:length(icbcOrderDetailList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="icbcOrderDetailList[0].id" type="hidden"/>
					<input name="icbcOrderDetailList[0].createName" type="hidden"/>
					<input name="icbcOrderDetailList[0].createDate" type="hidden"/>
					<input name="icbcOrderDetailList[0].updateName" type="hidden"/>
					<input name="icbcOrderDetailList[0].updateDate" type="hidden"/>
				  <td align="left">
					  	<input name="icbcOrderDetailList[0].orderId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">订单</label>
					</td>
				  <td align="left">
					  	<input name="icbcOrderDetailList[0].code" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">兑换码</label>
					</td>
				  <td align="left">
					  	<input name="icbcOrderDetailList[0].state" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">状态</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(icbcOrderDetailList)  > 0 }">
		<c:forEach items="${icbcOrderDetailList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="icbcOrderDetailList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="icbcOrderDetailList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="icbcOrderDetailList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="icbcOrderDetailList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="icbcOrderDetailList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
				   <td align="left">
					  	<input name="icbcOrderDetailList[${stuts.index }].orderId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.orderId }">
					  <label class="Validform_label" style="display: none;">订单</label>
				   </td>
				   <td align="left">
					  	<input name="icbcOrderDetailList[${stuts.index }].code" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.code }">
					  <label class="Validform_label" style="display: none;">兑换码</label>
				   </td>
				   <td align="left">
					  	<input name="icbcOrderDetailList[${stuts.index }].state" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.state }">
					  <label class="Validform_label" style="display: none;">状态</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
