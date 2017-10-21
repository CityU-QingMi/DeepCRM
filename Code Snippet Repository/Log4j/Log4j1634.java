    @Test
    public void testReconfiguration() throws Exception {
        final Configuration cfg = context.getConfiguration();
        assertNotNull("No configuration", cfg);
        assertTrue("Not set to default configuration", cfg instanceof DefaultConfiguration);
        final File file = new File(CONFIG);
        final LoggerContext loggerContext = LoggerContext.getContext(null, false, file.toURI());
        assertNotNull("No Logger Context", loggerContext);
        final Configuration newConfig = loggerContext.getConfiguration();
        assertTrue("Configuration not reset", cfg != newConfig);
        assertTrue("Reconfiguration failed", newConfig instanceof XmlConfiguration);
        context = LoggerContext.getContext(false);
        final Configuration sameConfig = context.getConfiguration();
        assertTrue("Configuration should not have been reset", newConfig == sameConfig);
    }
