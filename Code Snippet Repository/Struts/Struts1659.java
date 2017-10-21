    public void testMaxValidation() throws Exception {
        // given
        ValidationAction action = prepareAction((short) 11);
        ValidatorContext context = new DummyValidatorContext(action, tpf);
        ShortRangeFieldValidator validator = prepareValidator(action, context);

        // when
        validator.validate(action);

        // then
        assertTrue(context.getFieldErrors().size() == 1);
        assertEquals("Max is 10, min is 2 but value is 11", context.getFieldErrors().get("shortRange").get(0));
    }
