    @PluginFactory
    public static Route createRoute(
            @PluginAttribute("ref") final String appenderRef,
            @PluginAttribute("key") final String key,
            @PluginNode final Node node) {
        if (node != null && node.hasChildren()) {
            if (appenderRef != null) {
                LOGGER.error("A route cannot be configured with an appender reference and an appender definition");
                return null;
            }
        } else {
            if (appenderRef == null) {
                LOGGER.error("A route must specify an appender reference or an appender definition");
                return null;
            }
        }
        return new Route(node, appenderRef, key);
    }
