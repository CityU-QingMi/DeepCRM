    public void testSimpleFieldOGNLExpressionNegative() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldOGNL", null, null);
        SimpleFieldOGNLExpression action = (SimpleFieldOGNLExpression) baseActionProxy.getAction();
        action.setName("Meursault");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(0, fieldErrors.size());
    }
