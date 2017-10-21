    @Test
    public void testReconfiguration() throws Exception {
        final File file = new File("target/test-classes/log4j2-config.xml");
        assertTrue("setLastModified should have succeeded.", file.setLastModified(System.currentTimeMillis() - 120000));
        ctx = Configurator.initialize("Test1", "target/test-classes/log4j2-config.xml");
        final Logger logger = LogManager.getLogger("org.apache.test.TestConfigurator");
        Configuration config = ctx.getConfiguration();
        assertNotNull("No configuration", config);
        assertEquals("Incorrect Configuration.", CONFIG_NAME, config.getName());
        final Map<String, Appender> map = config.getAppenders();
        assertNotNull("Appenders map should not be null.", map);
        assertThat(map, hasSize(greaterThan(0)));
        assertThat("Wrong configuration", map, hasKey("List"));

        // Sleep and check
        Thread.sleep(50);
        if (!file.setLastModified(System.currentTimeMillis())) {
            Thread.sleep(500);
        }
        assertTrue("setLastModified should have succeeded.", file.setLastModified(System.currentTimeMillis()));
        TimeUnit.SECONDS.sleep(config.getWatchManager().getIntervalSeconds()+1);
        for (int i = 0; i < 17; ++i) {
            logger.debug("Test message " + i);
        }

        // Sleep and check
        Thread.sleep(50);
        if (is(theInstance(config)).matches(ctx.getConfiguration())) {
            Thread.sleep(500);
        }
        final Configuration newConfig = ctx.getConfiguration();
        assertThat("Configuration not reset", newConfig, is(not(theInstance(config))));
        Configurator.shutdown(ctx);
        config = ctx.getConfiguration();
        assertEquals("Unexpected Configuration.", NullConfiguration.NULL_NAME, config.getName());
    }
