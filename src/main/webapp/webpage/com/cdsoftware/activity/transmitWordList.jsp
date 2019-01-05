<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTransmitWordBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTransmitWordBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTransmitWordBtn').bind('click', function(){   
 		 var tr =  $("#add_transmitWord_table_template tr").clone();
	 	 $("#add_transmitWord_table").append(tr);
	 	 resetTrNum('add_transmitWord_table');
	 	 return false;
    });  
	$('#delTransmitWordBtn').bind('click', function(){   
      	$("#add_transmitWord_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_transmitWord_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#transmitWord_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTransmitWordBtn" href="#">添加</a> <a id="delTransmitWordBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="transmitWord_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  
				  <td align="left" bgcolor="#EEEEEE">
						转发字
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						顺序
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						总字数
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						已发字数
				  </td>
	</tr>
	<tbody id="add_transmitWord_table">	
	<c:if test="${fn:length(transmitWordList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="transmitWordList[0].id" type="hidden"/>
					<input name="transmitWordList[0].createName" type="hidden"/>
					<input name="transmitWordList[0].createDate" type="hidden"/>
					<input name="transmitWordList[0].updateName" type="hidden"/>
					<input name="transmitWordList[0].updateDate" type="hidden"/>
				 
				  <td align="left">
					  	<input name="transmitWordList[0].word" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">转发字</label>
					</td>
				  <td align="left">
					  	<input name="transmitWordList[0].orderNumber" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">顺序</label>
					</td>
				  <td align="left">
					  	<input name="transmitWordList[0].count" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">总字数</label>
					</td>
				  <td align="left">
					  	<input name="transmitWordList[0].haveCount" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">已发字数</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(transmitWordList)  > 0 }">
		<c:forEach items="${transmitWordList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="transmitWordList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="transmitWordList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="transmitWordList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="transmitWordList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="transmitWordList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
				  
				   <td align="left">
					  	<input name="transmitWordList[${stuts.index }].word" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.word }">
					  <label class="Validform_label" style="display: none;">转发字</label>
				   </td>
				   <td align="left">
					  	<input name="transmitWordList[${stuts.index }].orderNumber" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.orderNumber }">
					  <label class="Validform_label" style="display: none;">顺序</label>
				   </td>
				   <td align="left">
					  	<input name="transmitWordList[${stuts.index }].count" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.count }">
					  <label class="Validform_label" style="display: none;">总字数</label>
				   </td>
				   <td align="left">
					  	<input name="transmitWordList[${stuts.index }].haveCount" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.haveCount }">
					  <label class="Validform_label" style="display: none;">已发字数</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
