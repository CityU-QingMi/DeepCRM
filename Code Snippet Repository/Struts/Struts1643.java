    public void testArrayOfIntValidation() throws Exception {
        // given
        ValidationAction action = new ValidationAction();
        action.setInts(new Integer[] {99, 100, 101, 102});

        ValidatorContext context = new DummyValidatorContext(action, tpf);
        IntRangeFieldValidator validator = prepareValidator(action, context);

        // when
        validator.setMin(100);
        validator.setMax(101);
        validator.setFieldName("ints");
        validator.setDefaultMessage("Max is ${max}, min is ${min} but value is ${currentValue}");
        validator.validate(action);

        // then
        assertEquals(1, context.getFieldErrors().size());
        assertEquals(2, context.getFieldErrors().get("ints").size());
        assertEquals("Max is 101, min is 100 but value is 99", context.getFieldErrors().get("ints").get(0));
        assertEquals("Max is 101, min is 100 but value is 102", context.getFieldErrors().get("ints").get(1));
    }
