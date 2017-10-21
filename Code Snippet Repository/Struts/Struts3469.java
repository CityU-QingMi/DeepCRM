    public void testValidationInMethods() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "validationInMethods", null, null);
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(4, fieldErrors.size());
        assertValue(fieldErrors, "name", Arrays.asList("name cannot be null"));
        assertValue(fieldErrors, "SisyphusHasTheAnswer", Arrays.asList("SisyphusHasTheAnswer() cannot be null"));
        assertValue(fieldErrors, "thereAnyMeaningInLife", Arrays.asList("thereAnyMeaningInLife cannot be null"));
        assertValue(fieldErrors, "theManingOfLife", Arrays.asList("theManingOfLife cannot be null"));
    }
