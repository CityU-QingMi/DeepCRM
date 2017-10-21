    @PluginFactory
    public static HangingAppender createAppender(
            @PluginAttribute("name")
            @Required(message = "No name provided for HangingAppender")
            final String name,
            @PluginAttribute("delay") final long delay,
            @PluginAttribute("startupDelay") final long startupDelay,
            @PluginAttribute("shutdownDelay") final long shutdownDelay,
            @PluginElement("Layout") final Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter) {
        return new HangingAppender(name, delay, startupDelay, shutdownDelay);
    }
