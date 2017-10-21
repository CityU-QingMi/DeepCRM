    public void testSessionAware() throws Exception {
        SessionAware mock = (SessionAware) createMock(SessionAware.class);

        MockActionInvocation mai = createActionInvocation(mock);

        Map<String, Object> session = new HashMap<String, Object>();
        mai.getInvocationContext().setSession(session);

        mock.setSession(session);
        expectLastCall().times(1);

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
