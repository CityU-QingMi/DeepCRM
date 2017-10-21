    @Setup
    public void setup() throws Exception {
        connectionHSQLDB = getConnectionHSQLDB();
        connectionH2 = getConnectionH2();

        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "log4j2-jpa-appender.xml");
        final LoggerContext context = LoggerContext.getContext(false);
        if (context.getConfiguration() instanceof DefaultConfiguration) {
            context.reconfigure();
        }
        StatusLogger.getLogger().reset();
        loggerH2 = LogManager.getLogger("H2Logger");
        loggerHSQLDB = LogManager.getLogger("HSQLDBLogger");
    }
