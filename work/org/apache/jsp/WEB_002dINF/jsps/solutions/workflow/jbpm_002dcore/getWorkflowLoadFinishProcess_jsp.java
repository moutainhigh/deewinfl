package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.jbpm_002dcore;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class getWorkflowLoadFinishProcess_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(function() {\r\n");
      out.write("    jQuery(\"div.leftComboContainer\").css(\"border\", \"0px solid silver\");\r\n");
      out.write("    jQuery(\"div.leftComboContainer\").css(\"height\", \"auto\");\r\n");
      out.write("    if (isViewHistoryTask) {\r\n");
      out.write("        var submitForm = document.getElementById(\"id_submitProcessedForm\");\r\n");
      out.write("        var formElements = submitForm.elements;\r\n");
      out.write("        //jQuery(\"#id_submitProcessedForm input[type='text'],#id_submitProcessedForm textarea\").addClass(\"element-readonly\");\r\n");
      out.write("        for (var i = 0; i < formElements.length; i++) {\r\n");
      out.write("            var formElement = formElements[i];\r\n");
      out.write("            formElement.readOnly = true;\r\n");
      out.write("            //jQuery(\"#id_submitProcessedForm input[type='text'],#id_submitProcessedForm textarea\").addClass(\"element-readonly\");\r\n");
      out.write("            var currentIsReadOnly = true;\r\n");
      out.write("            if (!isCompletedTask) {\r\n");
      out.write("                if (\"true\" == (\"\" + formElement.getAttribute(\"isForceModify\"))) {\r\n");
      out.write("                    currentIsReadOnly = false;\r\n");
      out.write("                }\r\n");
      out.write("            } else {\r\n");
      out.write("                var currentElementType = (formElement.getAttribute(\"type\") || \"\").toLowerCase();\r\n");
      out.write("                if ((\"radio\" == currentElementType) || (\"checkbox\" == currentElementType) || (\"select\" == currentElementType)) {\r\n");
      out.write("                    formElement.disabled = true;\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("            if (currentIsReadOnly) {\r\n");
      out.write("                if (formElement.className.indexOf(\"Wdate\") > -1) {\r\n");
      out.write("                    formElement.onclick = null;\r\n");
      out.write("                }\r\n");
      out.write("                removeClass(formElement, \"Wdate\");\r\n");
      out.write("                if(formElement.type != 'button'){\r\n");
      out.write("\t                addClass(formElement, \"element-readonly\");\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("        if (!isCompletedTask) {\r\n");
      out.write("            if (window.formPageReadOnlyCallBack) {\r\n");
      out.write("                window.formPageReadOnlyCallBack(submitForm);\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    document.documentElement.style.overflow = \"hidden\";\r\n");
      out.write("    document.body.style.overflow = \"hidden\";\r\n");
      out.write("    var iframes = window.top.frames;\r\n");
      out.write("    var iframesLen = iframes.length;\r\n");
      out.write("    var index = 0;\r\n");
      out.write("    setTimeout('jQuery(\".easyui-linkbutton-btn\").linkbutton(\"enable\");', iframesLen * 250);\r\n");
      out.write("});\r\n");
      out.write("</script>");
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
