    protected String doIntercept(ActionInvocation invocation) throws Exception {

        Object action = invocation.getAction();
        if (action != null) {
            Method method = getActionMethod(action.getClass(), invocation.getProxy().getMethod());

            if (null != MethodUtils.getAnnotation(method, SkipValidation.class, true, true)) {
                return invocation.invoke();
            }
        }

        return super.doIntercept(invocation);
    }
