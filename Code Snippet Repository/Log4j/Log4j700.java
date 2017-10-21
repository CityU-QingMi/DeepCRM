    @PluginFactory
    public static WriterAppender createAppender(StringLayout layout, final Filter filter, final Writer target,
            final String name, final boolean follow, final boolean ignore) {
        if (name == null) {
            LOGGER.error("No name provided for WriterAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new WriterAppender(name, layout, filter, getManager(target, follow, layout), ignore);
    }
