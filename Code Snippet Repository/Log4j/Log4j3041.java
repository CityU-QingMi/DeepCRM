    @Before
    public void setUp() {
        given(servletContext.getAttribute(Log4jWebSupport.SUPPORT_ATTRIBUTE)).willReturn(null);

        final Log4jWebLifeCycle initializer = WebLoggerContextUtils.getWebLifeCycle(this.servletContext);

        then(servletContext).should().setAttribute(eq(Log4jWebSupport.SUPPORT_ATTRIBUTE), initializerCaptor.capture());
        assertNotNull("The initializer should not be null.", initializer);
        assertSame("The capture is not correct.", initializer, initializerCaptor.getValue());
        assertTrue("The initializer is not correct.", initializer instanceof Log4jWebInitializerImpl);

        this.initializerImpl = (Log4jWebInitializerImpl) initializer;
    }
