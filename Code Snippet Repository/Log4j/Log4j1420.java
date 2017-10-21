    @PluginFactory
    public static RegexReplacement createRegexReplacement(
            @PluginAttribute("regex") final Pattern regex,
            @PluginAttribute("replacement") final String replacement) {
        if (regex == null) {
            LOGGER.error("A regular expression is required for replacement");
            return null;
        }
        if (replacement == null) {
            LOGGER.error("A replacement string is required to perform replacement");
        }
        // FIXME: should we use Matcher.quoteReplacement() here?
        return new RegexReplacement(regex, replacement);
    }
