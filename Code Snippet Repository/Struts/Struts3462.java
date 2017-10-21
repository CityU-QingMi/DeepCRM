    public void testSimpleFieldI18n() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldI18n", null, null);
        SimpleFieldI18n action = (SimpleFieldI18n) baseActionProxy.getAction();
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertValue(fieldErrors, "name", Arrays.asList("name cannot be null"));
    }
