<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未办事宜</title>
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
        <script type="text/javascript">
	      var all_width = (document.documentElement||document.body).clientWidth-2;
	      var all_height = (document.documentElement||document.body).clientHeight-2;
	    </script>
	    <style type="text/css">
	       html,body{
	         overflow:hidden;
	       }
	    </style>
</head>
<body style="overflow:hidden;"> 
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
	
	var processDefinitionsColumns = [
		{header:'流水号',name:'serialno',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		 return '<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">'+rowData.serialno+'</a>';
		}},
		{header:'项目名称',name:'projectname'},
		{header:'流程名称',name:'workflowname'},
		{header:'任务名称',name:'taskname'},
		{header:'任务类型',name:'tasktype',renderer:function(value){return getTaskTypeChineseName(value);},width:80},
		{header:'任务开始时间',name:'taskstart',width:150},
		{header:'提交类型',name:'sourcetype',width:150},
		{header:'执行人编号',name:'actorid',hidden:true},
		{header:'流程开始时间',name:'processstart',width:150},
		{header:'操作',name:'oper',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+rowData.processinstanceid+'\',\''+rowData.actorid+'\')">显示流程图</a>'
		+'&nbsp;&nbsp;&nbsp;&nbsp;'
		+'<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">查看</a>';
		}}
	];
	var tableActivityDetail= new tracywindyTable({
		renderTo:'id_tasksContainer',
		isAutoBreakContent:true,
		width:all_width,
		height:all_height,
		isCheck:false,
		isRank:false,
		toolsLeftMargin:20,
		tools:[{
	    	  isHtml:true,
	    	  html:'全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;" id="id_queryWorkflowsTableInput" value="${param.queryText}" />'
	        }],
		id:'id_tasks_table',
		xmlFileName:'/jbpm/queryPendingTasks.xml',
		isFit:true,
		isPage:true,
		border:true,
		title:'待办事宜',
		loadMode:'ajax',
		isExcel:true,
		columns:processDefinitionsColumns,
		params:{
		  USERID:"${sessionScope['login_userid']}",
		  NOTPROCESSINSTANCESTATE:'Draft',
		  PENDING:true
		}
	});
	 document.getElementById("id_queryWorkflowsTableInput").onkeypress = function(evt){
		 var e  = getEvent(evt);
	     var code = e.keyCode||e.charCode;
	     if(13 == code){
	         var workflowsTable = getTracywindyTable("id_tasks_table");
	         workflowsTable.setParams({
	                queryText:this.value.toUpperCase()
	         });
	         workflowsTable.reload();
	     }
	 };
	//显示流程图
	function toProcessActivePicture(deployId,processInstanceId,planActorId)
	{
		var url = "${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId="+deployId+"&processInstanceId="+escape(encodeURIComponent(processInstanceId))+"&jbpmWorkflowHistoryInfoUserId="+planActorId+"&randomNumber="+Math.random();
		openFullScreenWindow(url);
	}
	function doQueryByText_pending()
	{
	  var contentText = document.all['id_contextText_pending'].value;
	  var tableContact = getTracywindyTable("pendingRequestTable");
	  tableContact.params['proj_id'] = contentText.toUpperCase();
	  tableContact.reload();
	}
</script>
<script type="text/javascript">
function toProcessForm(currentTaskId,planActorId,tasktype)
{
	var url = "${pageContext.request.contextPath}/jbpm/requestProcessTaskForm.action?currentTaskId="+currentTaskId+"&jbpmWorkflowHistoryInfoUserId="+planActorId+"&currentRequestTaskType="+tasktype;
	openFullScreenWindow(url);
}
</script>
</body>
</html>