    @Deprecated
    public static Routes createRoutes(
            final String pattern,
            final Route... routes) {
        if (routes == null || routes.length == 0) {
            LOGGER.error("No routes configured");
            return null;
        }
        return new Routes(null, null, pattern, routes);
    }
