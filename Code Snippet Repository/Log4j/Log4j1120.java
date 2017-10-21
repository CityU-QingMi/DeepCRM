    @PluginFactory
    public static TimeFilter createFilter(
            @PluginAttribute("start") final String start,
            @PluginAttribute("end") final String end,
            @PluginAttribute("timezone") final String tz,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch) {
        final long s = parseTimestamp(start, 0);
        final long e = parseTimestamp(end, Long.MAX_VALUE);
        final TimeZone timezone = tz == null ? TimeZone.getDefault() : TimeZone.getTimeZone(tz);
        final Result onMatch = match == null ? Result.NEUTRAL : match;
        final Result onMismatch = mismatch == null ? Result.DENY : mismatch;
        return new TimeFilter(s, e, timezone, onMatch, onMismatch);
    }
