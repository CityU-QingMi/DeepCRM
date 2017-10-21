    public void testValidAmericanExpressCardWithSpaces() throws Exception {
        // given
        action.setAmericanExpress("3782 8224 6310 005");
        validator.setFieldName("americanExpress");

        // when
        validator.validate(action);

        // then
        assertFalse(context.hasFieldErrors());
    }
