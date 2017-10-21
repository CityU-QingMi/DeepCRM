    @Test
    public void testFromClassPathProperty() throws Exception {
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "classpath:log4j2-config.xml");
        ctx = Configurator.initialize("Test1", null);
        LogManager.getLogger("org.apache.test.TestConfigurator");
        Configuration config = ctx.getConfiguration();
        assertNotNull("No configuration", config);
        assertEquals("Incorrect Configuration.", CONFIG_NAME, config.getName());
        final Map<String, Appender> map = config.getAppenders();
        assertNotNull("Appenders map should not be null.", map);
        assertThat(map, hasSize(greaterThan(0)));
        assertThat("Wrong configuration", map, hasKey("List"));
        Configurator.shutdown(ctx);
        config = ctx.getConfiguration();
        assertEquals("Unexpected Configuration.", NullConfiguration.NULL_NAME, config.getName());
    }
