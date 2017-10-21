    @Test
    public void narrow() throws Exception {
        final Logger logger = LogManager.getLogger(OutputStreamManagerTest.class);
        logger.info("test");
        final List<StatusData> statusData = StatusLogger.getLogger().getStatusData();
        StatusData data = statusData.get(0);
        if (data.getMessage().getFormattedMessage().contains("WindowsAnsiOutputStream")) {
            data = statusData.get(1);
        }
        assertEquals(Level.ERROR, data.getLevel());
        assertEquals("Could not create plugin of type class org.apache.logging.log4j.core.appender.RollingRandomAccessFileAppender for element RollingRandomAccessFile",
                data.getMessage().getFormattedMessage());
        assertEquals("org.apache.logging.log4j.core.config.ConfigurationException: Configuration has multiple incompatible Appenders pointing to the same resource 'target/multiIncompatibleAppender.log'",
                data.getThrowable().toString());
    }
