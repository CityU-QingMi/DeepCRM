    public void testNoFieldName() throws Exception {
        MyTestPerson testPerson = new MyTestPerson();
        testPerson.setUsername("NoExpression");

        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);

        RegexFieldValidator validator = new RegexFieldValidator();
        validator.setRegex("^Sec.*");
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName(null);
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.validate(testPerson);

        assertFalse(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertFalse(validator.getValidatorContext().hasFieldErrors());
    }
