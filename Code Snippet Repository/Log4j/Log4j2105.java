    @Test
    public void testConfig() {
        try (final LoggerContext ctx = Configurator.initialize("Test1",
                "target/test-classes/log4j2-dynamicfilter.xml")) {
            final Configuration config = ctx.getConfiguration();
            final Filter filter = config.getFilter();
            assertNotNull("No DynamicThresholdFilter", filter);
            assertTrue("Not a DynamicThresholdFilter", filter instanceof DynamicThresholdFilter);
            final DynamicThresholdFilter dynamic = (DynamicThresholdFilter) filter;
            final String key = dynamic.getKey();
            assertNotNull("Key is null", key);
            assertEquals("Incorrect key value", "loginId", key);
            final Map<String, Level> map = dynamic.getLevelMap();
            assertNotNull("Map is null", map);
            assertEquals("Incorrect number of map elements", 1, map.size());
        }
    }
