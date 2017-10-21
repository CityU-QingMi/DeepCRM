    public void testCreateSession() throws Exception {
        Mock httpServletRequestMock = new Mock(HttpServletRequest.class);

        ClearSessionInterceptor interceptor = new ClearSessionInterceptor();
        MockActionInvocation invocation = new MockActionInvocation();
        ActionContext context = new ActionContext(new HashMap());
        Map session = new HashMap();
        session.put("Test1", "Test1");
        session.put("Test2", "Test2");
        session.put("Test3", "Test3");
        context.setSession(session);
        invocation.setInvocationContext(context);
        interceptor.intercept(invocation);
        
        assertEquals(0, session.size());
    }
