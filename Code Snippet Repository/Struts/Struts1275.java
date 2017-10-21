    public void testPreResultListenersAreCalledInOrder() throws Exception {
        ActionProxy proxy = actionProxyFactory.createActionProxy("package", "action", null, new HashMap<String, Object>(), false, true);
        ActionInvocation invocation = proxy.getInvocation();
        CountPreResultListener listener1 = new CountPreResultListener();
        CountPreResultListener listener2 = new CountPreResultListener();
        invocation.addPreResultListener(listener1);
        invocation.addPreResultListener(listener2);
        proxy.execute();
        assertNotNull(listener1.getMyOrder());
        assertNotNull(listener2.getMyOrder());
        assertEquals(listener1.getMyOrder() + 1, listener2.getMyOrder().intValue());
    }
