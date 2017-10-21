    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();

        if (action instanceof ValidationAware) {
            ValidationAware validationAwareAction = (ValidationAware) action;

            if (validationAwareAction.hasErrors()) {
                LOG.debug("Errors on action [{}], returning result name [{}]", validationAwareAction, inputResultName);

                String resultName = inputResultName;
                resultName = processValidationWorkflowAware(action, resultName);
                resultName = processInputConfig(action, invocation.getProxy().getMethod(), resultName);
                resultName = processValidationErrorAware(action, resultName);

                return resultName;
            }
        }

        return invocation.invoke();
    }
