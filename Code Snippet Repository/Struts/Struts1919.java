    public void testCAllExecute2Times() throws Exception {
        setToken(request);
        ActionProxy proxy = buildProxy(getActionName());
        assertEquals(Action.SUCCESS, proxy.execute());

        ActionProxy proxy2 = buildProxy(getActionName());
        // must not call setToken
        // double post will result in a invalid.token return code
        assertEquals(TokenInterceptor.INVALID_TOKEN_CODE, proxy2.execute());
    }
