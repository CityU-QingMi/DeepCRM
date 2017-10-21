    public void testParameterAware() throws Exception {
        ParameterAware mock = createMock(ParameterAware.class);

        MockActionInvocation mai = createActionInvocation(mock);

        HttpParameters param = HttpParameters.create().build();
        mai.getInvocationContext().setParameters(param);

        param.applyParameters(mock);
        expectLastCall().times(1);

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
