    protected void prepare() {
        String profileKey = "create DefaultActionProxy: ";
        try {
            UtilTimerStack.push(profileKey);
            config = configuration.getRuntimeConfiguration().getActionConfig(namespace, actionName);

            if (config == null && unknownHandlerManager.hasUnknownHandlers()) {
                config = unknownHandlerManager.handleUnknownAction(namespace, actionName);
            }
            if (config == null) {
                throw new ConfigurationException(getErrorMessage());
            }

            resolveMethod();

            if (config.isAllowedMethod(method)) {
                invocation.init(this);
            } else {
                throw new ConfigurationException(prepareNotAllowedErrorMessage());
            }
        } finally {
            UtilTimerStack.pop(profileKey);
        }
    }
