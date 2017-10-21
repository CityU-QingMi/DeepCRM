    @Test
    public void testOnStartupCanceledDueToPreExistingFilter() throws Exception {
        given(servletContext.getMajorVersion()).willReturn(3);
        given(servletContext.getEffectiveMajorVersion()).willReturn(3);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.IS_LOG4J_AUTO_INITIALIZATION_DISABLED))).willReturn(
            "false");
        given(servletContext.addFilter(eq("log4jServletFilter"), filterCaptor.capture())).willReturn(null);

        this.containerInitializer.onStartup(null, this.servletContext);

        assertNotNull("The filter should not be null.", filterCaptor.getValue());
        assertSame("The filter is not correct.", Log4jServletFilter.class, filterCaptor.getValue());
    }
