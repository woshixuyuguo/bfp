<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>机构商户清算流水</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Jquery组件引用 -->
<script src="https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>

<!-- bootstrap组件引用 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- icheck组件引用 -->
<link href="plug-in/icheck-1.x/skins/square/_all.css" rel="stylesheet">
<script type="text/javascript" src="plug-in/icheck-1.x/icheck.js"></script>

<!-- Validform组件引用 -->
<link href="plug-in/themes/bootstrap-ext/css/validform-ext.css" rel="stylesheet" />
<script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
<!-- Layer组件引用 -->
<script src="plug-in/layer/layer.js"></script>
<script src="plug-in/laydate/laydate.js"></script>
<!-- 通用组件引用 -->
<link href="plug-in/bootstrap3.3.5/css/default.css" rel="stylesheet" />
<script src="plug-in/themes/bootstrap-ext/js/common.js"></script>
</head>
 <body style="overflow:hidden;overflow-y:auto;margin-top: 20px">
 <form id="formobj" action="rppAgentPssfilController.do?doUpdate" class="form-horizontal validform" role="form"  method="post">
	<input type="hidden" id="btn_sub" class="btn_sub"/>
	<input type="hidden" id="id" name="id" value="${rppAgentPssfilPage.id}"/>
	<div class="form-group">
		<label for="agentCode" class="col-sm-3 control-label">商户编码：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="agentCode" name="agentCode" value='${rppAgentPssfilPage.agentCode}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入商户编码"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="money" class="col-sm-3 control-label">清算金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="money" name="money" value='${rppAgentPssfilPage.money}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入清算金额"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="fee" class="col-sm-3 control-label">手续费：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="fee" name="fee" value='${rppAgentPssfilPage.fee}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入手续费"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="moneyAct" class="col-sm-3 control-label">到账金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="moneyAct" name="moneyAct" value='${rppAgentPssfilPage.moneyAct}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入到账金额"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">收款人：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="name" name="name" value='${rppAgentPssfilPage.name}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入收款人"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="bankCard" class="col-sm-3 control-label">银行卡号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="bankCard" name="bankCard" value='${rppAgentPssfilPage.bankCard}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入银行卡号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="bankName" class="col-sm-3 control-label">银行名称：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="bankName" name="bankName" value='${rppAgentPssfilPage.bankName}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入银行名称"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="bankCode" class="col-sm-3 control-label">联行号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="bankCode" name="bankCode" value='${rppAgentPssfilPage.bankCode}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入联行号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="branchBank" class="col-sm-3 control-label">支行名称：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="branchBank" name="branchBank" value='${rppAgentPssfilPage.branchBank}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入支行名称"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="phone" class="col-sm-3 control-label">电话：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="phone" name="phone" value='${rppAgentPssfilPage.phone}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入电话"  datatype="m" ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="state" class="col-sm-3 control-label">状态：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
               <t:dictSelect field="state" type="list" extendJson="{class:'form-control input-sm'}"  
 typeGroupCode="pssfilState" 			 hasLabel="false"  title="状态" defaultVal="${rppAgentPssfilPage.state}"></t:dictSelect>
			</div>
		</div>
	</div>
		<div class="form-group">
			<label for="remark" class="col-sm-3 control-label">结果描述：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<textarea id="remark" name="remark" class="form-control" placeholder="请输入结果描述" rows="4">${rppAgentPssfilPage.remark}</textarea>
				</div>
			</div>
		</div>
</form>
<script type="text/javascript">
	var subDlgIndex = '';
	$(document).ready(function() {
		
		//单选框/多选框初始化
		$('.i-checks').iCheck({
			labelHover : false,
			cursor : true,
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%'
		});
		
		//表单提交
		$("#formobj").Validform({
			tiptype:function(msg,o,cssctl){
				if(o.type==3){
					validationMessage(o.obj,msg);
				}else{
					removeMessage(o.obj);
				}
			},
			btnSubmit : "#btn_sub",
			btnReset : "#btn_reset",
			ajaxPost : true,
			beforeSubmit : function(curform) {
			},
			usePlugin : {
				passwordstrength : {
					minLen : 6,
					maxLen : 18,
					trigger : function(obj, error) {
						if (error) {
							obj.parent().next().find(".Validform_checktip").show();
							obj.find(".passwordStrength").hide();
						} else {
							$(".passwordStrength").show();
							obj.parent().next().find(".Validform_checktip").hide();
						}
					}
				}
			},
			callback : function(data) {
				var win = frameElement.api.opener;
				if (data.success == true) {
					frameElement.api.close();
				    win.reloadTable();
				    win.tip(data.msg);
				} else {
				    if (data.responseText == '' || data.responseText == undefined) {
				        $.messager.alert('错误', data.msg);
				        $.Hidemsg();
				    } else {
				        try {
				            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
				            $.messager.alert('错误', emsg);
				            $.Hidemsg();
				        } catch (ex) {
				            $.messager.alert('错误', data.responseText + "");
				            $.Hidemsg();
				        }
				    }
				    return false;
				}
			}
		});
	});
</script>
</body>
</html>