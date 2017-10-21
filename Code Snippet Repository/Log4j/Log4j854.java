    @Override
    public void start() {
        if (defaultRouteScript != null) {
            if (configuration == null) {
                error("No Configuration defined for RoutingAppender; required for Script element.");
            } else {
                final ScriptManager scriptManager = configuration.getScriptManager();
                scriptManager.addScript(defaultRouteScript);
                final Bindings bindings = scriptManager.createBindings(defaultRouteScript);
                bindings.put(STATIC_VARIABLES_KEY, scriptStaticVariables);
                final Object object = scriptManager.execute(defaultRouteScript.getName(), bindings);
                final Route route = routes.getRoute(Objects.toString(object, null));
                if (route != null) {
                    defaultRoute = route;
                }
            }
        }
        // Register all the static routes.
        for (final Route route : routes.getRoutes()) {
            if (route.getAppenderRef() != null) {
                final Appender appender = configuration.getAppender(route.getAppenderRef());
                if (appender != null) {
                    final String key = route == defaultRoute ? DEFAULT_KEY : route.getKey();
                    appenders.put(key, new AppenderControl(appender, null, null));
                } else {
                    error("Appender " + route.getAppenderRef() + " cannot be located. Route ignored");
                }
            }
        }
        super.start();
    }
