    private boolean isValidateAnnotatedMethodOnly(String actionName) {
        RuntimeConfiguration runtimeConfiguration = configuration.getRuntimeConfiguration();
        String actionNamespace = TagUtils.buildNamespace(actionMapper, stack, request);
        ActionConfig actionConfig = runtimeConfiguration.getActionConfig(actionNamespace, actionName);

        if (actionConfig != null) {
            List<InterceptorMapping> interceptors = actionConfig.getInterceptors();
            for (InterceptorMapping interceptorMapping : interceptors) {
                if (ValidationInterceptor.class.isInstance(interceptorMapping.getInterceptor())) {
                    ValidationInterceptor validationInterceptor = (ValidationInterceptor) interceptorMapping.getInterceptor();
                    return validationInterceptor.isValidateAnnotatedMethodOnly();
                }
            }
        }
        return false;
    }
