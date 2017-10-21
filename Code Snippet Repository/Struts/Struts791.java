    protected Object getOverrideExpr(ActionInvocation invocation, Object value) {
        ValueStack stack = invocation.getStack();

        try {
            stack.push(value);

            return escape(stack.findString("top"));
        } finally {
            stack.pop();
        }
    }
