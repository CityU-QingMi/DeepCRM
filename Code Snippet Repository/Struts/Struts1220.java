    @Override
    protected void setUp() throws Exception {
        super.setUp();
        config = new ActionConfig.Builder("", "name", "").build();
        action = EasyMock.createNiceMock(ValidateAction.class);
        invocation = EasyMock.createNiceMock(ActionInvocation.class);
        interceptor = new DefaultWorkflowInterceptor();
        proxy = EasyMock.createNiceMock(ActionProxy.class);

        EasyMock.expect(invocation.getProxy()).andReturn(proxy).anyTimes();
        EasyMock.expect(invocation.getAction()).andReturn(action).anyTimes();
        EasyMock.expect(invocation.invoke()).andAnswer(new IAnswer<String>() {
            public String answer() throws Throwable {
                return result;
            }
        }).anyTimes();

        EasyMock.expect(proxy.getConfig()).andReturn(config).anyTimes();
        EasyMock.expect(proxy.getMethod()).andReturn("execute").anyTimes();


        EasyMock.replay(invocation);
        EasyMock.replay(action);
        EasyMock.replay(proxy);

        ActionContext actionContext = new ActionContext(new HashMap<String, Object>());
        ActionContext.setContext(actionContext);
        actionContext.setActionInvocation(invocation);
    }
