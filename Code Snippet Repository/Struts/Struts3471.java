    public void testSimpleFieldsInheritedXML() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldsXMLChild", null, null);
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(3, fieldErrors.size());
        assertValue(fieldErrors, "firstName", Arrays.asList("firstName cannot be null"));
        assertValue(fieldErrors, "lastName", Arrays.asList("lastName cannot be null"));
        assertValue(fieldErrors, "middleName", Arrays.asList("middleName cannot be null"));
    }
