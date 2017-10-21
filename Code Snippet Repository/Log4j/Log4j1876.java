    @Test
    public void testAccessManagerWithBuilder() throws IOException {
        try (final LoggerContext ctx = LoggerContext.getContext(false)) {
            final Configuration config = ctx.getConfiguration();
            final File file = File.createTempFile("RollingFileAppenderAccessTest", ".tmp");
            file.deleteOnExit();
            // @formatter:off
            final RollingFileAppender appender = RollingFileAppender.newBuilder()
                    .withFileName(file.getCanonicalPath())
                    .withFilePattern("FilePattern")
                    .withName("Name")
                    .withPolicy(OnStartupTriggeringPolicy.createPolicy(1))
                    .setConfiguration(config)
                    .build();
            // @formatter:on
            final RollingFileManager manager = appender.getManager();
            // Since the RolloverStrategy and TriggeringPolicy are immutable, we could also use generics to type their
            // access.
            Assert.assertNotNull(manager.getRolloverStrategy());
            Assert.assertNotNull(manager.getTriggeringPolicy());
        }
    }
