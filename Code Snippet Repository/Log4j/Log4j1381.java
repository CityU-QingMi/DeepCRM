    public static EncodingPatternConverter newInstance(final Configuration config, final String[] options) {
        if (options.length > 2 || options.length == 0) {
            LOGGER.error("Incorrect number of options on escape. Expected 1 or 2, but received {}",
                options.length);
            return null;
        }
        if (options[0] == null) {
            LOGGER.error("No pattern supplied on escape");
            return null;
        }
        final EscapeFormat escapeFormat = options.length < 2 ? EscapeFormat.HTML
            : EnglishEnums.valueOf(EscapeFormat.class, options[1], EscapeFormat.HTML);
        final PatternParser parser = PatternLayout.createPatternParser(config);
        final List<PatternFormatter> formatters = parser.parse(options[0]);
        return new EncodingPatternConverter(formatters, escapeFormat);
    }
