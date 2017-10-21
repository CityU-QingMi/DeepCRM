    @PluginFactory
    public static ThresholdFilter createFilter(
            @PluginAttribute("level") final Level level,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch) {
        final Level actualLevel = level == null ? Level.ERROR : level;
        final Result onMatch = match == null ? Result.NEUTRAL : match;
        final Result onMismatch = mismatch == null ? Result.DENY : mismatch;
        return new ThresholdFilter(actualLevel, onMatch, onMismatch);
    }
