    public void testValidationSucceedsWithDifferentParamName() throws Exception {
        JSONValidationInterceptor interceptor = new JSONValidationInterceptor();
        interceptor.setValidateJsonParam("enableJSONValidation");

        action.setText("abcd@ggg.com");
        action.setPassword("apassword");
        action.setValue(10);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("enableJSONValidation", "true");
        request.setParameterMap(parameters);

        validationInterceptor.intercept(invocation);
        interceptor.intercept(invocation);

        String json = stringWriter.toString();

        String normalizedActual = TestUtils.normalize(json, true);
        assertEquals("", normalizedActual);
    }
