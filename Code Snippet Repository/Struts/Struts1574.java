    public void testNoStringField() throws Exception {
        MyTestPerson testPerson = new MyTestPerson();
        testPerson.setAge(33);

        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);

        RegexFieldValidator validator = new RegexFieldValidator();
        validator.setRegex("[0-9][0-9]");
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("age");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.validate(testPerson);

        assertFalse(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertFalse(validator.getValidatorContext().hasFieldErrors());
    }
