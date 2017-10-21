    @Test
    public void testLevelInheritence() throws Exception {
        final Configuration config = context.getConfiguration();
        final LoggerConfig loggerConfig = config.getLoggerConfig("org.apache.logging.log4j.core.LoggerTest");
        assertNotNull(loggerConfig);
        assertEquals(loggerConfig.getName(), "org.apache.logging.log4j.core.LoggerTest");
        assertEquals(loggerConfig.getLevel(), Level.DEBUG);
        final Logger localLogger = context.getLogger("org.apache.logging.log4j.core.LoggerTest");
        assertTrue("Incorrect level - expected DEBUG, actual " + localLogger.getLevel(), localLogger.getLevel() == Level.DEBUG);
    }
