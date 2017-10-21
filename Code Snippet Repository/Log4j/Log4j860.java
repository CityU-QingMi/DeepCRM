    @Deprecated
    public static RoutingAppender createAppender(
            final String name,
            final String ignore,
            final Routes routes,
            final Configuration config,
            final RewritePolicy rewritePolicy,
            final PurgePolicy purgePolicy,
            final Filter filter) {

        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
        if (name == null) {
            LOGGER.error("No name provided for RoutingAppender");
            return null;
        }
        if (routes == null) {
            LOGGER.error("No routes defined for RoutingAppender");
            return null;
        }
        return new RoutingAppender(name, filter, ignoreExceptions, routes, rewritePolicy, config, purgePolicy, null);
    }
