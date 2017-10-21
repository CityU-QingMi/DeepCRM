    public void testApplicationAware() throws Exception {
        ApplicationAware mock = (ApplicationAware) createMock(ApplicationAware.class);

        MockActionInvocation mai = createActionInvocation(mock);

        Map<String, Object> app = new HashMap<String, Object>();
        mai.getInvocationContext().setApplication(app);

        mock.setApplication(app);
        expectLastCall().times(1);

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
