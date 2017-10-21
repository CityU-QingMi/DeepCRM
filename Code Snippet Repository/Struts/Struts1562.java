    public void testExpressionValidatorFailure() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("date", "12/23/2002");
        params.put("foo", "5");
        params.put("bar", "7");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ACTION_NAME, null, extraContext);
        proxy.execute();
        assertTrue(((ValidationAware) proxy.getAction()).hasActionErrors());

        Collection errors = ((ValidationAware) proxy.getAction()).getActionErrors();
        assertEquals(1, errors.size());

        String message = (String) errors.iterator().next();
        assertNotNull(message);
        assertEquals("Foo must be greater than Bar. Foo = 5, Bar = 7.", message);
    }
