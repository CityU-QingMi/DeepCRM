    public CustomConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
        super(loggerContext, source);

        setName(CONFIG_NAME);
        final Layout<? extends Serializable> layout = PatternLayout.newBuilder()
                .withPattern(DEFAULT_PATTERN)
                .withConfiguration(this)
                .build();
        final Appender appender = ConsoleAppender.createDefaultAppenderForLayout(layout);
        appender.start();
        addAppender(appender);
        final LoggerConfig root = getRootLogger();
        root.addAppender(appender, null, null);

        final String levelName = PropertiesUtil.getProperties().getStringProperty(DEFAULT_LEVEL);
        final Level level = levelName != null && Level.valueOf(levelName) != null ?
                Level.valueOf(levelName) : Level.ERROR;
        root.setLevel(level);
    }
