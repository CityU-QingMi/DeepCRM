    @Test
    public void testLevelNamesGood() {
        String colorName = "red";
        final String[] options = { "%-5level: %msg", PatternParser.NO_CONSOLE_NO_ANSI + "=false, "
                + PatternParser.DISABLE_ANSI + "=false, " + "DEBUG=" + colorName + ", TRACE=" + colorName };
        final HighlightConverter converter = HighlightConverter.newInstance(null, options);
        Assert.assertNotNull(converter);
        Assert.assertEquals(AnsiEscape.createSequence(colorName), converter.getLevelStyle(Level.TRACE));
        Assert.assertEquals(AnsiEscape.createSequence(colorName), converter.getLevelStyle(Level.DEBUG));
    }
