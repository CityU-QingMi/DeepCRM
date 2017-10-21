    public void testMinValidation() throws Exception {
        // given
        ValidationAction action = prepareAction(createDate(2012, Calendar.MARCH, 3));
        ValidatorContext context = new DummyValidatorContext(action, tpf);
        DateRangeFieldValidator validator = prepareValidator(action, context);

        // when
        validator.validate(action);

        // then
        assertTrue(context.getFieldErrors().size() == 1);
        assertEquals("Max is 12.12.13, min is 01.01.13 but value is 03.03.12", context.getFieldErrors().get("dateRange").get(0));
    }
