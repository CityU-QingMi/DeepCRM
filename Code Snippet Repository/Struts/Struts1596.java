    public void testCollectionOfUrls() throws Exception {
        URLValidator validator = new URLValidator();
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("urlCollection");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.setDefaultMessage("Wrong URL provided: ${currentValue}");
        validator.validate(new MyObject());

        assertTrue(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertTrue(validator.getValidatorContext().hasFieldErrors());
        assertEquals(1, validator.getValidatorContext().getFieldErrors().get("urlCollection").size());
        assertEquals("Wrong URL provided: htps://wrong.side.com", validator.getValidatorContext().getFieldErrors().get("urlCollection").get(0));
    }
