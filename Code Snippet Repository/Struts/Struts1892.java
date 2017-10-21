    public void testRequestOperationMode1() throws Exception {

        Map paramMap = new LinkedHashMap();
        paramMap.put("operationMode", new String[] { MessageStoreInterceptor.RETRIEVE_MODE });

        ActionContext actionContext = new ActionContext(new HashMap());
        actionContext.setParameters(HttpParameters.create(paramMap).build());

        ActionInvocation mockActionInvocation = EasyMock.createControl().createMock(ActionInvocation.class);
        mockActionInvocation.getInvocationContext();
        EasyMock.expectLastCall().andReturn(actionContext);
        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(mockActionInvocation);

        MessageStoreInterceptor interceptor = new MessageStoreInterceptor();
        String operationMode = interceptor.getRequestOperationMode(mockActionInvocation);

        assertEquals(operationMode, MessageStoreInterceptor.RETRIEVE_MODE);

        EasyMock.verify(mockActionInvocation);
    }
