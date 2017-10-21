    public String execute() throws Exception {
        ActionContext previous = ActionContext.getContext();
        ActionContext.setContext(invocation.getInvocationContext());
        try {
// This is for the new API:
//            return RequestContextImpl.callInContext(invocation, new Callable<String>() {
//                public String call() throws Exception {
//                    return invocation.invoke();
//                }
//            });

            return invocation.invoke();
        } finally {
            if (cleanupContext)
                ActionContext.setContext(previous);
        }
    }
