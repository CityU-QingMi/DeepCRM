    public static UuidPatternConverter newInstance(final String[] options) {
        if (options.length == 0) {
            return new UuidPatternConverter(false);
        }

        if (options.length > 1 || (!options[0].equalsIgnoreCase("RANDOM") && !options[0].equalsIgnoreCase("Time"))) {
            LOGGER.error("UUID Pattern Converter only accepts a single option with the value \"RANDOM\" or \"TIME\"");
        }
        return new UuidPatternConverter(options[0].equalsIgnoreCase("RANDOM"));
    }
