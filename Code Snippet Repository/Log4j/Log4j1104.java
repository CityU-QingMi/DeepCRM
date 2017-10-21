    @PluginFactory
    public static RegexFilter createFilter(
            //@formatter:off
            @PluginAttribute("regex") final String regex,
            @PluginElement("PatternFlags") final String[] patternFlags,
            @PluginAttribute("useRawMsg") final Boolean useRawMsg,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch)
            //@formatter:on
            throws IllegalArgumentException, IllegalAccessException {
        if (regex == null) {
            LOGGER.error("A regular expression must be provided for RegexFilter");
            return null;
        }
        return new RegexFilter(useRawMsg, Pattern.compile(regex, toPatternFlags(patternFlags)), match, mismatch);
    }
