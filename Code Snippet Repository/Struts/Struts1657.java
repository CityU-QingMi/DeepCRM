    public void testPassValidation() throws Exception {
        // given
        ValidationAction action = prepareAction((short) 5);
        ValidatorContext context = new DummyValidatorContext(action, tpf);
        ShortRangeFieldValidator validator = prepareValidator(action, context);

        // when
        validator.validate(action);

        // then
        assertTrue(context.getFieldErrors().size() == 0);
    }
