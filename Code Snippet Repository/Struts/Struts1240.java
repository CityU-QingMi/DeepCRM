    @Override
    protected void setUp() throws Exception {
        super.setUp();
        contextMap = new HashMap<>();
        stack = ActionContext.getContext().getValueStack();
        mockInvocation = new Mock(ActionInvocation.class);
        mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));
        mockInvocation.expectAndReturn("getStack", stack);
        mockInvocation.expectAndReturn("invoke", Action.SUCCESS);
        mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));
        mockInvocation.matchAndReturn("getAction", new SimpleAction());
        invocation = (ActionInvocation) mockInvocation.proxy();
        interceptor = new ParameterFilterInterceptor();
        interceptor.init();
    }
