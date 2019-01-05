<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addIcbcProductPictureBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delIcbcProductPictureBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addIcbcProductPictureBtn').bind('click', function(){   
 		 var tr =  $("#add_icbcProductPicture_table_template tr").clone();
	 	 $("#add_icbcProductPicture_table").append(tr);
	 	 resetTrNum('add_icbcProductPicture_table');
	 	 return false;
    });  
	$('#delIcbcProductPictureBtn').bind('click', function(){   
      	$("#add_icbcProductPicture_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_icbcProductPicture_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#icbcProductPicture_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addIcbcProductPictureBtn" href="#">添加</a> <a id="delIcbcProductPictureBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="icbcProductPicture_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">
						是否主图
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						图片地址
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						指向地址
				  </td>
				  <td align="left" bgcolor="#EEEEEE">
						产品
				  </td>
	</tr>
	<tbody id="add_icbcProductPicture_table">	
	<c:if test="${fn:length(icbcProductPictureList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="icbcProductPictureList[0].id" type="hidden"/>
					<input name="icbcProductPictureList[0].createName" type="hidden"/>
					<input name="icbcProductPictureList[0].createDate" type="hidden"/>
					<input name="icbcProductPictureList[0].updateName" type="hidden"/>
					<input name="icbcProductPictureList[0].updateDate" type="hidden"/>
				  <td align="left">
							<t:dictSelect field="isMain" type="list"
										typeGroupCode="yesorno" defaultVal="${icbcProductPicturePage.isMain}" hasLabel="false"  title="是否主图"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否主图</label>
					</td>
				  <td align="left">
							<input type="hidden" id="icbcProductPictureList[0].address" name="icbcProductPictureList[0].address" />
										<a  target="_blank" id="icbcProductPictureList[0].address_href">未上传</a>
										<br>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(commonUploadDefaultCallBack,'icbcProductPictureList\\[0\\]\\.address')"/> 
					  <label class="Validform_label" style="display: none;">图片地址</label>
					</td>
				  <td align="left">
					  	<input name="icbcProductPictureList[0].url" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">指向地址</label>
					</td>
				  <td align="left">
					  	<input name="icbcProductPictureList[0].productId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					               >
					  <label class="Validform_label" style="display: none;">产品</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(icbcProductPictureList)  > 0 }">
		<c:forEach items="${icbcProductPictureList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="icbcProductPictureList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="icbcProductPictureList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="icbcProductPictureList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="icbcProductPictureList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="icbcProductPictureList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
				   <td align="left">
							<t:dictSelect field="icbcProductPictureList[${stuts.index }].isMain" type="list"
										typeGroupCode="yesorno" defaultVal="${poVal.isMain }" hasLabel="false"  title="是否主图"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否主图</label>
				   </td>
				   <td align="left">
					        <input type="hidden" id="icbcProductPictureList[${stuts.index }].address" name="icbcProductPictureList[${stuts.index }].address"  value="${poVal.address }"/>
										<c:if test="${empty poVal.address}">
											<a  target="_blank" id="icbcProductPictureList[${stuts.index }].address_href">未上传</a>
										</c:if>
										<c:if test="${!empty poVal.address}">
											<a  href="${poVal.address}"  target="_blank" id="icbcProductPictureList[${stuts.index }].address_href">下载</a>
										</c:if>
										<br>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(commonUploadDefaultCallBack,'icbcProductPictureList\\[${stuts.index }\\]\\.address')"/> 
					  <label class="Validform_label" style="display: none;">图片地址</label>
				   </td>
				   <td align="left">
					  	<input name="icbcProductPictureList[${stuts.index }].url" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.url }">
					  <label class="Validform_label" style="display: none;">指向地址</label>
				   </td>
				   <td align="left">
					  	<input name="icbcProductPictureList[${stuts.index }].productId" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					               
					                value="${poVal.productId }">
					  <label class="Validform_label" style="display: none;">产品</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
