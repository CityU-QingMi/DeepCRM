    @Test
    public void testBadStatus() throws Exception {
        ctx = Configurator.initialize("Test1", "bad/log4j-status.xml");
        LogManager.getLogger("org.apache.test.TestConfigurator");
        final Configuration config = ctx.getConfiguration();
        assertNotNull("No configuration", config);
        assertEquals("Unexpected Configuration", "XMLConfigTest", config.getName());
        final LoggerConfig root = config.getLoggerConfig("");
        assertNotNull("No Root Logger", root);
        assertTrue("Expected error level, was " + root.getLevel(), Level.ERROR == root.getLevel());
    }
