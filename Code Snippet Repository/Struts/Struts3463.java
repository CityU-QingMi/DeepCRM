    public void testSimpleFieldI18n2() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldI18n", null, null);
        SimpleFieldI18n action = (SimpleFieldI18n) baseActionProxy.getAction();
        action.setName("123123");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertValue(fieldErrors, "name", Arrays.asList("name value is too long, allowed length is 3"));
    }
