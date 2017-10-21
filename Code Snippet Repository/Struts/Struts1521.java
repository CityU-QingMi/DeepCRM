    private void validateDoubleRangeFieldValidator(DoubleRangeFieldValidator validator) {
        assertEquals("foo", validator.getFieldName());
        assertEquals("double.key", validator.getMessageKey());
        assertEquals("Foo is out of range!", validator.getDefaultMessage());
        assertTrue(Arrays.equals(new String[]{"one", "two", "three"}, validator.getMessageParameters()));
        assertEquals(true, validator.isShortCircuit());
        assertEquals(1.4, validator.getMaxExclusive());
        assertEquals(1.2, validator.getMinExclusive());
        assertEquals(0.1, validator.getMaxInclusive());
        assertEquals(0.0, validator.getMinInclusive());
    }
