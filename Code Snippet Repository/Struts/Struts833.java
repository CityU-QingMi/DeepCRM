    public static ActionInvocation loadInvocation(String key, String token) {
        InvocationContext invocationContext = (InvocationContext) getInvocationMap().get(key);

        if ((invocationContext == null) || !invocationContext.token.equals(token)) {
            return null;
        }

        ValueStack stack = invocationContext.invocation.getStack();
        ActionContext.getContext().setValueStack(stack);

        return invocationContext.invocation.deserialize(ActionContext.getContext());
    }
