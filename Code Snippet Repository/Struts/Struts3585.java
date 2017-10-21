    public void testSetPortletMode() throws Exception  {

        PortletMode mode = PortletMode.HELP;

        mockHttpReq.stubs().method("getQueryString").will(returnValue(""));

        mockPortletRes.expects(once()).method("createRenderURL").will(
                returnValue(mockPortletUrl.proxy()));
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));

        Map paramMap = new HashMap();
        paramMap.put(PortletConstants.ACTION_PARAM, new String[]{"/help/testAction"});
        paramMap.put(PortletConstants.MODE_PARAM, new String[]{mode.toString()});

        mockPortletUrl.expects(once()).method("setParameters").with(new ParamMapConstraint(paramMap));
        mockPortletUrl.expects(once()).method("setPortletMode").with(eq(PortletMode.HELP));
        mockPortletUrl.expects(once()).method("setWindowState").with(eq(WindowState.NORMAL));

        tag.setNamespace("/help");
        tag.setAction("testAction");
        tag.setPortletMode("help");
        tag.doStartTag();
        tag.doEndTag();
    }
