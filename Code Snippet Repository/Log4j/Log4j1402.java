    public static MaxLengthConverter newInstance(final Configuration config, final String[] options) {
        if (options.length != 2) {
            LOGGER.error("Incorrect number of options on maxLength: expected 2 received {}: {}", options.length,
                options);
            return null;
        }
        if (options[0] == null) {
            LOGGER.error("No pattern supplied on maxLength");
            return null;
        }
        if (options[1] == null) {
            LOGGER.error("No length supplied on maxLength");
            return null;
        }
        final PatternParser parser = PatternLayout.createPatternParser(config);
        final List<PatternFormatter> formatters = parser.parse(options[0]);
        return new MaxLengthConverter(formatters, AbstractAppender.parseInt(options[1], 100));
    }
