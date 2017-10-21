    public void testSetAliasKeys() throws Exception {
        Action action = new SimpleFooAction();
        MockActionInvocation mai = new MockActionInvocation();

        MockActionProxy map = new MockActionProxy();

        ActionConfig cfg = new ActionConfig.Builder("", "", "")
                .addParam("hello", "invalid alias expression")
                .build();
        map.setConfig(cfg);

        mai.setProxy(map);
        mai.setAction(action);
        mai.setInvocationContext(ActionContext.getContext());

        AliasInterceptor ai = new AliasInterceptor();
        ai.init();
        ai.setAliasesKey("hello");

        ai.intercept(mai);

        ai.destroy();
    }
