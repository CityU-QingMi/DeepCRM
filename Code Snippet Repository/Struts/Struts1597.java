    public void testCollectionOfUrlsSafness() throws Exception {
        URLValidator validator = new URLValidator();
        validator.setValidatorContext(new DummyValidatorContext(new Object(), tpf));
        validator.setFieldName("urlSafeness");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.setDefaultMessage("Wrong URL provided: ${currentValue}");
        validator.validate(new MyObject());

        assertTrue(validator.getValidatorContext().hasErrors());
        assertFalse(validator.getValidatorContext().hasActionErrors());
        assertFalse(validator.getValidatorContext().hasActionMessages());
        assertTrue(validator.getValidatorContext().hasFieldErrors());
        assertEquals(2, validator.getValidatorContext().getFieldErrors().get("urlSafeness").size());
        assertEquals("Wrong URL provided: ${1+2}", validator.getValidatorContext().getFieldErrors().get("urlSafeness").get(0));
        assertEquals("Wrong URL provided: %{2+3}", validator.getValidatorContext().getFieldErrors().get("urlSafeness").get(1));
    }
