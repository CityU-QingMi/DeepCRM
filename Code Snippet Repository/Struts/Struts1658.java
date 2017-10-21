    public void testMinValidation() throws Exception {
        // given
        ValidationAction action = prepareAction((short) 1);
        ValidatorContext context = new DummyValidatorContext(action, tpf);
        ShortRangeFieldValidator validator = prepareValidator(action, context);

        // when
        validator.validate(action);

        // then
        assertTrue(context.getFieldErrors().size() == 1);
        assertEquals("Max is 10, min is 2 but value is 1", context.getFieldErrors().get("shortRange").get(0));
    }
