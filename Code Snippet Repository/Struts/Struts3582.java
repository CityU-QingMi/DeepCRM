    public void testUrlWithNoActionOrMethod() throws Exception {
    	PortletMode mode = PortletMode.VIEW;
    	mockHttpReq.stubs().method("getQueryString").will(returnValue(""));
        mockPortletRes.expects(once()).method("createRenderURL").will(
                returnValue(mockPortletUrl.proxy()));
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));
    	Map paramMap = new HashMap();
    	
    	
    	mockActionProxy.stubs().method("getActionName").will(returnValue("currentExecutingAction"));
    	
    	
    	paramMap.put(PortletConstants.ACTION_PARAM, new String[]{"/view/currentExecutingAction"});
        paramMap.put(PortletConstants.MODE_PARAM, new String[]{mode.toString()});
        mockPortletUrl.expects(once()).method("setParameters").with(new ParamMapConstraint(paramMap));
        mockPortletUrl.expects(once()).method("setPortletMode").with(eq(PortletMode.VIEW));
        mockPortletUrl.expects(once()).method("setWindowState").with(eq(WindowState.NORMAL));
    	tag.doStartTag();
    	tag.doEndTag();    	
    }
