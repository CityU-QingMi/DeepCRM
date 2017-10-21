    public void setUp(final String configFileName) throws SQLException {
        this.connection = this.setUpConnection();

        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY,
                "org/apache/logging/log4j/core/appender/db/jpa/" + configFileName);
        final LoggerContext context = LoggerContext.getContext(false);
        if (context.getConfiguration() instanceof DefaultConfiguration) {
            context.reconfigure();
        }
        StatusLogger.getLogger().reset();
    }
