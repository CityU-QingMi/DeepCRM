    public void testSimpleFieldMultipleValidators() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleField", null, null);
        SimpleField action = (SimpleField) baseActionProxy.getAction();
        action.setName("12345");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertNotNull(fieldErrors.get("name"));
    }
