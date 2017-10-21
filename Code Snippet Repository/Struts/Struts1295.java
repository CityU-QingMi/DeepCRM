    public void testNoScopedModelAction() throws Exception {
        Action action = new SimpleAction();
        MockActionInvocation mai = new MockActionInvocation();
        MockActionProxy map = new MockActionProxy();
        ActionConfig ac = new ActionConfig.Builder("", "", "").build();
        map.setConfig(ac);
        mai.setAction(action);
        mai.setProxy(map);

        inter.intercept(mai);
        inter.destroy();
        // nothing happends
    }
