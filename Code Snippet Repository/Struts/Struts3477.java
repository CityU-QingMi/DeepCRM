    public void testSimpleMethod() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleMethod", null, null);
        SimpleMethod action = (SimpleMethod) baseActionProxy.getAction();
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertNotNull(fieldErrors.get("someName"));
    }
