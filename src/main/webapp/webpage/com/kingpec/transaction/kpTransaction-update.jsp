<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>交易流水</title>
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
 <form id="formobj" action="kpTransactionController.do?doUpdate" class="form-horizontal validform" role="form"  method="post">
	<input type="hidden" id="btn_sub" class="btn_sub"/>
	<input type="hidden" id="id" name="id" value="${kpTransactionPage.id}"/>
	<div class="form-group">
		<label for="siteName" class="col-sm-3 control-label">站点名称：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="siteName" name="siteName" value='${kpTransactionPage.siteName}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入站点名称"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="mercNo" class="col-sm-3 control-label">商户号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="mercNo" name="mercNo" value='${kpTransactionPage.mercNo}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入商户号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="termNo" class="col-sm-3 control-label">终端号：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="termNo" name="termNo" value='${kpTransactionPage.termNo}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入终端号"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="tranTime" class="col-sm-3 control-label">交易时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
      		    <input id="tranTime" name="tranTime" type="text" class="form-control input-sm" placeholder="请输入交易时间"  ignore="ignore"   value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' type='both' value='${kpTransactionPage.tranTime}'/>" />
                <span class="input-group-addon" >
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="tranCode" class="col-sm-3 control-label">交易码：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="tranCode" name="tranCode" value='${kpTransactionPage.tranCode}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入交易码"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="tranMoney" class="col-sm-3 control-label">交易金额：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="tranMoney" name="tranMoney" value='${kpTransactionPage.tranMoney}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入交易金额"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="tranState" class="col-sm-3 control-label">交易状态：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="tranState" name="tranState" value='${kpTransactionPage.tranState}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入交易状态"  ignore="ignore" />
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	var subDlgIndex = '';
	$(document).ready(function() {
				//交易时间 日期控件初始化
			    laydate.render({
				   elem: '#tranTime'
				  ,type: 'datetime'
				  ,trigger: 'click' //采用click弹出
				  ,ready: function(date){
				  	 $("#tranTime").val(DateJsonFormat(date,this.format));
				  }
				});
		
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