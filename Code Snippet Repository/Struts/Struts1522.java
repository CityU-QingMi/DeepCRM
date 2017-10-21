    private void validateConditionalFieldVisitorValidator(ConditionalVisitorFieldValidator validator) {
        assertEquals("foo+bar", validator.getExpression());
        assertEquals("some", validator.getContext());
        assertEquals("Foo doesn't match!", validator.getDefaultMessage());
        assertEquals("bar", validator.getFieldName());
        assertEquals(false, validator.isAppendPrefix());
        assertEquals(true, validator.isShortCircuit());
        assertEquals("conditional.key", validator.getMessageKey());
        assertTrue(Arrays.equals(new String[]{"one", "two", "three"}, validator.getMessageParameters()));
    }
