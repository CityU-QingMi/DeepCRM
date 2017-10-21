    public void testSimpleFieldNegative() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleField", null, null);
        SimpleField action = (SimpleField) baseActionProxy.getAction();
        action.setName("123");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(0, fieldErrors.size());
    }
