    private void validateStringLengthFieldValidator(StringLengthFieldValidator validator) {
        assertEquals("foo", validator.getFieldName());
        assertEquals("stringlength.key", validator.getMessageKey());
        assertEquals("Foo is too long!", validator.getDefaultMessage());
        assertTrue(Arrays.equals(new String[]{"one", "two", "three"}, validator.getMessageParameters()));
        assertEquals(1, validator.getMinLength());
        assertEquals(10, validator.getMaxLength());
        assertEquals(true, validator.isShortCircuit());
        assertEquals(false, validator.isTrim());
    }
