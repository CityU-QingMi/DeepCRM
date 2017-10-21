    @Test
    public void testLookup2() throws Exception {
        ContextAnchor.THREAD_CONTEXT.remove();
        final ServletContext servletContext = new MockServletContext();
        servletContext.setAttribute("TestAttr", "AttrValue");
        servletContext.setInitParameter("myapp.logdir", "target");
        servletContext.setAttribute("Name1", "Ben");
        servletContext.setInitParameter("Name2", "Jerry");
        servletContext.setInitParameter("log4jConfiguration", "WEB-INF/classes/log4j-webvar.xml");
        final Log4jWebLifeCycle initializer = WebLoggerContextUtils.getWebLifeCycle(servletContext);
        initializer.start();
        initializer.setLoggerContext();
        final LoggerContext ctx = ContextAnchor.THREAD_CONTEXT.get();
        assertNotNull("No LoggerContext", ctx);
        assertNotNull("No ServletContext", ctx.getExternalContext());
        final Configuration config = ctx.getConfiguration();
        assertNotNull("No Configuration", config);
        final Map<String, Appender> appenders = config.getAppenders();
        for (final Map.Entry<String, Appender> entry : appenders.entrySet()) {
            if (entry.getKey().equals("file")) {
                final FileAppender fa = (FileAppender) entry.getValue();
                assertEquals("target/myapp.log", fa.getFileName());
            }
        }
        initializer.stop();
        ContextAnchor.THREAD_CONTEXT.remove();
    }
