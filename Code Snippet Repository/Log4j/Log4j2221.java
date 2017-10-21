    private void testMdcPattern(final String patternStr, final String expectedStr, final boolean useThreadContext)
            throws Exception {
        final PatternLayout layout = PatternLayout.newBuilder().withPattern(patternStr)
                .withConfiguration(ctx.getConfiguration()).build();
        if (useThreadContext) {
            ThreadContext.put("key1", "value1");
            ThreadContext.put("key2", "value2");
        }
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello")).build();
        assertToByteArray(expectedStr, layout, event);
        assertEncode(expectedStr, layout, event);
    }
