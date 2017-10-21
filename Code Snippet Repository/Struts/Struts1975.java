    protected void setUp() throws Exception {
        super.setUp();
        stack = ActionContext.getContext().getValueStack();

        ActionContext actionContext = new ActionContext(stack.getContext());
        ActionContext.setContext(actionContext);

        session = new HashMap();
        actionContext.setSession(session);

        invocationMock = new Mock(ActionInvocation.class);
        invocation = (ActionInvocation) invocationMock.proxy();
        invocationMock.matchAndReturn("serialize", invocation);
        invocationMock.matchAndReturn("deserialize", actionContext, invocation);

        actionContext.setValueStack(stack);
        invocationMock.matchAndReturn("getStack", stack);

        Mock proxyMock = new Mock(ActionProxy.class);
        proxyMock.matchAndReturn("getInvocation", invocation);

        ActionProxy proxy = (ActionProxy) proxyMock.proxy();

        invocationMock.matchAndReturn("getProxy", proxy);
    }
