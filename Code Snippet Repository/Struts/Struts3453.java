    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        ActionProxy proxy = invocation.getProxy();
        ValueStack valueStack = invocation.getStack();
        String methodName = proxy.getMethod();
        String context = proxy.getConfig().getName();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Validating [{}/{}] with method [{}]", invocation.getProxy().getNamespace(), invocation.getProxy().getActionName(), methodName);
        }

        //OVal vallidatio (no XML yet)
        performOValValidation(action, valueStack, methodName, context);

        //Validatable.valiedate() and validateX()
        performProgrammaticValidation(invocation, action);

        return invocation.invoke();
    }
