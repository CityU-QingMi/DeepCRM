    public ActionProxy createActionProxy(String namespace, String actionName, String methodName,
                                         Map<String, Object> extraContext, boolean executeResult, boolean cleanupContext) {

        String uri = namespace + (namespace.endsWith("/") ? actionName : "/" + actionName);
        for (int lastIndex = uri.lastIndexOf('/'); lastIndex > (-1); lastIndex = uri.lastIndexOf('/', lastIndex - 1)) {
            String key = uri.substring(0, lastIndex);
            ActionProxyFactory actionProxyFactory = actionProxyFactories.get(key);
            if (actionProxyFactory != null) {
                LOG.debug("Using ActionProxyFactory [{}] for prefix [{}]", actionProxyFactory, key);
                return actionProxyFactory.createActionProxy(namespace, actionName, methodName, extraContext, executeResult, cleanupContext);
            } else {
                LOG.debug("No ActionProxyFactory defined for [{}]", key);
            }
        }
        LOG.debug("Cannot find any matching ActionProxyFactory, falling back to [{}]", defaultFactory);
        return defaultFactory.createActionProxy(namespace, actionName, methodName, extraContext, executeResult, cleanupContext);
    }
