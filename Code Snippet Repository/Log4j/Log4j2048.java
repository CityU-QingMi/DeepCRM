    @Test
    public void testFromClassPathWithClassLoaderPrefix() throws Exception {
        ctx = Configurator.initialize("Test1", "classloader:log4j2-config.xml");
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
        assertEquals("Incorrect Configuration.", NullConfiguration.NULL_NAME, config.getName());
    }
