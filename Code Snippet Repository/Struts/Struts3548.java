    public void setUp() throws Exception {
        super.setUp();
        mockRenderRequest = mock(RenderRequest.class);
        mockRenderResponse = mock(RenderResponse.class);
        mockActionRequest = mock(ActionRequest.class);
        mockActionResponse = mock(ActionResponse.class);
        mockPortletConfig = mock(PortletConfig.class);

        renderRequest = (RenderRequest)mockRenderRequest.proxy();
        renderResponse = (RenderResponse)mockRenderResponse.proxy();
        actionRequest = (ActionRequest)mockActionRequest.proxy();
        actionResponse = (ActionResponse)mockActionResponse.proxy();
        portletConfig = (PortletConfig)mockPortletConfig.proxy();


        ActionContext.setContext(new ActionContext(context));
    }
