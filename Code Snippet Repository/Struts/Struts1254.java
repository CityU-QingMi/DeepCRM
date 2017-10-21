    public void testEvalExpressionAsParameterName() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("blah", "(#context[\"xwork.MethodAccessor.denyMethodExecution\"]= new " +
                "java.lang.Boolean(false), #_memberAccess[\"allowStaticMethodAccess\"]= new java.lang.Boolean(true), " +
                "@java.lang.Runtime@getRuntime().exec('mkdir /tmp/PWNAGE'))(meh)");
        params.put("top['blah'](0)", "true");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        proxy.execute();
        @SuppressWarnings("unused")
        SimpleAction action = (SimpleAction) proxy.getAction();
        File pwn = new File("/tmp/PWNAGE");
        boolean dirExists = pwn.exists();
        @SuppressWarnings("unused")
        boolean deleted = pwn.delete();
        Assert.assertFalse("Remote exploit: The PWN folder has been created", dirExists);
    }
