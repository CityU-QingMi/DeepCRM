    public void testSimpleFieldsXML() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldsXML", null, null);
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(2, fieldErrors.size());
        assertValue(fieldErrors, "firstName", Arrays.asList("firstName cannot be null"));
        assertValue(fieldErrors, "lastName", Arrays.asList("lastName cannot be null"));
    }
