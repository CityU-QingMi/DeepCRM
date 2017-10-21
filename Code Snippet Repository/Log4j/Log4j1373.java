    private static List<PatternFormatter> toPatternFormatterList(final Configuration config, final String[] options) {
        if (options.length == 0 || options[0] == null) {
            LOGGER.error("No pattern supplied on style for config=" + config);
            return null;
        }
        final PatternParser parser = PatternLayout.createPatternParser(config);
        if (parser == null) {
            LOGGER.error("No PatternParser created for config=" + config + ", options=" + Arrays.toString(options));
            return null;
        }
        return parser.parse(options[0]);
    }
