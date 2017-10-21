    @Override
    protected Object invoke(String operationName, Object[] params,
            String[] signature) throws SchedulerException {
        try {
            return server.invoke(getSchedulerObjectName(), operationName, params, signature);
        } catch (Exception e) {
            throw new SchedulerException(
                "Failed to invoke Scheduler MBean operation: " + operationName, e);
        }
    }
