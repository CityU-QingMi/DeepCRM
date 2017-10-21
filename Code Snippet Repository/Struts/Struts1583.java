    public void testLookingUpFieldNameAsTextKey() {
        HashMap<String, Object> params = new HashMap<>();

        // should cause a message
        params.put("baz", "-1");

        //valid values
        params.put("bar", "7");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ACTION_NAME, null, extraContext);
            proxy.execute();
            assertTrue(((ValidationAware) proxy.getAction()).hasFieldErrors());

            Map<String, List<String>> errors = ((ValidationAware) proxy.getAction()).getFieldErrors();
            List<String> bazErrors = errors.get("baz");
            assertEquals(1, bazErrors.size());

            String errorMessage = bazErrors.get(0);
            assertNotNull(errorMessage);
            assertEquals("Baz Field must be greater than 0", errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
