  private boolean _jspx_meth_s_005fparam_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005furl_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f4 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f2);
    // /WEB-INF/jsps/editor/EntrySidebar.jsp(52,28) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f4.setName("weblog");
    // /WEB-INF/jsps/editor/EntrySidebar.jsp(52,28) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f4.setValue("%{actionWeblog.handle}");
    int _jspx_eval_s_005fparam_005f4 = _jspx_th_s_005fparam_005f4.doStartTag();
    if (_jspx_th_s_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fparam_005f4);
    return false;
  }
