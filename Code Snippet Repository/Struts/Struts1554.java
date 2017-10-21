    public void testRangeValidationWithExpressionsFail() throws Exception {
        //Explicitly set an out-of-range double for DoubleRangeValidatorTest
        Map<String, Object> context = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("percentage", 100.12);
        context.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.EXPRESSION_VALIDATION_ACTION, null, context);
        proxy.execute();
        assertTrue(((ValidationAware) proxy.getAction()).hasFieldErrors());

        Map<String, List<String>> errors = ((ValidationAware) proxy.getAction()).getFieldErrors();
        List<String> errorMessages = errors.get("percentage");
        assertNotNull("Expected double range validation error message.", errorMessages);
        assertEquals(1, errorMessages.size());

        String errorMessage = errorMessages.get(0);
        assertNotNull("Expecting: percentage must be between 0.1 and 10.1, current value is 100.12.", errorMessage);
        assertEquals("percentage must be between 0.1 and 10.1, current value is 100.12.", errorMessage);
    }
