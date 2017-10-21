    public void testListOfStringField() throws Exception {
        MyTestPerson testPerson = new MyTestPerson();
        testPerson.setCars(Arrays.asList("Audi", "BMW"));

        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);

        RegexFieldValidator validator = new RegexFieldValidator();
        validator.setRegex("A([a-zA-Z]*)");
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("cars");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.setDefaultMessage("Only cars starting with A are allowed!");

        validator.validate(testPerson);

        assertTrue(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertTrue(validator.getValidatorContext().hasFieldErrors());
        assertEquals(1, validator.getValidatorContext().getFieldErrors().size());
        assertEquals(1, validator.getValidatorContext().getFieldErrors().get("cars").size());
        assertEquals("Only cars starting with A are allowed!", validator.getValidatorContext().getFieldErrors().get("cars").get(0));
    }
