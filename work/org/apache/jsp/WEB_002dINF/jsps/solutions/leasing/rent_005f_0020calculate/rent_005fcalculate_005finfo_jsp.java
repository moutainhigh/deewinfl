package org.apache.jsp.WEB_002dINF.jsps.solutions.leasing.rent_005f_0020calculate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kernal.utils.FileUtil;
import com.kernal.utils.WebUtil;

public final class rent_005fcalculate_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<java.lang.String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>测算表生成</title>\r\n");
      out.write("    <!--css sheet-->\r\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/dtree/dtree.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/jquery-easyui/easyui.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/jquery-easyui/icon.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/tracywindy/tracywindy.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/tracywindy/button.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/tracywindy/workFlowUtil.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t<!--javascript libray-->\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/workFlowUtil.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyUtils.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyJsonUtil.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyAjax.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyLoadMask.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyTable.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyComboBox.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindySerializeFormToJsonObject.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/my97DatePicker/WdatePicker.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/validator.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyOperationTable.js\"></script>\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t   html,body{\r\n");
      out.write("\t     overflow:hidden;\r\n");
      out.write("\t   }\r\n");
      out.write("\t</style>\r\n");
      out.write("<script type=\"text/javascript\"> \r\n");
      out.write(" //var proj_id=\"");
      out.print( request.getParameter("id"));
      out.write("\";\r\n");
      out.write("   var param={};\r\n");
      out.write("   //if(proj_id!=\"null\" || proj_id==\"\"){param.proj_id=proj_id;}\r\n");
      out.write("\tvar pageWidth  = document.documentElement.clientWidth-2;\r\n");
      out.write("\tvar pageHeight = document.documentElement.clientHeight-2;\r\n");
      out.write("\tjQuery(function(){\r\n");
      out.write("   \t var table = new tracywindyOperationTable({\r\n");
      out.write("   \t\t tableComment:'[测算信息]',\r\n");
      out.write("   \t\t constantFlagTable:'ProjInfo',\r\n");
      out.write("   \t\t windowTop:20,\r\n");
      out.write("   \t     border:true,\r\n");
      out.write("         renderTo:'id_tableContainer',\r\n");
      out.write("         title:'测算信息',\r\n");
      out.write("         width:parseInt(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.tableWidth}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\")||pageWidth,\r\n");
      out.write("         height:parseInt(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.tableHeight}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\")||pageHeight,\r\n");
      out.write("         id:'id_table',\r\n");
      out.write("         xmlFileName:'\\\\eleasing\\\\jsp\\\\fund_bankcarddown\\\\rent_calc_list.xml',\r\n");
      out.write("         loadMode:'ajax',\r\n");
      out.write("         params:{\r\n");
      out.write("   \t\t\tuser_id:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.loginUser.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("   \t\t\tloginuser:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope['login_userid']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",\r\n");
      out.write("   \t\t\tCONTRACT_STATUS_LessThan : '100', \r\n");
      out.write("   \t\t\tCONTRACT_STATUS_GreaterThan : '21'\r\n");
      out.write("   \t\t\t//work_flag : '0'\r\n");
      out.write("\t     },\r\n");
      out.write("         isPage:true,\r\n");
      out.write("         operButtons:' ',\r\n");
      out.write("         isFit:false,\r\n");
      out.write("         isExcel:true,\r\n");
      out.write("         isAutoBreakContent:true,\r\n");
      out.write("         tools:[{\r\n");
      out.write("        \t html : '<font color=\"red\">生成测算表</font>',\r\n");
      out.write(" \t\t\thandler : function(table) {\r\n");
      out.write(" \t\t\t\tvar projIDValue = table.getCheckedRowDatas();\r\n");
      out.write(" \t\t\t\tif (0 == projIDValue.length) {\r\n");
      out.write(" \t\t\t\t\talert(\"请选择需要生成测算的项目！\");\r\n");
      out.write(" \t\t\t\t\treturn false;\r\n");
      out.write(" \t\t\t\t}\r\n");
      out.write(" \t\t\t\t\r\n");
      out.write(" \t\t\t\tvar attachmentParams = \"contract_id=\" + projIDValue[0][\"id\"]+\"&cust_id=\"+projIDValue[0][\"custid\"];\r\n");
      out.write(" \t\t\t\tvar url=\"/leasing/template/createFileByTemplateClass.action\";\r\n");
      out.write(" \t\t\t    //var filename='table_projcreditreport';\r\n");
      out.write(" \t\t\t    var custname=projIDValue[0][\"contractnumber\"]+\"-\"+projIDValue[0][\"custdealer\"]+\"-\"+projIDValue[0][\"custname\"]+'租金测算表.xls';\r\n");
      out.write(" \t\t\t\tvar parames=[];\r\n");
      out.write(" \t\t\t    parames.push({filename:custname});\r\n");
      out.write(" \t\t\t    attachmentDown({uploadid:\"\",url:url,title:\"\",twoClassify:'wordtempletypetwo.55',returnType:'file',modelname:'rentcalculate',contractid:projIDValue[0][\"id\"],parames:parames});\r\n");
      out.write(" \t\t\t \r\n");
      out.write(" \t\t\t\t\r\n");
      out.write(" \t\r\n");
      out.write(" \t\t\t},\r\n");
      out.write(" \t\t\ticonCls :'icon-update'\r\n");
      out.write("             }],\r\n");
      out.write("         columns:[\r\n");
      out.write("\t\t            {header:'id',name:'id',hidden:true},\r\n");
      out.write("\t\t            {header:'custid',name:'custid',hidden:true},\r\n");
      out.write("\t\t            {header:'项目编号',name:'projid'},\r\n");
      out.write("\t\t            {header:'合同编号',name:'contractid'},\r\n");
      out.write("\t\t            {header:'合同号',name:'contractnumber',width:200,queryConfig:{}},\r\n");
      out.write("\t\t            {header:'合同状态',name:'contractstatus'},\r\n");
      out.write("\t\t            {header:'客户名称',name:'custname',width:150,queryConfig:{}},\r\n");
      out.write("\t\t            {header:'经销商名称',name:'custdealer',queryConfig:{isNewLine:true}},\r\n");
      out.write("\t\t            {header:'业务类别',name:'businesstype'},\r\n");
      out.write("\t\t            {header:'业务模式',name:'businessmode'},\r\n");
      out.write("\t\t            {header:'区域',name:'district'},\r\n");
      out.write("\t\t            {header:'合同金额',name:'contractmoney'},\r\n");
      out.write("\t\t            {header:'合同起租日',name:'acceptdate',type:'date',isRange:true,queryConfig:{}}\r\n");
      out.write("         ]\r\n");
      out.write("   \t });\r\n");
      out.write("   });\r\n");
      out.write("\t\r\n");
      out.write(" </script>\r\n");
      out.write(" \r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div id=\"id_tableContainer\"></div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
