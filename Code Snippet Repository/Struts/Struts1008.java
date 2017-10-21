    public void testNestedNoValueStack() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        assertEquals(VALUE, stack.findValue(KEY));

        ActionProxy proxy = actionProxyFactory.createActionProxy(NAMESPACE, NO_STACK_ACTION_NAME, null, null);
        proxy.execute();
        stack = ActionContext.getContext().getValueStack();
        assertEquals(stack.findValue(KEY), VALUE);
        assertNull(stack.findValue(NESTED_KEY));
    }
