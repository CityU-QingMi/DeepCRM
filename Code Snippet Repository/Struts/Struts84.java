    protected DefaultActionProxy(ActionInvocation inv, String namespace, String actionName, String methodName, boolean executeResult, boolean cleanupContext) {

        this.invocation = inv;
        this.cleanupContext = cleanupContext;
        LOG.debug("Creating an DefaultActionProxy for namespace [{}] and action name [{}]", namespace, actionName);

        this.actionName = StringEscapeUtils.escapeHtml4(actionName);
        this.namespace = namespace;
        this.executeResult = executeResult;
        this.method = StringEscapeUtils.escapeEcmaScript(StringEscapeUtils.escapeHtml4(methodName));
    }
