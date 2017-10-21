    public void testParamterizedMessage() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("bar", "42");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ACTION_NAME, null, extraContext);
            proxy.execute();
            assertTrue(((ValidationAware) proxy.getAction()).hasFieldErrors());

            Map<String, List<String>> errors = ((ValidationAware) proxy.getAction()).getFieldErrors();
            List<String> barErrors = errors.get("bar");
            assertEquals(1, barErrors.size());

            String errorMessage = barErrors.get(0);
            assertNotNull(errorMessage);
            assertEquals("bar must be between 6 and 10, current value is 42.", errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
