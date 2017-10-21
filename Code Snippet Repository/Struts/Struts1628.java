    public void testInvalidArrayOfCardNumbers() throws Exception {
        // given
        action.setAmericanExpresses(new String[]{"098776544322"});
        validator.setFieldName("americanExpresses");
        validator.setDefaultMessage("It is not a valid American Express card number: ${currentValue}");

        // when
        validator.validate(action);

        // then
        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertEquals("It is not a valid American Express card number: 098776544322", context.getFieldErrors().get("americanExpresses").get(0));
    }
