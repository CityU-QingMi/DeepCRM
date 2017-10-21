    public void testSimpleFieldOGNLExpression() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldOGNL", null, null);
        SimpleFieldOGNLExpression action = (SimpleFieldOGNLExpression) baseActionProxy.getAction();
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertNotNull(fieldErrors.get("name"));
    }
