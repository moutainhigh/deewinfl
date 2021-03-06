<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kernal.utils.FileUtil,com.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>经销商文件管理</title>
    <!--css sheet-->
    <%@include file="/base/mini.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	</style>
	
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
		seajs.use("js/apcomponent/aptable/aptable.js",function(ApTable){
			new ApTable({	 
         renderTo:'id_tableContainer',
         id:'dealer_file',
         title:'经销商合同文件管理',
         width:window.formWidth||parseInt("${param.tableWidth}")||pageWidth,
         height:parseInt("${param.tableHeight}")||pageHeight,
         xmlFileName:'/eleasing/workflow/dealer_file_management/dealer_file_open_list.xml',
         pageSize:20,
         windowTop:20,
		    showPager:true, 
		  showFooter:false,
		    showToolbar:false,
		    hiddenQueryArea : false,
         columns:[
                    {type:'indexcolumn'},
                    {header:'经销商主键',field:'cust_id',visible:false},
		            {header:'经销商名称',field:'dealername',queryConfig:{},
	                	  renderer:showOverdueName,
	                	  width:180},
		            {header:'经销商合作字编号',field:'contract_num' },
		            {header:'所属区域',field:'ownerdistrict' },
		            {header:'办事处',field:'bsc'},
		            {header:'签署状态',field:'filestatus',visible:false,queryConfig:{
		            	fieldVisible:true,
						colspan : 1,
						type : 'combobox',//表单域类型
						valueField : 'value',
						textField : 'name',
						allowInput : false,
						showNullItem : false,
						data : [{name : '已完成协议签署',value : '0'},{name : '未完成协议签署',value : "1"},{name : '不合作经销商',value : "2"}]
		            }},
		            {header:'签署状态',field:'filestatusname'},
		            {header:'备注',field:'memo'},
	        ]
   	 });
   })});;
</script>
<script type="text/javascript">
	function showOverdueName(e){
  	  var rowData=e.record; 
  	  var base = "<a href='#' onclick=showFileInfo('"+rowData.cust_id+"','"+rowData.filestatus+"')>{1}</a>{2}";
  	  var separator = "&nbsp;&nbsp;";
        var updateFlag=rowData.dealername;
        var updateClickFunc="other";
        var field=base.replace("{1}",updateFlag).replace("{2}",separator);
        return field;
    };
	 function showFileInfo(custid,isbuhezuo){
	    	var URL = "${pageContext.request.contextPath}/workflow/forms/dealer_file_management/dealer_file_detail.bi";
	    	var params = {cust_id:custid,isbuhezuo:isbuhezuo};
		    openFullScreenWindow(URL,params);
	        //window.open(getRootPath()+"/leasing/law_cust/show_template.bi?cust_id="+cust_id+"&jsp=law_cust_all_info",'','height=500px,width=1000px,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	    }
</script>

</head>
<body>
	<div id="id_tableContainer"></div>
</body>
</html>