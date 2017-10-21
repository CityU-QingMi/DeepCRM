    public void testInvokeWithLazyParams() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("blah", "this is blah");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        DefaultActionInvocation defaultActionInvocation = new DefaultActionInvocation(extraContext, true);
        container.inject(defaultActionInvocation);

        ActionProxy actionProxy = actionProxyFactory.createActionProxy( "", "LazyFoo", null, extraContext);
        defaultActionInvocation.init(actionProxy);
        defaultActionInvocation.invoke();

        SimpleAction action = (SimpleAction) defaultActionInvocation.getAction();

        assertEquals("this is blah", action.getBlah());
        assertEquals("this is blah", action.getName());
    }
