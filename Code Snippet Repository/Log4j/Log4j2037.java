    @Test
    public void testNoFilters() throws Exception {
        ctx = Configurator.initialize("Test1", "bad/log4j-nofilter.xml");
        LogManager.getLogger("org.apache.test.TestConfigurator");
        final Configuration config = ctx.getConfiguration();
        assertNotNull("No configuration", config);
        assertEquals("Unexpected Configuration", "XMLConfigTest", config.getName());
        final LoggerConfig lcfg = config.getLoggerConfig("org.apache.logging.log4j.test1");
        assertNotNull("No Logger", lcfg);
        final Filter filter = lcfg.getFilter();
        assertNotNull("No Filter", filter);
        assertThat(filter, instanceOf(CompositeFilter.class));
        assertTrue("Unexpected filters", ((CompositeFilter) filter).isEmpty());
    }
