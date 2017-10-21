    protected void before(ActionInvocation invocation) throws Exception {
        String reqOperationMode = getRequestOperationMode(invocation);

        if (RETRIEVE_MODE.equalsIgnoreCase(reqOperationMode) ||
                RETRIEVE_MODE.equalsIgnoreCase(operationMode) ||
                AUTOMATIC_MODE.equalsIgnoreCase(operationMode)) {

            Object action = invocation.getAction();
            if (action instanceof ValidationAware) {
                // retrieve error / message from session
                Map session = (Map) invocation.getInvocationContext().get(ActionContext.SESSION);

                if (session == null) {
                    LOG.debug("Session is not open, no errors / messages could be retrieve for action [{}]", action);
                    return;
                }

                ValidationAware validationAwareAction = (ValidationAware) action;

                LOG.debug("Retrieve error / message from session to populate into action [{}]", action);

                Collection actionErrors = (Collection) session.get(actionErrorsSessionKey);
                Collection actionMessages = (Collection) session.get(actionMessagesSessionKey);
                Map fieldErrors = (Map) session.get(fieldErrorsSessionKey);

                if (actionErrors != null && actionErrors.size() > 0) {
                    Collection mergedActionErrors = mergeCollection(validationAwareAction.getActionErrors(), actionErrors);
                    validationAwareAction.setActionErrors(mergedActionErrors);
                }

                if (actionMessages != null && actionMessages.size() > 0) {
                    Collection mergedActionMessages = mergeCollection(validationAwareAction.getActionMessages(), actionMessages);
                    validationAwareAction.setActionMessages(mergedActionMessages);
                }

                if (fieldErrors != null && fieldErrors.size() > 0) {
                    Map mergedFieldErrors = mergeMap(validationAwareAction.getFieldErrors(), fieldErrors);
                    validationAwareAction.setFieldErrors(mergedFieldErrors);
                }
                session.remove(actionErrorsSessionKey);
                session.remove(actionMessagesSessionKey);
                session.remove(fieldErrorsSessionKey);
            }
        }
    }
