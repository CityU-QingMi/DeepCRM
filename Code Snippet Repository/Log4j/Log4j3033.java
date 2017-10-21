    @Test
    public void testOnStartupWithServletVersion3_xEffectiveVersion3_x() throws Exception {
        final FilterRegistration.Dynamic registration = mock(FilterRegistration.Dynamic.class);
        given(servletContext.getMajorVersion()).willReturn(3);
        given(servletContext.getEffectiveMajorVersion()).willReturn(3);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.IS_LOG4J_AUTO_INITIALIZATION_DISABLED))).willReturn(
            null);
        given(servletContext.addFilter(eq("log4jServletFilter"), filterCaptor.capture())).willReturn(registration);
        given(servletContext.getAttribute(Log4jWebSupport.SUPPORT_ATTRIBUTE)).willReturn(initializer);

        containerInitializer.onStartup(null, servletContext);

        then(initializer).should().start();
        then(initializer).should().setLoggerContext();
        then(servletContext).should().addListener(listenerCaptor.capture());
        then(registration).should().setAsyncSupported(eq(true));
        then(registration).should().addMappingForUrlPatterns(eq(EnumSet.allOf(DispatcherType.class)), eq(false), eq("/*"));

        assertNotNull("The listener should not be null.", listenerCaptor.getValue());
        assertSame("The listener is not correct.", Log4jServletContextListener.class,
            listenerCaptor.getValue().getClass());

        assertNotNull("The filter should not be null.", filterCaptor.getValue());
        assertSame("The filter is not correct.", Log4jServletFilter.class, filterCaptor.getValue());
    }
