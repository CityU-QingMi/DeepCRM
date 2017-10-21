    public void testRequestOperationMode3() throws Exception {

        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setParameters(HttpParameters.create().build());

        ActionInvocation mockActionInvocation = EasyMock.createControl().createMock(ActionInvocation.class);
        mockActionInvocation.getInvocationContext();
        EasyMock.expectLastCall().andReturn(actionContext);
        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(mockActionInvocation);

        MessageStoreInterceptor interceptor = new MessageStoreInterceptor();
        String operationMode = interceptor.getRequestOperationMode(mockActionInvocation);

        assertEquals(operationMode, MessageStoreInterceptor.NONE);

        EasyMock.verify(mockActionInvocation);

    }
