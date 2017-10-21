    public void testArrayOfStringsLengthTrim() throws Exception {
        action.setStrings(new String[]{"123456", "    ", null});

        validator.setFieldName("strings");
        validator.setTrim(true);
        validator.validate(action);

        assertTrue(action.hasFieldErrors());
        assertEquals(1, action.getFieldErrors().get("strings").size());
    }
