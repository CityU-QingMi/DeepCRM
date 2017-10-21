    public void testRequiredArrayOfStringsPass() throws Exception {
        // given
        ValueStack valueStack = ActionContext.getContext().getValueStack();

        ValidationAction action = new ValidationAction();
        action.setStrings(new String[]{"", "12334", null});
        valueStack.push(action);

        ValidatorContext context = new DummyValidatorContext(action, tpf);
        RequiredStringValidator validator = new RequiredStringValidator();
        validator.setValidatorContext(context);
        validator.setFieldName("strings");
        validator.setValueStack(valueStack);

        // when
        validator.validate(action);

        // then
        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertEquals(2, context.getFieldErrors().get("strings").size());
    }
