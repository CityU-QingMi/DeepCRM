    public void testServletContextAware() throws Exception {
        ServletContextAware mock = (ServletContextAware) createMock(ServletContextAware.class);

        MockActionInvocation mai = createActionInvocation(mock);

        MockServletContext ctx = new MockServletContext();
        mai.getInvocationContext().put(StrutsStatics.SERVLET_CONTEXT, ctx);

        mock.setServletContext((ServletContext) ctx);
        expectLastCall().times(1);

        replay(mock);
        interceptor.intercept(mai);
        verify(mock);
    }
