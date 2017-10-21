    @Test
    public void testInitializeFailsAfterDeinitialize() throws Exception {
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONTEXT_NAME))).willReturn(null);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONFIG_LOCATION))).willReturn(null);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.IS_LOG4J_CONTEXT_SELECTOR_NAMED))).willReturn(null);
        given(servletContext.getServletContextName()).willReturn("helloWorld04");
        given(servletContext.getResourcePaths("/WEB-INF/")).willReturn(null);
        given(servletContext.getClassLoader()).willReturn(getClass().getClassLoader());
        assertNull("The context should be null.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.start();

        then(servletContext).should().setAttribute(eq(Log4jWebSupport.CONTEXT_ATTRIBUTE), loggerContextCaptor.capture());
        assertNotNull("The context attribute should not be null.", loggerContextCaptor.getValue());

        this.initializerImpl.stop();

        then(servletContext).should().removeAttribute(eq(Log4jWebSupport.CONTEXT_ATTRIBUTE));

        expectedException.expect(IllegalStateException.class);
        this.initializerImpl.start();
    }
