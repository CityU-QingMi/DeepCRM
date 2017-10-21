  private boolean _jspx_meth_s_005fparam_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005furl_005f4, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f2 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f4);
    // /WEB-INF/jsps/editor/EntryEdit.jsp(281,20) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f2.setName("removeId");
    // /WEB-INF/jsps/editor/EntryEdit.jsp(281,20) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f2.setValue("%{entry.id}");
    int _jspx_eval_s_005fparam_005f2 = _jspx_th_s_005fparam_005f2.doStartTag();
    if (_jspx_th_s_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fparam_005f2);
    return false;
  }
