    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ActionConfig config = new ActionConfig.Builder("", "name", "").build();
        ValidateErrorAction action = EasyMock.createNiceMock(ValidateErrorAction.class);
        invocation = EasyMock.createNiceMock(ActionInvocation.class);
        interceptor = new DefaultWorkflowInterceptor();
        ActionProxy proxy = EasyMock.createNiceMock(ActionProxy.class);

        EasyMock.expect(action.actionErrorOccurred(EasyMock.<String>anyObject())).andAnswer(new IAnswer<String>() {
            public String answer() throws Throwable {
                return actionResult;
            }
        }).anyTimes();
        EasyMock.expect(action.hasErrors()).andReturn(true).anyTimes();

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

        ActionContext contex = new ActionContext(new HashMap<String, Object>());
        ActionContext.setContext(contex);
        contex.setActionInvocation(invocation);
    }
