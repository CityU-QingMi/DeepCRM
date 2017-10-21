    public void testRecursiveChain() throws Exception {
        ActionProxy proxy = actionProxyFactory.createActionProxy("", "InfiniteRecursionChain", null, null);

        try {
            proxy.execute();
            fail("did not detected repeated chain to an action");
        } catch (XWorkException e) {
            assertTrue(true);
        }
    }
