    public void testInvalidValue() throws Exception {

        URLValidator validator = new URLValidator();
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("testingUrl3");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.validate(new MyObject());

        assertTrue(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertTrue(validator.getValidatorContext().hasFieldErrors());
    }
