    @Test
    public void testLevelNamesBad() {
        String colorName = "red";
        final String[] options = { "%-5level: %msg", PatternParser.NO_CONSOLE_NO_ANSI + "=false, "
                + PatternParser.DISABLE_ANSI + "=false, " + "BAD_LEVEL_A=" + colorName + ", BAD_LEVEL_B=" + colorName };
        final HighlightConverter converter = HighlightConverter.newInstance(null, options);
        Assert.assertNotNull(converter);
        Assert.assertNotNull(converter.getLevelStyle(Level.TRACE));
        Assert.assertNotNull(converter.getLevelStyle(Level.DEBUG));
    }
