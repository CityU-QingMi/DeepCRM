    public void testVisaCard() throws Exception {
        // given
        action.setVisa("4111111111111111");
        validator.setFieldName("visa");

        // when
        validator.validate(action);

        // then
        assertFalse(context.hasFieldErrors());
    }
