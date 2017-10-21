    @Test
    public void testUpdate() {
        final KeyValuePair[] rewrite = new KeyValuePair[] {
                new KeyValuePair("INFO", "DEBUG"),
                new KeyValuePair("WARN", "INFO") };
        final String loggerNameRewrite = "com.foo.bar";
        LogEvent logEvent = Log4jLogEvent.newBuilder().setLoggerName(loggerNameRewrite)
                .setLoggerFqcn("LoggerNameLevelRewritePolicyTest.testUpdate()").setLevel(Level.INFO)
                .setMessage(new SimpleMessage("Test")).setThrown(new RuntimeException("test")).setThreadName("none")
                .setTimeMillis(1).build();
        final LoggerNameLevelRewritePolicy updatePolicy = LoggerNameLevelRewritePolicy.createPolicy(loggerNameRewrite,
                rewrite);
        LogEvent rewritten = updatePolicy.rewrite(logEvent);
        Assert.assertEquals(Level.DEBUG, rewritten.getLevel());
        logEvent = Log4jLogEvent.newBuilder().setLoggerName(loggerNameRewrite)
                .setLoggerFqcn("LoggerNameLevelRewritePolicyTest.testUpdate()").setLevel(Level.WARN)
                .setMessage(new SimpleMessage("Test")).setThrown(new RuntimeException("test")).setThreadName("none")
                .setTimeMillis(1).build();
        rewritten = updatePolicy.rewrite(logEvent);
        Assert.assertEquals(Level.INFO, rewritten.getLevel());
        final String loggerNameReadOnly = "com.nochange";
        logEvent = Log4jLogEvent.newBuilder().setLoggerName(loggerNameReadOnly)
                .setLoggerFqcn("LoggerNameLevelRewritePolicyTest.testUpdate()").setLevel(Level.INFO)
                .setMessage(new SimpleMessage("Test")).setThrown(new RuntimeException("test")).setThreadName("none")
                .setTimeMillis(1).build();
        rewritten = updatePolicy.rewrite(logEvent);
        Assert.assertEquals(Level.INFO, rewritten.getLevel());
        logEvent = Log4jLogEvent.newBuilder().setLoggerName(loggerNameReadOnly)
                .setLoggerFqcn("LoggerNameLevelRewritePolicyTest.testUpdate()").setLevel(Level.WARN)
                .setMessage(new SimpleMessage("Test")).setThrown(new RuntimeException("test")).setThreadName("none")
                .setTimeMillis(1).build();
        rewritten = updatePolicy.rewrite(logEvent);
        Assert.assertEquals(Level.WARN, rewritten.getLevel());
    }
