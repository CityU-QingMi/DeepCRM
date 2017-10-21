    public void testFail() throws Exception {
        MyTestPerson testPerson = new MyTestPerson();
        testPerson.setUsername("Superman");

        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);

        RegexFieldValidator validator = new RegexFieldValidator();
        validator.setRegex("^Sec.*");
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("username");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.validate(testPerson);

        assertTrue(validator.getValidatorContext().hasErrors());
        assertTrue(validator.getValidatorContext().hasFieldErrors());
        List<String> msgs = validator.getValidatorContext().getFieldErrors().get("username");
        assertNotNull(msgs);
        assertTrue(msgs.size() == 1); // should contain 1 error message

        // when failing the validator will not add action errors/msg
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
    }
