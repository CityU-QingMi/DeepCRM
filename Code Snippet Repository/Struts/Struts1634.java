    public void testValidJCBCard() throws Exception {
        // given
        action.setJCB("3530111333300000");
        validator.setFieldName("JCB");

        // when
        validator.validate(action);

        // then
        assertFalse(context.hasFieldErrors());
    }
