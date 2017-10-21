    public void testConversionErrorMessageUsesProvidedMessage() throws ValidationException {
        String message = "default message";
        validator.setDefaultMessage(message);
        validator.validate(validationAware);


        Map fieldErrors = validationAware.getFieldErrors();
        assertTrue(fieldErrors.containsKey("foo"));
        assertEquals(message, ((List) fieldErrors.get("foo")).get(0));
    }
