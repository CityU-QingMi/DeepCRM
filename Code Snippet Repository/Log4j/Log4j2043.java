    @Test
    public void testInitialize_InputStream_File() throws Exception {
        final File file = new File("target/test-classes/log4j2-config.xml");
        final InputStream is = new FileInputStream(file);
        final ConfigurationSource source = new ConfigurationSource(is, file);
        ctx = Configurator.initialize(null, source);
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
