    public String intercept(ActionInvocation invocation) throws Exception {
        LOG.trace("entering MessageStoreInterceptor ...");

        before(invocation);

        LOG.trace("Registering listener to store messages before result will be executed");
        MessageStorePreResultListener preResultListener = createPreResultListener(invocation);
        preResultListener.init(this);

        invocation.addPreResultListener(preResultListener);

        String result = invocation.invoke();

        LOG.debug("exit executing MessageStoreInterceptor");

        return result;
    }
