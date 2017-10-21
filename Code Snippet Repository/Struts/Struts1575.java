    public void testArrayOfStringField() throws Exception {
        MyTestPerson testPerson = new MyTestPerson();
        testPerson.setFriends(new String[]{"Alice", "Matt"});

        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);

        RegexFieldValidator validator = new RegexFieldValidator();
        validator.setRegex("A([a-zA-Z]*)");
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("friends");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.setDefaultMessage("Only names starting with A are allowed!");

        validator.validate(testPerson);

        assertTrue(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertTrue(validator.getValidatorContext().hasFieldErrors());
        assertEquals(1, validator.getValidatorContext().getFieldErrors().size());
        assertEquals(1, validator.getValidatorContext().getFieldErrors().get("friends").size());
        assertEquals("Only names starting with A are allowed!", validator.getValidatorContext().getFieldErrors().get("friends").get(0));
    }
