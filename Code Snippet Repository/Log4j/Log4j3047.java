    @Test
    public void testInitializeUsingJndiSelectorFails() throws Exception {
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONTEXT_NAME))).willReturn(null);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONFIG_LOCATION))).willReturn(null);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.IS_LOG4J_CONTEXT_SELECTOR_NAMED))).willReturn("true");
        given(servletContext.getResourcePaths("/WEB-INF/")).willReturn(null);
        assertNull("The context should be null.", ContextAnchor.THREAD_CONTEXT.get());

        expectedException.expect(IllegalStateException.class);
        this.initializerImpl.start();
    }
