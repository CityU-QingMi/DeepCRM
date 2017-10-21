    protected String handleInvalidToken(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        String errorMessage = getErrorMessage(invocation);

        if (action instanceof ValidationAware) {
            ((ValidationAware) action).addActionError(errorMessage);
        } else {
            LOG.warn(errorMessage);
        }

        return INVALID_TOKEN_CODE;
    }
