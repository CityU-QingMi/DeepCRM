    @PluginFactory
    public static LevelRangeFilter createFilter(
            // @formatter:off
            @PluginAttribute("minLevel") final Level minLevel,
            @PluginAttribute("maxLevel") final Level maxLevel,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch) {
            // @formatter:on
        final Level actualMinLevel = minLevel == null ? Level.ERROR : minLevel;
        final Level actualMaxLevel = maxLevel == null ? Level.ERROR : maxLevel;
        final Result onMatch = match == null ? Result.NEUTRAL : match;
        final Result onMismatch = mismatch == null ? Result.DENY : mismatch;
        return new LevelRangeFilter(actualMinLevel, actualMaxLevel, onMatch, onMismatch);
    }
