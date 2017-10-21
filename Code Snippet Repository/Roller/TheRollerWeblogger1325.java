  private boolean _jspx_meth_s_005fsubmit_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felse_005f4, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  s:submit
    org.apache.struts2.views.jsp.ui.SubmitTag _jspx_th_s_005fsubmit_005f2 = (org.apache.struts2.views.jsp.ui.SubmitTag) _005fjspx_005ftagPool_005fs_005fsubmit_0026_005fvalue_005faction_005fnobody.get(org.apache.struts2.views.jsp.ui.SubmitTag.class);
    _jspx_th_s_005fsubmit_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fsubmit_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felse_005f4);
    // /WEB-INF/jsps/editor/EntryEdit.jsp(273,16) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fsubmit_005f2.setValue("%{getText('weblogEdit.submitForReview')}");
    // /WEB-INF/jsps/editor/EntryEdit.jsp(273,16) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fsubmit_005f2.setAction("%{#mainAction}!publish");
    int _jspx_eval_s_005fsubmit_005f2 = _jspx_th_s_005fsubmit_005f2.doStartTag();
    if (_jspx_th_s_005fsubmit_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fsubmit_0026_005fvalue_005faction_005fnobody.reuse(_jspx_th_s_005fsubmit_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fsubmit_0026_005fvalue_005faction_005fnobody.reuse(_jspx_th_s_005fsubmit_005f2);
    return false;
  }
