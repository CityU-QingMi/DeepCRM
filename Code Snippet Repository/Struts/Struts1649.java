    @Test
    public void testNullObject() throws Exception {
        // given
        RequiredFieldValidator rfv = container.inject(RequiredFieldValidator.class);
        rfv.setValueStack(ActionContext.getContext().getValueStack());
        rfv.setFieldName("stringValue");
        rfv.setDefaultMessage("${fieldName} field is required!");
        ValidationAction action = new ValidationAction();
        DummyValidatorContext context = new DummyValidatorContext(action, container.getInstance(TextProviderFactory.class));
        rfv.setValidatorContext(context);

        // when
        rfv.validate(action);

        // then
        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertNotNull(context.getFieldErrors().get("stringValue"));
        assertEquals("stringValue field is required!", context.getFieldErrors().get("stringValue").get(0));
    }
