    public void setUp() throws Exception {
        super.setUp();

        renderResponse = EasyMock.createMock(RenderResponse.class);
        renderRequest = EasyMock.createMock(RenderRequest.class);
        url = new MockUrl();
        
        EasyMock.expect(renderRequest.getPortletMode()).andReturn(PortletMode.VIEW).anyTimes();
        EasyMock.expect(renderRequest.getWindowState()).andReturn(WindowState.NORMAL).anyTimes();

        Map<String, String> modeNamespaceMap = new HashMap<String, String>();
        modeNamespaceMap.put("view", "/view");
        modeNamespaceMap.put("edit", "/edit");
        modeNamespaceMap.put("help", "/help");

        Map<String, Object> context = new HashMap<String, Object>();
        context.put(REQUEST, renderRequest);
        context.put(RESPONSE, renderResponse);
        context.put(PHASE, PortletPhase.RENDER_PHASE);
        context.put(MODE_NAMESPACE_MAP, modeNamespaceMap);

        ActionContext.setContext(new ActionContext(context));

    }
