    @AfterClass
    public static void afterClass() {
        System.clearProperty("AsyncLogger.RingBufferSize");
        System.clearProperty("AsyncLoggerConfig.RingBufferSize");
        System.clearProperty(Constants.LOG4J_CONTEXT_SELECTOR);
        System.clearProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY);
        System.clearProperty("log4j2.garbagefree.threadContextMap");
        System.clearProperty("log4j2.is.webapp");
        System.clearProperty("log4j2.threadContextMap");
    }
