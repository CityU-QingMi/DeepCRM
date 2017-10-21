    public void testValidationSucceedsValidateOnly() throws Exception {
        JSONValidationInterceptor interceptor = new JSONValidationInterceptor();

        action.setText("abcd@ggg.com");
        action.setPassword("apassword");
        action.setValue(10);

        //just validate
        Map parameters = new HashMap();
        parameters.put("struts.validateOnly", "true");
        parameters.put("struts.enableJSONValidation", "true");
        request.setParameterMap(parameters);
        
        validationInterceptor.intercept(invocation);
        interceptor.intercept(invocation);

        String json = stringWriter.toString();

        String normalizedActual = TestUtils.normalize(json, true);
        assertEquals("{}", normalizedActual);
        assertFalse(action.isExecuted());
        assertEquals("application/json", response.getContentType());
        assertEquals("UTF-8", response.getCharacterEncoding());
    }
