    @PluginFactory
    public static AppenderRef createAppenderRef(
            @PluginAttribute("ref") final String ref,
            @PluginAttribute("level") final Level level,
            @PluginElement("Filter") final Filter filter) {

        if (ref == null) {
            LOGGER.error("Appender references must contain a reference");
            return null;
        }
        return new AppenderRef(ref, level, filter);
    }
