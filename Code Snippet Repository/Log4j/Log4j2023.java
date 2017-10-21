    @Test
    public void testConfig() {
        // don't bother using "error" since that's the default; try another level
        final LoggerContext ctx = this.init.getLoggerContext();
        ctx.reconfigure();
        final Configuration config = ctx.getConfiguration();
        assertThat(config, instanceOf(XmlConfiguration.class));
        for (final StatusListener listener : StatusLogger.getLogger().getListeners()) {
            if (listener instanceof StatusConsoleListener) {
                assertSame(listener.getStatusLevel(), Level.INFO);
                break;
            }
        }
        final Layout<? extends Serializable> layout = PatternLayout.newBuilder()
            .withPattern(PatternLayout.SIMPLE_CONVERSION_PATTERN)
            .withConfiguration(config)
            .build();
        // @formatter:off
        final FileAppender appender = FileAppender.newBuilder()
            .withFileName(LOG_FILE)
            .withAppend(false)
            .withName("File")
            .withIgnoreExceptions(false)
            .withBufferSize(4000)
            .withBufferedIo(false)
            .withLayout(layout)
            .build();
        // @formatter:on
        appender.start();
        config.addAppender(appender);
        final AppenderRef ref = AppenderRef.createAppenderRef("File", null, null);
        final AppenderRef[] refs = new AppenderRef[] {ref};

        final LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, "org.apache.logging.log4j",
            "true", refs, null, config, null );
        loggerConfig.addAppender(appender, null, null);
        config.addLogger("org.apache.logging.log4j", loggerConfig);
        ctx.updateLoggers();
        final Logger logger = ctx.getLogger(CustomConfigurationTest.class.getName());
        logger.info("This is a test");
        final File file = new File(LOG_FILE);
        assertThat(file, exists());
        assertThat(file, hasLength(greaterThan(0L)));
    }
