    protected String invokeUnderTiming(ActionInvocation invocation) throws Exception {
        long startTime = System.currentTimeMillis();
        String result = invocation.invoke();
        long executionTime = System.currentTimeMillis() - startTime;

        StringBuilder message = new StringBuilder(100);
        message.append("Executed action [");
        String namespace = invocation.getProxy().getNamespace();
        if (StringUtils.isNotBlank(namespace)) {
            message.append(namespace).append("/");
        }
        message.append(invocation.getProxy().getActionName());
        message.append("!");
        message.append(invocation.getProxy().getMethod());
        message.append("] took ").append(executionTime).append(" ms.");

        doLog(getLoggerToUse(), message.toString());

        return result;
    }
