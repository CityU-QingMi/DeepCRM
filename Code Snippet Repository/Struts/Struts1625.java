    public void testInvalidCardNumber() throws Exception {
        // given
        action.setAmericanExpress("123456768900");
        validator.setFieldName("americanExpress");
        validator.setDefaultMessage("It is not a valid American Express card number: ${americanExpress}");

        // when
        validator.validate(action);

        // then
        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertEquals("It is not a valid American Express card number: 123456768900", context.getFieldErrors().get("americanExpress").get(0));
    }
