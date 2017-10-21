    @Test
    public void testNanoPatternShortChangesConfigurationNanoClock() {
        final Configuration config = new NullConfiguration();
        assertTrue(config.getNanoClock() instanceof DummyNanoClock);

        final PatternParser pp = new PatternParser(config, KEY, null);
        assertTrue(config.getNanoClock() instanceof DummyNanoClock);

        pp.parse("%m");
        assertTrue(config.getNanoClock() instanceof DummyNanoClock);

        pp.parse("%nano"); // this changes the config clock
        assertTrue(config.getNanoClock() instanceof SystemNanoClock);
    }
