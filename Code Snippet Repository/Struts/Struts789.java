    public String intercept(ActionInvocation invocation) throws Exception {
        String result = null;
        Map ses = ActionContext.getContext().getSession();
        before(invocation);
        try {
            result = invocation.invoke();
            after(invocation, result);
        } finally {
            if (ses != null) {
                unlock(ses);
            }
        }

        return result;
    }
