    public void testSubPropertiesAreValidated() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("baz", "10");

        //valid values
        params.put("foo", "8");
        params.put("bar", "7");
        params.put("date", "12/23/2002");

        params.put("bean.name", "Name should be valid");

        // this should cause a message
        params.put("bean.count", "100");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_SUBPROPERTY_NAME, null, extraContext);
            proxy.execute();
            assertTrue(((ValidationAware) proxy.getAction()).hasFieldErrors());

            Map<String, List<String>> errors = ((ValidationAware) proxy.getAction()).getFieldErrors();
            List<String> beanCountErrors = errors.get("bean.count");
            assertEquals(1, beanCountErrors.size());

            String errorMessage = beanCountErrors.get(0);
            assertNotNull(errorMessage);
            assertEquals("bean.count out of range.", errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
