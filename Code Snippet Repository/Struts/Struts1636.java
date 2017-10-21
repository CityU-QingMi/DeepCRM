    public void testPassValidation() throws Exception {
        // given
        ValidationAction action = prepareAction(createDate(2013, 6, 6));
        ValidatorContext context = new DummyValidatorContext(action, tpf);
        DateRangeFieldValidator validator = prepareValidator(action, context);

        // when
        validator.validate(action);

        // then
        assertTrue(context.getFieldErrors().size() == 0);
    }
