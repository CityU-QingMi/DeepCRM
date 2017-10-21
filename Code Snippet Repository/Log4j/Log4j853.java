    private RoutingAppender(final String name, final Filter filter, final boolean ignoreExceptions, final Routes routes,
            final RewritePolicy rewritePolicy, final Configuration configuration, final PurgePolicy purgePolicy,
            final AbstractScript defaultRouteScript) {
        super(name, filter, null, ignoreExceptions);
        this.routes = routes;
        this.configuration = configuration;
        this.rewritePolicy = rewritePolicy;
        this.purgePolicy = purgePolicy;
        if (this.purgePolicy != null) {
            this.purgePolicy.initialize(this);
        }
        this.defaultRouteScript = defaultRouteScript;
        Route defRoute = null;
        for (final Route route : routes.getRoutes()) {
            if (route.getKey() == null) {
                if (defRoute == null) {
                    defRoute = route;
                } else {
                    error("Multiple default routes. Route " + route.toString() + " will be ignored");
                }
            }
        }
        defaultRoute = defRoute;
    }
