    public void testProxiedActionIsNotStateful() throws Exception {
        ActionProxy proxy = actionProxyFactory.createActionProxy(null, "proxiedAction", null, null);
        SimpleAction action = (SimpleAction) proxy.getAction();

        action.setBlah("Hello World");

        proxy = actionProxyFactory.createActionProxy(null, "proxiedAction", null, null);
        action = (SimpleAction) proxy.getAction();

        // If the action is a singleton, this test will fail
        SimpleAction sa = new SimpleAction();
        assertEquals(sa.getBlah(), action.getBlah());

        // And if the advice is not being applied, this will be SUCCESS.
        String result = action.execute();
        assertEquals(Action.INPUT, result);
    }
