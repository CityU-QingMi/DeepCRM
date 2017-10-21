    public void testValidationSucceeds() throws Exception {
        JSONValidationInterceptor interceptor = new JSONValidationInterceptor();

        action.setText("abcd@ggg.com");
        action.setPassword("apassword");
        action.setValue(10);
        
        Map parameters = new HashMap();
        parameters.put("struts.enableJSONValidation", "true");
        request.setParameterMap(parameters);

        validationInterceptor.intercept(invocation);
        interceptor.intercept(invocation);

        String json = stringWriter.toString();

        String normalizedActual = TestUtils.normalize(json, true);
        assertEquals("", normalizedActual);
    }
