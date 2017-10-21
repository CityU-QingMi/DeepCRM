    @PluginFactory
    public static MarkerFilter createFilter(
            @PluginAttribute("marker") final String marker,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch) {

        if (marker == null) {
            LOGGER.error("A marker must be provided for MarkerFilter");
            return null;
        }
        return new MarkerFilter(marker, match, mismatch);
    }
