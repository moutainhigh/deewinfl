package org.apache.jsp.WEB_002dINF.jsps.solutions.report;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Form_005fPage_005fConfig_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<java.lang.String>(1);
    _jspx_dependants.add("/WEB-INF/tlds/spring.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fmessage_0026_005ftext_005fcode_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<java.lang.String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005fmessage_0026_005ftext_005fcode_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    if (_005fjspx_005ftagPool_005fs_005fmessage_0026_005ftext_005fcode_005fnobody != null) _005fjspx_005ftagPool_005fs_005fmessage_0026_005ftext_005fcode_005fnobody.release();
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

      out.write("\n");
      out.write("\n");
      out.write("<form id=\"page_form\" class=\"mini-form\" method=\"post\">\n");
      out.write("\t<div style=\"display:none\">\n");
      out.write("\t \t<input type=\"text\" class=\"mini-textbox\" name=\"page_id\"  id=\"page_id\"/>\n");
      out.write("        <input type=\"text\" class=\"mini-textbox\" name=\"page_layoutId\" id=\"page_layoutId\" />        \n");
      out.write("    </div>\n");
      out.write("    <div id=\"page_tabs\" class=\"mini-tabs\" activeIndex=\"0\" style=\"width:100%;height:100%\" plain=\"false\" borderStyle=\"border:1px solid #ddd\" bodyStyle=\"border:1px solid #ddd;height:100%\" headerStyle=\"border:1px soldi #ddd\">\n");
      out.write("    \t<div title=\"");
      if (_jspx_meth_s_005fmessage_005f0(_jspx_page_context))
        return;
      out.write("\" class=\"form_tab\">\n");
      out.write("    \t\t<table style=\"width:90%\">\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td class=\"input_label_desc\">名称:</td>\n");
      out.write("\t\t\t\t\t<td class=\"input_value\">\n");
      out.write("\t\t\t\t\t\t<input id=\"page_name\" name=\"page_name\" require=\"true\"  type=\"text\" class=\"mini-textbox\" /><span class=\"content-required\">*</span>\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td class=\"input_label_desc\">链接:</td>\n");
      out.write("\t\t\t\t\t<td class=\"input_value\"><input id=\"page_url\" name=\"page_url\" require=\"true\"  type=\"text\" class=\"mini-textbox\"/><span class=\"content-required\">*</span></td>\n");
      out.write("\t\t\t\t</tr>\t\n");
      out.write("\t\t\t\t<tr id=\"tr_layout_width\" style=\"display:none\">\n");
      out.write("\t\t\t\t\t<td class=\"input_label_desc\">页面宽度:</td>\n");
      out.write("\t\t\t\t\t<td class=\"input_value\"><input type=\"text\"  name=\"page_layout_width\" id=\"page_layout_width\" value=\"600\"/><span class=\"content-required\">px</span></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr id=\"tr_layout_height\" style=\"display:none\">\n");
      out.write("\t\t\t\t\t<td class=\"input_label_desc\">页面高度:</td>\n");
      out.write("\t\t\t\t\t<td class=\"input_value\"><input type=\"text\"  name=\"page_layout_height\" id=\"page_layout_height\" value=\"300\"/><span class=\"content-required\">px</span></td>\n");
      out.write("\t\t\t\t</tr>\t\t\t\n");
      out.write("\t\t\t</table>\n");
      out.write("    \t</div>\n");
      out.write("    </div>\n");
      out.write("</form>\n");
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

  private boolean _jspx_meth_s_005fmessage_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_s_005fmessage_005f0 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fs_005fmessage_0026_005ftext_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_s_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fmessage_005f0.setParent(null);
    // /WEB-INF/jsps/solutions/report/Form_Page_Config.jsp(9,17) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fmessage_005f0.setCode("report.config.form.page.basic");
    // /WEB-INF/jsps/solutions/report/Form_Page_Config.jsp(9,17) name = text type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fmessage_005f0.setText("基本");
    int[] _jspx_push_body_count_s_005fmessage_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_s_005fmessage_005f0 = _jspx_th_s_005fmessage_005f0.doStartTag();
      if (_jspx_th_s_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_s_005fmessage_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_s_005fmessage_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_s_005fmessage_005f0.doFinally();
      _005fjspx_005ftagPool_005fs_005fmessage_0026_005ftext_005fcode_005fnobody.reuse(_jspx_th_s_005fmessage_005f0);
    }
    return false;
  }
}
