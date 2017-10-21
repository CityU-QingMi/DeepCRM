    @Test
    public void testOnStartupFailedDueToInitializerFailure() throws Exception {
        final FilterRegistration.Dynamic registration = mock(FilterRegistration.Dynamic.class);
        final IllegalStateException exception = new IllegalStateException(Strings.EMPTY);
        given(servletContext.getMajorVersion()).willReturn(3);
        given(servletContext.getEffectiveMajorVersion()).willReturn(3);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.IS_LOG4J_AUTO_INITIALIZATION_DISABLED))).willReturn(
            "balderdash");
        given(servletContext.addFilter(eq("log4jServletFilter"), filterCaptor.capture())).willReturn(registration);
        given(servletContext.getAttribute(Log4jWebSupport.SUPPORT_ATTRIBUTE)).willReturn(initializer);
        willThrow(exception).given(initializer).start();

        try {
            this.containerInitializer.onStartup(null, this.servletContext);
            fail("Expected the exception thrown by the initializer; got no exception.");
        } catch (final IllegalStateException e) {
            assertSame("The exception is not correct.", exception, e);
        }

        then(initializer).should().start();
        assertNotNull("The filter should not be null.", filterCaptor.getValue());
        assertSame("The filter is not correct.", Log4jServletFilter.class, filterCaptor.getValue());
    }
