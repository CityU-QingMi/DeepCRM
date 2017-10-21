    public void testProxyInvocation() throws Exception {

        ActionProxy proxy = actionProxyFactory
            .createActionProxy("", "ProxyInvocation", null, createDummyContext());
        ActionInvocation invocation = proxy.getInvocation();
        
        String result = invocation.invokeActionOnly();
        assertEquals("proxyResult", result);

    }
