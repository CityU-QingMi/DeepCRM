    public void testSimpleFieldI18nDefaultKey() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldI18nDefaultKey", null, null);
        SimpleFieldI18nDefaultKey action = (SimpleFieldI18nDefaultKey) baseActionProxy.getAction();
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertValue(fieldErrors, "name", Arrays.asList("notnull.field"));
    }
