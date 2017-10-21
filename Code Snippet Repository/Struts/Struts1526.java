    public void testRangeValidation() throws Exception {
        Calendar date = Calendar.getInstance();
        date.set(2002, Calendar.NOVEMBER, 20);
        Map<String, Object> context = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("date", date.getTime());
        context.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ACTION_NAME, null, context);
        proxy.execute();
        assertTrue(((ValidationAware) proxy.getAction()).hasFieldErrors());

        Map<String, List<String>> errors = ((ValidationAware) proxy.getAction()).getFieldErrors();

        List<String> errorMessages = errors.get("date");
        assertNotNull("Expected date range validation error message.", errorMessages);
        assertEquals(1, errorMessages.size());

        String errorMessage = errorMessages.get(0);
        assertEquals("The date must be between 12-22-2002 and 12-25-2002.", errorMessage);
    }
