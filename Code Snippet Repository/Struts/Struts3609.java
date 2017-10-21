    @Override
    public ActionProxy createActionProxy(String namespace, String actionName, String methodName, Map<String, Object> extraContext,
                                         boolean executeResult, boolean cleanupContext)
    {
        if (this.namespace == null || namespace.startsWith(this.namespace)) {
            ActionInvocation inv = new RestActionInvocation(extraContext, true);
            container.inject(inv);
            return createActionProxy(inv, namespace, actionName, methodName, executeResult, cleanupContext);
        } else {
            return super.createActionProxy(namespace, actionName, methodName, extraContext, executeResult, cleanupContext);
        }
    }
