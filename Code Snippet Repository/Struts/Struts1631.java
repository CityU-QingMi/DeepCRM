    public void testValidAmericanExpressCard() throws Exception {
        // given
        action.setAmericanExpress("378282246310005");
        validator.setFieldName("americanExpress");

        // when
        validator.validate(action);

        // then
        assertFalse(context.hasFieldErrors());
    }
