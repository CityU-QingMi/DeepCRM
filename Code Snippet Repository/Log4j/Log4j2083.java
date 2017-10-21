    @Test
    public void testDefaultStatus() {
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY, CONFIG1);
        System.setProperty(Constants.LOG4J_DEFAULT_STATUS_LEVEL, "WARN");
        try {
            final LoggerContext ctx = LoggerContext.getContext();
            ctx.reconfigure();
            final Configuration config = ctx.getConfiguration();
            assertTrue("Configuration is not an XmlConfiguration", config instanceof XmlConfiguration);
        } finally {
            System.clearProperty(Constants.LOG4J_DEFAULT_STATUS_LEVEL);
        }
    }
