    public void testSessionWasInvalidated() throws Exception {
        // given
        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.put(ActionContext.PARAMETERS, new LinkedHashMap());

        ActionInvocation mockActionInvocation = EasyMock.createControl().createMock(ActionInvocation.class);

        mockActionInvocation.getInvocationContext();
        EasyMock.expectLastCall().andReturn(actionContext);
        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(mockActionInvocation);

        HttpServletRequest mockedRequest = EasyMock.createControl().createMock(HttpServletRequest.class);
        mockedRequest.getSession(false);
        EasyMock.expectLastCall().andReturn(null);
        EasyMock.expectLastCall().once();
        ServletActionContext.setRequest(mockedRequest);

        EasyMock.replay(mockedRequest);

        HttpServletResponse mockedResponse = EasyMock.createControl().createMock(HttpServletResponse.class);
        mockedResponse.isCommitted();
        EasyMock.expectLastCall().andReturn(false);
        EasyMock.expectLastCall().once();
        ServletActionContext.setResponse(mockedResponse);

        EasyMock.replay(mockedResponse);

        // when
        MessageStoreInterceptor msi = new MessageStoreInterceptor();
        MessageStorePreResultListener listener = new MessageStorePreResultListener();
        listener.init(msi);
        listener.beforeResult(mockActionInvocation, Action.SUCCESS);

        // then
        EasyMock.verify(mockActionInvocation);
        EasyMock.verify(mockedRequest);
        EasyMock.verify(mockedResponse);
    }
