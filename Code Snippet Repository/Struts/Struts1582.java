    public void testAliasValidation() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("baz", "10");

        //valid values
        params.put("bar", "7");
        params.put("date", "12/23/2002");
        params.put("percentage", "1.23456789");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ACTION_NAME, null, extraContext);
            proxy.execute();

            ValidationAware validationAware = (ValidationAware) proxy.getAction();
            assertFalse(validationAware.hasFieldErrors());

            // put in an out-of-range value to see if the old validators still work
            ActionContext.setContext(new ActionContext(new HashMap<String, Object>()));

            params.put("bar", "42");
            extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

            proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ALIAS_NAME, null, extraContext);
            proxy.execute();
            validationAware = (ValidationAware) proxy.getAction();
            assertTrue(validationAware.hasFieldErrors());

            Map<String, List<String>> errors = validationAware.getFieldErrors();
            assertTrue(errors.containsKey("baz"));

            List<String> bazErrors = errors.get("baz");
            assertEquals(1, bazErrors.size());

            String message = bazErrors.get(0);
            assertEquals("baz out of range.", message);
            assertTrue(errors.containsKey("bar"));

            List<String> barErrors = errors.get("bar");
            assertEquals(1, barErrors.size());
            message = barErrors.get(0);
            assertEquals("bar must be between 6 and 10, current value is 42.", message);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
