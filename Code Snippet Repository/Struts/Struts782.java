    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (devMode) {
            Parameter val = invocation.getInvocationContext().getParameters().get(profilingKey);
            if (val.isDefined()) {
                String sval = val.getValue();
                boolean enable = BooleanUtils.toBoolean(sval);
                UtilTimerStack.setActive(enable);
                invocation.getInvocationContext().getParameters().remove(profilingKey);
            }
        }
        return invocation.invoke();
    }
