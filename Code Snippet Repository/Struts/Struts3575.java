    public void testTitleAndContentType() throws Exception {
        Mock mockRequest = mock(RenderRequest.class);
        Mock mockResponse = mock(RenderResponse.class);
        Mock mockRd = mock(PortletRequestDispatcher.class);

        RenderRequest req = (RenderRequest)mockRequest.proxy();
        RenderResponse res = (RenderResponse)mockResponse.proxy();
        PortletRequestDispatcher rd = (PortletRequestDispatcher)mockRd.proxy();
        PortletContext ctx = (PortletContext)mockCtx.proxy();

        Constraint[] params = new Constraint[]{same(req), same(res)};
        mockRd.expects(once()).method("include").with(params);
        mockCtx.expects(once()).method("getRequestDispatcher").with(eq("/WEB-INF/pages/testPage.jsp")).will(returnValue(rd));
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));

        mockRequest.stubs().method("getPortletMode").will(returnValue(PortletMode.VIEW));

        ActionContext ctxMap = ActionContext.getContext();
        ctxMap.put(RESPONSE, res);
        ctxMap.put(REQUEST, req);
        ctxMap.put(SERVLET_CONTEXT, ctx);
        ctxMap.put(PHASE, PortletPhase.RENDER_PHASE);

        mockResponse.expects(atLeastOnce()).method("setTitle").with(eq("testTitle"));
        mockResponse.expects(atLeastOnce()).method("setContentType").with(eq("testContentType"));

        PortletResult result = new PortletResult();
        result.setTitle("testTitle");
        result.setContentType("testContentType");
        result.doExecute("/WEB-INF/pages/testPage.jsp", (ActionInvocation)mockInvocation.proxy());
    }
