    public void testSimpleFieldTooLong() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldTooLong", null, null);
        SimpleField action = (SimpleField) baseActionProxy.getAction();
        action.setName("12367");
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertValue(fieldErrors, "name", Arrays.asList("name is not between 0 and 3 characters long"));
        assertValue(fieldErrors, "name", Arrays.asList("name is not between 0 and 3 characters long"));
    }
