    protected String getRequestOperationMode(ActionInvocation invocation) {
        String reqOperationMode = NONE;
        if (allowRequestParameterSwitch) {
            HttpParameters reqParams = invocation.getInvocationContext().getParameters();
            if (reqParams.contains(requestParameterSwitch)) {
                reqOperationMode = reqParams.get(requestParameterSwitch).getValue();
            }
        }
        return reqOperationMode;
    }
