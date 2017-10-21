    @Override
    protected void setUp() throws Exception {
        super.setUp();

        config = new ActionConfig.Builder("", "action", "").build();
        invocation = EasyMock.createNiceMock(ActionInvocation.class);
        proxy = EasyMock.createNiceMock(ActionProxy.class);
        action = EasyMock.createNiceMock(ValidateAction.class);


        EasyMock.expect(invocation.getProxy()).andReturn(proxy).anyTimes();
        EasyMock.expect(invocation.getAction()).andReturn(action).anyTimes();
        EasyMock.expect(invocation.invoke()).andAnswer(new IAnswer<String>() {
            public String answer() throws Throwable {
                return result;
            }
        }).anyTimes();

        EasyMock.expect(proxy.getConfig()).andReturn(config).anyTimes();
        EasyMock.expect(proxy.getMethod()).andAnswer(new IAnswer<String>() {
            public String answer() throws Throwable {
                return method;
            }
        }).anyTimes();


        EasyMock.replay(invocation);
        EasyMock.replay(action);
        EasyMock.replay(proxy);

        ActionContext contex = new ActionContext(new HashMap<String, Object>());
        ActionContext.setContext(contex);
        contex.setActionInvocation(invocation);
    }
