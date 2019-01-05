<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
    <script>
<%-- //        update-start--Author:zhangguoming  Date:20140826 for：将combobox修改为combotree
        function setOrgIds() {
//            var orgIds = $("#orgSelect").combobox("getValues");
            var orgIds = $("#orgSelect").combotree("getValues");
            $("#orgIds").val(orgIds);
        }
        $(function() {
            $("#orgSelect").combotree({
                onChange: function(n, o) {
                    if($("#orgSelect").combotree("getValues") != "") {
                        $("#orgSelect option").eq(1).attr("selected", true);
                    } else {
                        $("#orgSelect option").eq(1).attr("selected", false);
                    }
                }
            });
            $("#orgSelect").combobox("setValues", ${orgIdList});
            $("#orgSelect").combotree("setValues", ${orgIdList});
        }); --%>


		function openDepartmentSelect() {
			$.dialog.setting.zIndex = getzIndex(); 
			var orgIds = $("#orgIds").val();

			$.dialog({content: 'url:departController.do?departSelect&orgIds='+orgIds, zIndex: getzIndex(), title: '组织机构列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
			   {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackDepartmentSelect, focus: true},
			   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
		   ]}).zindex();

		}
			
		function callbackDepartmentSelect() {
			  var iframe = this.iframe.contentWindow;
			  var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
			  var nodes = treeObj.getCheckedNodes(true);
			  if(nodes.length>0){
			  var ids='',names='';
			  for(i=0;i<nodes.length;i++){
			     var node = nodes[i];
			     ids += node.id+',';
			    names += node.name+',';
			 }
			 $('#departname').val(names);
			 $('#departname').blur();		
			 $('#orgIds').val(ids);		
			}
		}
		
		function callbackClean(){
			$('#departname').val('');
			 $('#orgIds').val('');	
		}
		
		function setOrgIds() {
			return true;
		}
		$(function(){
			$("#departname").prev().hide();
		});
    </script>
</head>
<body style="overflow:auto" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mercController.do?saveUser" beforeSubmit="setOrgIds">
	<input id="id" name="id" type="hidden" value="${user.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
                <label class="Validform_label">  <t:mutiLang langKey="common.username"/>:  </label>
            </td>
			<td class="value" width="85%">
                <c:if test="${user.id!=null }"> ${user.userName } </c:if>
                <c:if test="${user.id==null }">
                    <input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id" value="${user.userName }" maxlength="50" datatype="s2-50" />
                    <span class="Validform_checktip"> <t:mutiLang langKey="username.rang2to50"/></span>
                </c:if>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 联系人: </label></td>
			<td class="value" width="10%">
                <input id="realName" class="inputxt" name="realName" value="${user.realName }" maxlength="25" datatype="s2-25"/>
                <span class="Validform_checktip"><t:mutiLang langKey="fill.realname"/></span>
            </td>
		</tr>
		<c:if test="${user.id==null }">
			<tr>
				<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.password"/>: </label></td>
				<td class="value">
                    <input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" />
                    <span class="passwordStrength" style="display: none;">
                        <span><t:mutiLang langKey="common.weak"/></span>
                        <span><t:mutiLang langKey="common.middle"/></span>
                        <span class="last"><t:mutiLang langKey="common.strong"/></span>
                    </span>
                    <span class="Validform_checktip"> <t:mutiLang langKey="password.rang6to18"/></span>
                </td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.repeat.password"/>: </label></td>
				<td class="value">
                    <input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！"/>
                    <span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
                </td>
			</tr>
		</c:if>
		
		
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="common.phone"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="mobilePhone" value="${user.mobilePhone}" datatype="m" errormsg="手机号码不正确" ignore="ignore"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.tel"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="officePhone" value="${user.officePhone}" datatype="n" errormsg="办公室电话不正确" ignore="ignore"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.common.mail"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="email" value="${user.email}"  validType="t_s_user,email,id" datatype="e" errormsg="邮箱格式不正确!" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> key: </label></td>
			<td class="value">
                <input class="inputxt" name="key" value="${user.key}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 账户余额: </label></td>
			<td class="value">
                <input class="inputxt" name="money" value="${user.money}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 锁定金额: </label></td>
			<td class="value">
                <input class="inputxt" name="lockMoney" value="${user.lockMoney}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 商户名称: </label></td>
			<td class="value">
                <input class="inputxt" name="mercName" value="${user.mercName}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 商户简称: </label></td>
			<td class="value">
                <input class="inputxt" name="shortName" value="${user.shortName}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
        <tr>
			<td align="right"><label class="Validform_label"> 银行卡号: </label></td>
			<td class="value">
                <input class="inputxt" name="bankCardNo" value="${user.bankCardNo}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 银行名称: </label></td>
			<td class="value">
                <input class="inputxt" name="bankName" value="${user.bankName}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 联行号: </label></td>
			<td class="value">
                <input class="inputxt" name="bankCode" value="${user.bankCode}"    />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 所属机构商户: </label></td>
			<td class="value">
                <input class="inputxt" name="agentCode" value="${user.agentCode}" datatype="*" errormsg="机构商户不能为空!"   />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>