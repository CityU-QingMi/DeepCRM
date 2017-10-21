    public void testServletResponseAware() throws Exception {
        ServletResponseAware mock = (ServletResponseAware) createMock(ServletResponseAware.class);

        MockHttpServletResponse res = new MockHttpServletResponse();

        MockActionInvocation mai = createActionInvocation(mock);
        mai.getInvocationContext().put(StrutsStatics.HTTP_RESPONSE, res);

        mock.setServletResponse((HttpServletResponse) res);
        expectLastCall().times(1);

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
