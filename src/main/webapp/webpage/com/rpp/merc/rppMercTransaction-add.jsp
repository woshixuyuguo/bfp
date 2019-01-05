<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商户资金流水表</title>
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
 <form id="formobj" action="rppMercTransactionController.do?doAdd" class="form-horizontal validform" role="form"  method="post">
	<input type="hidden" id="btn_sub" class="btn_sub"/>
	<input type="hidden" id="id" name="id"/>
	<div class="form-group">
		<label for="mercCode" class="col-sm-3 control-label">商户号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="mercCode" name="mercCode" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入商户号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="type" class="col-sm-3 control-label">变动类型：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<t:dictSelect field="type" type="list" extendJson="{class:'form-control input-sm'}"  
 typeGroupCode="accountChangeType" 			 hasLabel="false"  title="变动类型"></t:dictSelect>     
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="money" class="col-sm-3 control-label">变动金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="money" name="money" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入变动金额"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="beforMoney" class="col-sm-3 control-label">变动前金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="beforMoney" name="beforMoney" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入变动前金额"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="afterMoney" class="col-sm-3 control-label">变动后金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="afterMoney" name="afterMoney" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入变动后金额"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="source" class="col-sm-3 control-label">资金来源：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<t:dictSelect field="source" type="list" extendJson="{class:'form-control input-sm'}"  
 typeGroupCode="accountChangeSource" 			 hasLabel="false"  title="资金来源"></t:dictSelect>     
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