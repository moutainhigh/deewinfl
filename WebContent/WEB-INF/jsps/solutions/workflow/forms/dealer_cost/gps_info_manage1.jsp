<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
//isViewHistoryTask = false;
jQuery(function(){
	    new tracywindyMultiTable({
	    renderTo:'id_table_leasing_proj_equip_container_info',
	    width:formWidth,
	    height:formHeight,
	    isAutoHeight:true,
	    isRemoteSortable:false,
	    //isShowCopyCount:true,
	    // isNeedTools:currentIsNeedTools,
	    isFit:true,
	    tools:'修改',
	    datas:JsonUtil.decode('${empty json_proj_gps_str ? "[]" : json_proj_gps_str }'),
	    columns:[
				{header:'车型号',name:'model_'},
				{header:'车架号',name:'chassis_num'},
				//{header:'设备名称',name:'equip_name'},
				//{header:'是否安装GPS',name:'isgpsname'},
				{header:'车架号id',name:'equipid',hidden:true},
				{header:'经销商id',name:'custdealer',hidden:true},
				{header:'客户名称id',name:'custid',hidden:true},
				{header:'合同id',name:'contractid',hidden:true},
				{header:'GPS软件网址/软件名',name:'gpsname',width:140,renderer:showCustName,nullable:false},
				{header:'GPS用户名',name:'gpsuser',width:100,nullable:false},
				{header:'GPS密码',name:'gpspassword',width:100,nullable:false},
				{name:'memo',header:'备注',width:100}
		     	
	    ]
	 });
	    
});
function showCustName(value,tableObj,columnName,columnIndex,rowData){
	//<a href="http://www.w3school.com.cn/">Visit W3School</a>
	var base = "<a href='"+rowData.gpsname+"' target='_blank' onclick='return testname(\""+rowData.gpsname+"\")'>{1}</a>{2}";
    var separator = "&nbsp;&nbsp;";
    var updateFlag=value;
    // var updateClickFunc="other";
    var field=base.replace("{1}",updateFlag).replace("{2}",separator);
    return field;
  }
 function testname(field){
	 if(field.indexOf("http://")<0){
	    	alert("不是有效的网址,不能链接,例如：http://www.deewinfl.com/");
	    	return false;
	    }else{
	    	return true;
	    }
 } 
</script>
