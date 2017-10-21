    public static StyleConverter newInstance(final Configuration config, final String[] options) {
        if (options == null) {
            return null;
        }
        if (options.length < 2) {
            LOGGER.error("Incorrect number of options on style. Expected at least 1, received " + options.length);
            return null;
        }
        if (options[0] == null) {
            LOGGER.error("No pattern supplied for style converter");
            return null;
        }
        if (options[1] == null) {
            LOGGER.error("No style attributes supplied for style converter");
            return null;
        }
        final PatternParser parser = PatternLayout.createPatternParser(config);
        final List<PatternFormatter> formatters = parser.parse(options[0]);
        final String style = AnsiEscape.createSequence(options[1].split(Patterns.COMMA_SEPARATOR));
        final boolean disableAnsi = Arrays.toString(options).contains(PatternParser.DISABLE_ANSI + "=true");
        final boolean noConsoleNoAnsi = Arrays.toString(options).contains(PatternParser.NO_CONSOLE_NO_ANSI + "=true");
        final boolean hideAnsi = disableAnsi || (noConsoleNoAnsi && System.console() == null);
        return new StyleConverter(formatters, style, hideAnsi);
    }
