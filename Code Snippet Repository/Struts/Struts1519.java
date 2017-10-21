    private void validateEmailValidator(EmailValidator validator) {
        assertEquals("foo", validator.getFieldName());
        assertEquals(EmailValidator.EMAIL_ADDRESS_PATTERN, validator.getRegex());
        assertEquals("Foo isn't a valid e-mail!", validator.getDefaultMessage());
        assertEquals("email.key", validator.getMessageKey());
        assertTrue(Arrays.equals(new String[]{"one", "two", "three"}, validator.getMessageParameters()));
        assertEquals(true, validator.isShortCircuit());
        assertEquals(false, validator.isCaseSensitive());
        assertEquals(true, validator.isTrimed());
    }
