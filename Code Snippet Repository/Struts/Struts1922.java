    public void setUp() throws Exception {
        super.setUp();
        test = new TestAction();
        interceptor = new AnnotationValidationInterceptor();
        container.inject(interceptor);
        config = new ActionConfig.Builder("", "foo", "").build();
        mockActionInvocation = new Mock(ActionInvocation.class);
        mockActionProxy = new Mock(ActionProxy.class);
        mockActionInvocation.matchAndReturn("getProxy", (ActionProxy) mockActionProxy.proxy());
        mockActionInvocation.matchAndReturn("getAction", test);
        mockActionInvocation.expect("invoke");

        ActionContext.getContext().setActionInvocation((ActionInvocation) mockActionInvocation.proxy());
    }
