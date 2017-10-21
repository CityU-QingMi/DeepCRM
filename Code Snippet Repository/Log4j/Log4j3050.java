    @Test
    public void testAppender() throws Exception {
        ContextAnchor.THREAD_CONTEXT.remove();
        final ServletContext servletContext = new MockServletContext();
        servletContext.setAttribute("TestAttr", "AttrValue");
        servletContext.setInitParameter("TestParam", "ParamValue");
        servletContext.setAttribute("Name1", "Ben");
        servletContext.setInitParameter("Name2", "Jerry");
        servletContext.setInitParameter(Log4jWebSupport.LOG4J_CONFIG_LOCATION, CONFIG);
        final Log4jWebLifeCycle initializer = WebLoggerContextUtils.getWebLifeCycle(servletContext);
        try {
            initializer.start();
            initializer.setLoggerContext();
            final LoggerContext ctx = ContextAnchor.THREAD_CONTEXT.get();
            assertNotNull("No LoggerContext", ctx);
            assertNotNull("No ServletContext", ctx.getExternalContext());
            final Configuration configuration = ctx.getConfiguration();
            assertNotNull("No configuration", configuration);
            final Appender appender = configuration.getAppender("Servlet");
            assertNotNull("No ServletAppender", appender);
            final Logger logger = LogManager.getLogger("Test");
            logger.info("This is a test");
            logger.error("This is a test 2", new IllegalStateException().fillInStackTrace());
        } catch (final IllegalStateException e) {
            fail("Failed to initialize Log4j properly." + e.getMessage());
        } finally {
            initializer.stop();
            ContextAnchor.THREAD_CONTEXT.remove();
        }
    }
