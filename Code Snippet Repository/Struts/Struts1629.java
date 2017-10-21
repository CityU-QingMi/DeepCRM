    public void testEmptyArrayOfCardNumbers() throws Exception {
        // given
        action.setAmericanExpresses(new String[]{});
        validator.setFieldName("americanExpresses");
        validator.setDefaultMessage("It is not a valid American Express card number: ${currentValue}");

        // when
        validator.validate(action);

        // then
        assertFalse(context.hasFieldErrors());
    }
