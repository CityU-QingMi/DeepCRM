  private boolean _jspx_meth_str_005ftruncateNicely_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fa_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  str:truncateNicely
    org.apache.roller.weblogger.ui.tags.TruncateNicelyTag _jspx_th_str_005ftruncateNicely_005f2 = (org.apache.roller.weblogger.ui.tags.TruncateNicelyTag) _005fjspx_005ftagPool_005fstr_005ftruncateNicely_0026_005flower.get(org.apache.roller.weblogger.ui.tags.TruncateNicelyTag.class);
    _jspx_th_str_005ftruncateNicely_005f2.setPageContext(_jspx_page_context);
    _jspx_th_str_005ftruncateNicely_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fa_005f2);
    // /WEB-INF/jsps/editor/EntrySidebar.jsp(99,51) name = lower type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_str_005ftruncateNicely_005f2.setLower("50");
    int _jspx_eval_str_005ftruncateNicely_005f2 = _jspx_th_str_005ftruncateNicely_005f2.doStartTag();
    if (_jspx_eval_str_005ftruncateNicely_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_str_005ftruncateNicely_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_str_005ftruncateNicely_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_str_005ftruncateNicely_005f2.doInitBody();
      }
      do {
        if (_jspx_meth_s_005fproperty_005f2(_jspx_th_str_005ftruncateNicely_005f2, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_str_005ftruncateNicely_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_str_005ftruncateNicely_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_str_005ftruncateNicely_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fstr_005ftruncateNicely_0026_005flower.reuse(_jspx_th_str_005ftruncateNicely_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fstr_005ftruncateNicely_0026_005flower.reuse(_jspx_th_str_005ftruncateNicely_005f2);
    return false;
  }
