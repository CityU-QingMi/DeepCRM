    public static HighlightConverter newInstance(final Configuration config, final String[] options) {
        if (options.length < 1) {
            LOGGER.error("Incorrect number of options on style. Expected at least 1, received " + options.length);
            return null;
        }
        if (options[0] == null) {
            LOGGER.error("No pattern supplied on style");
            return null;
        }
        final PatternParser parser = PatternLayout.createPatternParser(config);
        final List<PatternFormatter> formatters = parser.parse(options[0]);
        final boolean disableAnsi = Arrays.toString(options).contains(PatternParser.DISABLE_ANSI + "=true");
        final boolean noConsoleNoAnsi = Arrays.toString(options).contains(PatternParser.NO_CONSOLE_NO_ANSI + "=true");
        final boolean hideAnsi = disableAnsi || (noConsoleNoAnsi && System.console() == null);
        return new HighlightConverter(formatters, createLevelStyleMap(options), hideAnsi);
    }
