    public void testDoExecute_render() {
        Mock mockRequest = mock(RenderRequest.class);
        Mock mockResponse = mock(RenderResponse.class);
        Mock mockRd = mock(PortletRequestDispatcher.class);

        RenderRequest req = (RenderRequest)mockRequest.proxy();
        RenderResponse res = (RenderResponse)mockResponse.proxy();
        PortletRequestDispatcher rd = (PortletRequestDispatcher)mockRd.proxy();
        PortletContext ctx = (PortletContext)mockCtx.proxy();
        ActionInvocation inv = (ActionInvocation)mockInvocation.proxy();

        Constraint[] params = new Constraint[]{same(req), same(res)};
        mockRd.expects(once()).method("include").with(params);
        mockCtx.expects(once()).method("getRequestDispatcher").with(eq("/WEB-INF/pages/testPage.jsp")).will(returnValue(rd));
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));
        mockResponse.expects(once()).method("setContentType").with(eq("text/html"));

        mockRequest.stubs().method("getPortletMode").will(returnValue(PortletMode.VIEW));

        ActionContext ctxMap = ActionContext.getContext();
        ctxMap.put(RESPONSE, res);
        ctxMap.put(REQUEST, req);
        ctxMap.put(SERVLET_CONTEXT, ctx);
        ctxMap.put(PHASE, PortletPhase.RENDER_PHASE);

        PortletResult result = new PortletResult();
        try {
            result.doExecute("/WEB-INF/pages/testPage.jsp", inv);
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("Error occured!");
        }

    }
