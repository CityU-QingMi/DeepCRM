    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Validator validator = this.beanValidationManager.getValidator();
        if (validator == null) {
            LOG.debug("There is no Bean Validator configured in class path. Skipping Bean validation..");
            return invocation.invoke();
        }
        LOG.debug("Starting bean validation using validator: {}", validator.getClass());

        Object action = invocation.getAction();
        ActionProxy actionProxy = invocation.getProxy();
        String methodName = actionProxy.getMethod();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Validating [{}/{}] with method [{}]", invocation.getProxy().getNamespace(), invocation.getProxy().getActionName(), methodName);
        }

        if (null == MethodUtils.getAnnotation(getActionMethod(action.getClass(), methodName), SkipValidation.class,
                true, true)) {
            // performing bean validation on action
            performBeanValidation(action, validator);
        }

        return invocation.invoke();
    }
