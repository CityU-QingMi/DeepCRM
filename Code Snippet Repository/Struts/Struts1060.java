    public void testWildCardEvaluation() throws Exception {
        ActionContext.setContext(null);
        ActionProxy proxy = actionProxyFactory.createActionProxy(null, "WildCard", null, null);
        assertEquals("success", proxy.execute());
        assertEquals(VoidResult.class, proxy.getInvocation().getResult().getClass());

        ActionContext.setContext(null);
        proxy = actionProxyFactory.createActionProxy(null, "WildCardInput", null, null);
        assertEquals("input", proxy.execute());
        assertEquals(MockResult.class, proxy.getInvocation().getResult().getClass());

        ActionContext.setContext(null);
        proxy = actionProxyFactory.createActionProxy(null, "WildCardError", null, null);
        assertEquals("error", proxy.execute());
        assertEquals(MockResult.class, proxy.getInvocation().getResult().getClass());
    }
