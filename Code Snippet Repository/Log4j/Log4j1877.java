    @Test
    public void testAccessManagerWithStrings() throws IOException {
        try (final LoggerContext ctx = LoggerContext.getContext(false)) {
            final Configuration config = ctx.getConfiguration();
            final File file = File.createTempFile("RollingFileAppenderAccessTest", ".tmp");
            file.deleteOnExit();
            final RollingFileAppender appender = RollingFileAppender.createAppender(file.getCanonicalPath(),
                    "FilePattern", null, "Name", null, null, null, OnStartupTriggeringPolicy.createPolicy(1), null,
                    null, null, null, null, null, config);
            final RollingFileManager manager = appender.getManager();
            // Since the RolloverStrategy and TriggeringPolicy are immutable, we could also use generics to type their
            // access.
            Assert.assertNotNull(manager.getRolloverStrategy());
            Assert.assertNotNull(manager.getTriggeringPolicy());
        }
    }
