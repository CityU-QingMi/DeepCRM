    public void testRequiredStringFails() throws Exception {
        // given
        ValueStack valueStack = ActionContext.getContext().getValueStack();

        ValidationAction action = new ValidationAction();
        valueStack.push(action);

        ValidatorContext context = new DummyValidatorContext(action, tpf);
        RequiredStringValidator validator = new RequiredStringValidator();
        validator.setValidatorContext(context);
        validator.setFieldName("stringValue");
        validator.setValueStack(valueStack);
        validator.setDefaultMessage("Field ${fieldName} is required");

        // when
        validator.validate(action);

        // then
        assertTrue(context.getFieldErrors().size() == 1);
        assertEquals(context.getFieldErrors().get("stringValue").get(0), "Field stringValue is required");
    }
