    public void testNestedValueStack() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        assertEquals(VALUE, stack.findValue(KEY));

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.VALUE_STACK, stack);

        ActionProxy proxy = actionProxyFactory.createActionProxy(NAMESPACE, STACK_ACTION_NAME, null, extraContext);
        proxy.execute();
        assertEquals(context, ActionContext.getContext());
        assertEquals(stack, ActionContext.getContext().getValueStack());
        assertEquals(VALUE, stack.findValue(KEY));
        assertEquals(NESTED_VALUE, stack.findValue(NESTED_KEY));
        assertEquals(3, stack.size());
    }
