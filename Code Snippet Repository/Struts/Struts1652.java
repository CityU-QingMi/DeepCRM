    public void testRequiredStringPass() throws Exception {
        // given
        ValueStack valueStack = ActionContext.getContext().getValueStack();

        ValidationAction action = new ValidationAction();
        action.setStringValue("a string");
        valueStack.push(action);

        ValidatorContext context = new DummyValidatorContext(action, tpf);
        RequiredStringValidator validator = new RequiredStringValidator();
        validator.setValidatorContext(context);
        validator.setFieldName("stringValue");
        validator.setValueStack(valueStack);

        // when
        validator.validate(action);

        // then
        assertTrue(context.getFieldErrors().size() == 0);
    }
