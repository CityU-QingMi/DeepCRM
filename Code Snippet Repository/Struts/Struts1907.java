    public void testServletRequestAware() throws Exception {
        ServletRequestAware mock = (ServletRequestAware) createMock(ServletRequestAware.class);

        MockHttpServletRequest req = new MockHttpServletRequest();

        MockActionInvocation mai = createActionInvocation(mock);
        mai.getInvocationContext().put(StrutsStatics.HTTP_REQUEST, req);

        mock.setServletRequest((HttpServletRequest) req);
        expectLastCall();

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
