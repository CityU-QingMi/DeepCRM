    private void validateRegexValidator(RegexFieldValidator validator) {
        assertEquals("foo", validator.getRegex());
        assertEquals("Foo doesn't match!", validator.getDefaultMessage());
        assertEquals("regex.key", validator.getMessageKey());
        assertEquals("bar", validator.getFieldName());
        assertEquals(true, validator.isShortCircuit());
        assertEquals(false, validator.isTrimed());
        assertEquals(false, validator.isCaseSensitive());
        assertTrue(Arrays.equals(new String[]{"one", "two", "three"}, validator.getMessageParameters()));
    }
