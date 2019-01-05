<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>交易</title>
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
 <form id="formobj" action="rppTransactionController.do?doAdd" class="form-horizontal validform" role="form"  method="post">
	<input type="hidden" id="btn_sub" class="btn_sub"/>
	<input type="hidden" id="id" name="id"/>
	<div class="form-group">
		<label for="agentCode" class="col-sm-3 control-label">机构商户：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="agentCode" name="agentCode" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入机构商户"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="mercCode" class="col-sm-3 control-label">商户：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="mercCode" name="mercCode" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入商户"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="orgCode" class="col-sm-3 control-label">通道：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="orgCode" name="orgCode" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入通道"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="rateCode" class="col-sm-3 control-label">费率代码：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="rateCode" name="rateCode" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入费率代码"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="rate" class="col-sm-3 control-label">交易费率：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="rate" name="rate" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入交易费率"  datatype="n" ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="orgRate" class="col-sm-3 control-label">通道费率：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="orgRate" name="orgRate" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入通道费率"  datatype="n" ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="money" class="col-sm-3 control-label">交易金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="money" name="money" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入交易金额"  datatype="n" ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="moneyFee" class="col-sm-3 control-label">手续费：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="moneyFee" name="moneyFee" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入手续费"  datatype="n" ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="moneyAct" class="col-sm-3 control-label">入账金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="moneyAct" name="moneyAct" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入入账金额"  datatype="n" ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="state" class="col-sm-3 control-label">交易状态：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="state" name="state" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入交易状态"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="enter" class="col-sm-3 control-label">入账状态：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="enter" name="enter" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入入账状态"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="outOrderNo" class="col-sm-3 control-label">下游订单号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="outOrderNo" name="outOrderNo" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入下游订单号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="orderNo" class="col-sm-3 control-label">我方订单号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="orderNo" name="orderNo" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入我方订单号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="orderId" class="col-sm-3 control-label">上游订单号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="orderId" name="orderId" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入上游订单号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="notifyUrl" class="col-sm-3 control-label">异步通知地址：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="notifyUrl" name="notifyUrl" type="text" maxlength="200" class="form-control input-sm" placeholder="请输入异步通知地址"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="sysnNotifyUrl" class="col-sm-3 control-label">同步跳转页面：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="sysnNotifyUrl" name="sysnNotifyUrl" type="text" maxlength="200" class="form-control input-sm" placeholder="请输入同步跳转页面"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="notifyFlag" class="col-sm-3 control-label">通知结果：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="notifyFlag" name="notifyFlag" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入通知结果"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="notifyTime" class="col-sm-3 control-label">通知次数：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="notifyTime" name="notifyTime" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入通知次数"  ignore="ignore" />
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