    public void testAcceptNullValueForMutualExclusionOfValidators() throws Exception {

        URLValidator validator = new URLValidator();
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("testingUrl1");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.validate(new MyObject());

        assertFalse(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertFalse(validator.getValidatorContext().hasFieldErrors());
    }
