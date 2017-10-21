    @Test
    public void testLookup() throws Exception {
        ContextAnchor.THREAD_CONTEXT.remove();
        final ServletContext servletContext = new MockServletContext();
        servletContext.setAttribute("TestAttr", "AttrValue");
        servletContext.setInitParameter("TestParam", "ParamValue");
        servletContext.setAttribute("Name1", "Ben");
        servletContext.setInitParameter("Name2", "Jerry");
        final Log4jWebLifeCycle initializer = WebLoggerContextUtils.getWebLifeCycle(servletContext);
        try {
            initializer.start();
            initializer.setLoggerContext();
            final LoggerContext ctx = ContextAnchor.THREAD_CONTEXT.get();
            assertNotNull("No LoggerContext", ctx);
            assertNotNull("No ServletContext", ctx.getExternalContext());
            final Configuration config = ctx.getConfiguration();
            assertNotNull("No Configuration", config);
            final StrSubstitutor substitutor = config.getStrSubstitutor();
            assertNotNull("No Interpolator", substitutor);
            String value = substitutor.replace("${web:initParam.TestParam}");
            assertNotNull("No value for TestParam", value);
            assertEquals("Incorrect value for TestParam: " + value, "ParamValue", value);
            value = substitutor.replace("${web:attr.TestAttr}");
            assertNotNull("No value for TestAttr", value);
            assertEquals("Incorrect value for TestAttr: " + value, "AttrValue", value);
            value = substitutor.replace("${web:Name1}");
            assertNotNull("No value for Name1", value);
            assertEquals("Incorrect value for Name1: " + value, "Ben", value);
            value = substitutor.replace("${web:Name2}");
            assertNotNull("No value for Name2", value);
            assertEquals("Incorrect value for Name2: " + value, "Jerry", value);
        } catch (final IllegalStateException e) {
            fail("Failed to initialize Log4j properly." + e.getMessage());
        }
        initializer.stop();
        ContextAnchor.THREAD_CONTEXT.remove();
    }
