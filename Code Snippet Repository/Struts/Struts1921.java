    public void testCAllExecute2Times() throws Exception {
        setToken(request);
        ActionProxy proxy = buildProxy(getActionName());
        assertEquals(Action.SUCCESS, proxy.execute());

        ActionProxy proxy2 = buildProxy(getActionName());
        // must not call setToken
        // double post will just return success and render the same view as the first execute
        // see TokenInterceptor where a 2nd call will return invalid.token code instead
        assertEquals(Action.SUCCESS, proxy2.execute());
    }
