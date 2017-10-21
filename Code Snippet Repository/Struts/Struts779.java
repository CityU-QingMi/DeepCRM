    @Override
    public void beforeResult(ActionInvocation invocation, String resultCode) {

        boolean isCommitted = isCommitted();
        if (isCommitted) {
            LOG.trace("Response was already committed, cannot store messages!");
            return;
        }

        boolean isInvalidated = isInvalidated();
        if (isInvalidated) {
            LOG.trace("Session was invalidated or never created, cannot store messages!");
            return;
        }

        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if (session == null) {
            LOG.trace("Could not store action [{}] error/messages into session, because session hasn't been opened yet.", invocation.getAction());
            return;
        }

        String reqOperationMode = interceptor.getRequestOperationMode(invocation);

        boolean isRedirect = isRedirect(invocation, resultCode);

        if (MessageStoreInterceptor.STORE_MODE.equalsIgnoreCase(reqOperationMode) ||
                MessageStoreInterceptor.STORE_MODE.equalsIgnoreCase(interceptor.getOperationModel()) ||
                (MessageStoreInterceptor.AUTOMATIC_MODE.equalsIgnoreCase(interceptor.getOperationModel()) && isRedirect)) {

            Object action = invocation.getAction();
            if (action instanceof ValidationAware) {
                LOG.debug("Storing action [{}] error/messages into session ", action);

                ValidationAware validationAwareAction = (ValidationAware) action;
                session.put(MessageStoreInterceptor.actionErrorsSessionKey, validationAwareAction.getActionErrors());
                session.put(MessageStoreInterceptor.actionMessagesSessionKey, validationAwareAction.getActionMessages());
                session.put(MessageStoreInterceptor.fieldErrorsSessionKey, validationAwareAction.getFieldErrors());

            } else {
                LOG.debug("Action [{}] is not ValidationAware, no message / error that are storeable", action);
            }
        }
    }
