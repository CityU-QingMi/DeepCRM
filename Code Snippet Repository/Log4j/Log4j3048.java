    @Test
    public void testInitializeUsingJndiSelector() throws Exception {
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONTEXT_NAME))).willReturn("helloWorld06");
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONFIG_LOCATION))).willReturn(null);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.IS_LOG4J_CONTEXT_SELECTOR_NAMED))).willReturn("true");
        given(servletContext.getResourcePaths("/WEB-INF/")).willReturn(null);
        assertNull("The context should be null.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.start();

        then(servletContext).should().setAttribute(eq(Log4jWebSupport.CONTEXT_ATTRIBUTE), loggerContextCaptor.capture());
        assertNull("The context attribute should be null.", loggerContextCaptor.getValue());

        assertNull("The context should still be null.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.setLoggerContext();

        assertNull("The context should still be null because no named selector.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.clearLoggerContext();

        assertNull("The context should be null again.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.stop();

        assertNull("The context should again still be null.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.setLoggerContext();

        assertNull("The context should finally still be null.", ContextAnchor.THREAD_CONTEXT.get());
    }
