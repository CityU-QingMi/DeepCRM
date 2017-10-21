    @Test
    public void testUpdatePatternWithFileAppender() {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        // @formatter:off
        final Appender appender = FileAppender.newBuilder()
            .withFileName("target/" + getClass().getName() + ".log")
            .withAppend(false)
            .withName("File")
            .withIgnoreExceptions(false)
            .withBufferedIo(false)
            .withBufferSize(4000)
            .setConfiguration(config)
            .build();
        // @formatter:on
        appender.start();
        config.addAppender(appender);
        ConfigurationTestUtils.updateLoggers(appender, config);
        LogManager.getLogger().error("FOO MSG");
    }
