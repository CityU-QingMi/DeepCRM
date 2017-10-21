    public static RegexReplacementConverter newInstance(final Configuration config, final String[] options) {
        if (options.length != 3) {
            LOGGER.error("Incorrect number of options on replace. Expected 3 received " + options.length);
            return null;
        }
        if (options[0] == null) {
            LOGGER.error("No pattern supplied on replace");
            return null;
        }
        if (options[1] == null) {
            LOGGER.error("No regular expression supplied on replace");
            return null;
        }
        if (options[2] == null) {
            LOGGER.error("No substitution supplied on replace");
            return null;
        }
        final Pattern p = Pattern.compile(options[1]);
        final PatternParser parser = PatternLayout.createPatternParser(config);
        final List<PatternFormatter> formatters = parser.parse(options[0]);
        return new RegexReplacementConverter(formatters, p, options[2]);
    }
