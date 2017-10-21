    public void testRangeValidationNoError() throws Exception {
        Map<String, Object> context = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("percentage", 1.234567d);
        context.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", "percentage", null, context);
        proxy.execute();
        assertTrue(((ValidationAware) proxy.getAction()).hasFieldErrors());

        Map<String, List<String>> errors = ((ValidationAware) proxy.getAction()).getFieldErrors();
        List<String> errorMessages = errors.get("percentage");
        assertNull("Expected no double range validation error message.", errorMessages);
    }
