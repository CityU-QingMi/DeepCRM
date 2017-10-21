    public void setUp() throws Exception {
        super.setUp();
        mockInvocation = mock(ActionInvocation.class);
        mockCtx = mock(PortletContext.class);
        mockProxy = mock(ActionProxy.class);

        Map<String, Object> sessionMap = new HashMap<String, Object>();

        Map<String, Object> context = new HashMap<String, Object>();
        context.put(SESSION, sessionMap);
        context.put(PARAMETERS, HttpParameters.create().build());
        context.put(STRUTS_PORTLET_CONTEXT, mockCtx.proxy());

        ActionContext.setContext(new ActionContext(context));
        mockProxy.stubs().method("getNamespace").will(returnValue("/test"));
        proxy = (ActionProxy) mockProxy.proxy();
        mockInvocation.stubs().method("getInvocationContext").will(returnValue(ActionContext.getContext()));
        mockInvocation.stubs().method("getProxy").will(returnValue(proxy));
        invocation = (ActionInvocation) mockInvocation.proxy();

    }
