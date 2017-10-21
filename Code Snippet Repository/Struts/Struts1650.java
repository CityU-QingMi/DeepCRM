    @Test
    public void testArrayObject() throws Exception {
        // given
        RequiredFieldValidator rfv = container.inject(RequiredFieldValidator.class);
        rfv.setValueStack(ActionContext.getContext().getValueStack());
        rfv.setFieldName("ints");
        rfv.setDefaultMessage("${fieldName} field is required!");
        ValidationAction action = new ValidationAction();
        action.setInts(new Integer[]{});
        DummyValidatorContext context = new DummyValidatorContext(action, container.getInstance(TextProviderFactory.class));
        rfv.setValidatorContext(context);

        // when
        rfv.validate(action);

        // then
        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertNotNull(context.getFieldErrors().get("ints"));
        assertEquals("ints field is required!", context.getFieldErrors().get("ints").get(0));
    }
