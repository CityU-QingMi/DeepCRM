    public void testCollectionOfStringsLengthTrim() throws Exception {
        action.setStringCollection(Arrays.asList("123456", "    ", null));

        validator.setFieldName("stringCollection");
        validator.setTrim(true);
        validator.validate(action);

        assertTrue(action.hasFieldErrors());
        assertEquals(1, action.getFieldErrors().get("stringCollection").size());
    }
