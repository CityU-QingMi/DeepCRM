    @Test
    public void testNanoPatternLongChangesNanoClockFactoryMode() {
        final Configuration config = new NullConfiguration();
        assertTrue(config.getNanoClock() instanceof DummyNanoClock);

        final PatternParser pp = new PatternParser(config, KEY, null);
        assertTrue(config.getNanoClock() instanceof DummyNanoClock);

        pp.parse("%m");
        assertTrue(config.getNanoClock() instanceof DummyNanoClock);

        pp.parse("%N");
        assertTrue(config.getNanoClock() instanceof SystemNanoClock);
    }
