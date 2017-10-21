    public String intercept(ActionInvocation invocation) throws Exception {
        LOG.debug("Clearing HttpSession");

        ActionContext ac = invocation.getInvocationContext();
        Map session = ac.getSession();
 
        if (null != session) {
            session.clear();
        }
        return invocation.invoke();
    }
