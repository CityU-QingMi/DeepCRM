    public void testAccessToOgnlInternals() throws Exception {
        // given
        Map<String, Object> params = new HashMap<>();
        params.put("blah", "This is blah");
        params.put("('\\u0023_memberAccess[\\'allowStaticMethodAccess\\']')(meh)", "true");
        params.put("('(aaa)(('\\u0023context[\\'xwork.MethodAccessor.denyMethodExecution\\']\\u003d\\u0023foo')(\\u0023foo\\u003dnew java.lang.Boolean(\"false\")))", "");
        params.put("(asdf)(('\\u0023rt.exit(1)')(\\u0023rt\\u003d@java.lang.Runtime@getRuntime()))", "1");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        ValueStack stack = proxy.getInvocation().getStack();

        // when
        proxy.execute();
        proxy.getAction();

        //then
        assertEquals("This is blah", ((SimpleAction) proxy.getAction()).getBlah());
        boolean allowMethodAccess = ((SecurityMemberAccess) ((OgnlContext) stack.getContext()).getMemberAccess()).getAllowStaticMethodAccess();
        assertFalse(allowMethodAccess);
    }
