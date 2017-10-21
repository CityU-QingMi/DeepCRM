    @Override
    protected void setUp() throws Exception {
        super.setUp();
        stack = ActionContext.getContext().getValueStack();
        mockInvocation = new Mock(ActionInvocation.class);
        mockInvocation.expectAndReturn("getStack", stack);
        mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(new HashMap<String, Object>()));
        interceptor = new ExceptionMappingInterceptor();
        interceptor.init();
    }
