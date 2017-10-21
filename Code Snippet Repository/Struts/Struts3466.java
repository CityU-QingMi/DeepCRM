    public void testProgrammaticValidationDontInvokeValidate() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldNoValidate", null, null);
        SimpleField action = (SimpleField) baseActionProxy.getAction();
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(1, fieldErrors.size());
        assertValue(fieldErrors, "name", Arrays.asList("name cannot be null"));
        assertFalse(action.isValidateCalled());
        assertTrue(action.isValidateExecuteCalled());
    }
