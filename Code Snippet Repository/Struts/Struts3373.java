    public void testValidationFails() throws Exception {
        
        action.addActionError("General error");
        
        Map parameters = new HashMap();
        parameters.put("struts.enableJSONValidation", "true");
        request.setParameterMap(parameters);
        
        validationInterceptor.intercept(invocation);
        interceptor.intercept(invocation);

        String json = stringWriter.toString();

        String normalizedActual = TestUtils.normalize(json, true);

        //json
        assertThat(normalizedActual)
                .contains("\"errors\":[\"Generalerror\"]")
                .contains("\"fieldErrors\":{")
                .contains("\"value\":[\"Minvalueis-1\"]")
                .contains("\"text\":[\"Tooshort\",\"Thisisnoemail\"]")
                .contains("\"password\":[\"Passwordisn'tcorrect\"]");

        //execution
        assertFalse(action.isExecuted());
        //http status
        assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
        assertEquals("application/json", response.getContentType());
        assertEquals("UTF-8", response.getCharacterEncoding());
    }
