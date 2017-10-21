    public void testHttpParametersAware() throws Exception {
        HttpParametersAware mock = createMock(HttpParametersAware.class);

        MockActionInvocation mai = createActionInvocation(mock);

        HttpParameters param = HttpParameters.create().build();
        mai.getInvocationContext().setParameters(param);

        mock.setParameters(param);
        expectLastCall().times(1);

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
