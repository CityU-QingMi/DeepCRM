    protected void evaluateClientSideJsEnablement(String actionName, String namespace, String actionMethod) {

        // Only evaluate if Client-Side js is to be enable when validate=true
        Boolean validate = (Boolean) getParameters().get("validate");
        if (validate != null && validate) {

            addParameter("performValidation", Boolean.FALSE);

            RuntimeConfiguration runtimeConfiguration = configuration.getRuntimeConfiguration();
            ActionConfig actionConfig = runtimeConfiguration.getActionConfig(namespace, actionName);

            if (actionConfig != null) {
                List<InterceptorMapping> interceptors = actionConfig.getInterceptors();
                for (InterceptorMapping interceptorMapping : interceptors) {
                    if (ValidationInterceptor.class.isInstance(interceptorMapping.getInterceptor())) {
                        ValidationInterceptor validationInterceptor = (ValidationInterceptor) interceptorMapping.getInterceptor();

                        Set excludeMethods = validationInterceptor.getExcludeMethodsSet();
                        Set includeMethods = validationInterceptor.getIncludeMethodsSet();

                        if (MethodFilterInterceptorUtil.applyMethod(excludeMethods, includeMethods, actionMethod)) {
                            addParameter("performValidation", Boolean.TRUE);
                        }
                        return;
                    }
                }
            }
        }
    }
