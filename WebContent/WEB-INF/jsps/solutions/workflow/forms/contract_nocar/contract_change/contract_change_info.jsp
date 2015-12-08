<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>合同变更流程</title>
</head>
<body>
    
<jsp:include page="../contract_approval/boot.jsp"></jsp:include>
   <div id="contract_change_form1">
	    <table class="fillTable" cellspacing="0" cellpadding="0">
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title" style="width:10%">变更日期：</td><td class="td-content">
	             <input id="id_contract_other_info_contractchangedate" name="contract_other_info.contractchangedate" class="mini-datepicker" required="true"  allowInput="false"/>
	             </td>
	          </tr>
	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">变更说明：</td>
	             <td class="td-content" colspan=5 >
	             <input id="id_contract_other_info_contractchangememo" style="width:72%" name="contract_other_info.contractchangememo"  required="true" label="变更原因" class="mini-textarea"  type="text" > 
	             </td>
	          </tr>
	            
	</table>
</div>
</body>
</html>
<script language="javascript">
    //控制只读页面
	mini.parse("contract_change_form1");
	debugger;
	var repealdate = parent.document.getElementById("id_contract_other_info_contractchangedate").value;
	mini.get("id_contract_other_info_contractchangedate").setValue(repealdate);
	var repealreason = parent.document.getElementById("id_contract_other_info_contractchangememo").value;
	mini.get("id_contract_other_info_contractchangememo").setValue(repealreason);
	
	var isViewHistoryTask="<%=request.getParameter("isViewHistoryTask")%>";
	var form = new mini.Form("contract_change_form1");
	debugger;
    //var grid=mini.get("proj_change_form1");
	if (isViewHistoryTask == "true") {
		var o = form.getData();
		var fields = form.getFields();
		for (var index = 0; index < fields.length; index++) {
			//判断是否是下拉框控件，是则同时把text属性传入后台
			if (fields[index].uiCls == "mini-combobox"
					|| fields[index].uiCls == "mini-datepicker") {
				fields[index].disable();
			} else {
				fields[index].allowInput = false;
			}
		}
	}
	//调用返回到主页面
	function returnTab() {
		return form;
	}
</script>

