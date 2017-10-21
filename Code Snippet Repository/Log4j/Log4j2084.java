    @Test
    public void testWithConfigProp() {
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY, CONFIG);
        System.setProperty("log4j.level", "warn");
        try {
            final LoggerContext ctx = LoggerContext.getContext();
            ctx.reconfigure();
            final Configuration config = ctx.getConfiguration();
            assertTrue("Configuration is not an XmlConfiguration", config instanceof XmlConfiguration);
        } finally {
            System.clearProperty("log4j.level");

        }
    }
