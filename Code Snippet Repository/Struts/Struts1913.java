    public void testPrincipalAware() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setUserPrincipal(null);
        req.setRemoteUser("Santa");
        PrincipalAware mock = (PrincipalAware) createMock(PrincipalAware.class);

        MockActionInvocation mai = createActionInvocation(mock);
        mai.getInvocationContext().put(StrutsStatics.HTTP_REQUEST, req);
        
        MockServletContext ctx = new MockServletContext();
        mai.getInvocationContext().put(StrutsStatics.SERVLET_CONTEXT, ctx);

        mock.setPrincipalProxy(anyObject(ServletPrincipalProxy.class)); // less strick match is needed for this unit test to be conducted using mocks
        expectLastCall().times(1);

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
