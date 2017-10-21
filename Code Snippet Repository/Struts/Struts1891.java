    public void testIgnoreMessageWithoutSession() throws Exception {
        MessageStoreInterceptor interceptor = new MessageStoreInterceptor();
        interceptor.setAllowRequestParameterSwitch(true);
        interceptor.setOperationMode(MessageStoreInterceptor.STORE_MODE);

        ActionSupport action = new ActionSupport();
        action.addActionError("some action error 1");
        action.addActionMessage("some action message 1");
        action.addFieldError("field2", "some field error 2");

        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setParameters(HttpParameters.create().build());

        HttpSession mockedSession = EasyMock.createControl().createMock(HttpSession.class);
        HttpServletRequest mockedRequest = EasyMock.createControl().createMock(HttpServletRequest.class);
        mockedRequest.getSession(false);
        EasyMock.expectLastCall().andReturn(mockedSession);
        EasyMock.expectLastCall().once();
        ServletActionContext.setRequest(mockedRequest);

        EasyMock.replay(mockedRequest);

        // Mock (ActionInvocation)
        ActionInvocation mockActionInvocation = EasyMock.createControl().createMock(ActionInvocation.class);
        mockActionInvocation.getInvocationContext();
        EasyMock.expectLastCall().andReturn(actionContext);
        EasyMock.expectLastCall().anyTimes();

        mockActionInvocation.invoke();
        EasyMock.expectLastCall().andReturn(Action.SUCCESS);

        mockActionInvocation.addPreResultListener(EasyMock.<PreResultListener>anyObject());
        EasyMock.expectLastCall();

        EasyMock.replay(mockActionInvocation);

        interceptor.init();
        interceptor.intercept(mockActionInvocation);
        interceptor.destroy();

        EasyMock.verify(mockActionInvocation);
    }
