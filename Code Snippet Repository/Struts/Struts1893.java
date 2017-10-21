    public void testRequestOperationMode2() throws Exception {

        Map paramMap = new LinkedHashMap();
        paramMap.put("operationMode", new String[] { MessageStoreInterceptor.STORE_MODE });

        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setParameters(HttpParameters.create(paramMap).build());

        ActionInvocation mockActionInvocation = EasyMock.createControl().createMock(ActionInvocation.class);
        mockActionInvocation.getInvocationContext();
        EasyMock.expectLastCall().andReturn(actionContext);
        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(mockActionInvocation);

        MessageStoreInterceptor interceptor = new MessageStoreInterceptor();
        String operationMode = interceptor.getRequestOperationMode(mockActionInvocation);

        assertEquals(operationMode, MessageStoreInterceptor.STORE_MODE);

        EasyMock.verify(mockActionInvocation);
    }
