    public void testMasterCardCard() throws Exception {
        // given
        action.setMasterCard("5555555555554444");
        validator.setFieldName("masterCard");

        // when
        validator.validate(action);

        // then
        assertFalse(context.hasFieldErrors());
    }
