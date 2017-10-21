    @Test
    public void testCollectionObject() throws Exception {
        // given
        RequiredFieldValidator rfv = container.inject(RequiredFieldValidator.class);
        rfv.setValueStack(ActionContext.getContext().getValueStack());
        rfv.setFieldName("shorts");
        rfv.setDefaultMessage("${fieldName} field is required!");
        ValidationAction action = new ValidationAction();
        action.setShorts(new ArrayList<Short>());
        DummyValidatorContext context = new DummyValidatorContext(action, container.getInstance(TextProviderFactory.class));
        rfv.setValidatorContext(context);

        // when
        rfv.validate(action);

        // then
        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertNotNull(context.getFieldErrors().get("shorts"));
        assertEquals("shorts field is required!", context.getFieldErrors().get("shorts").get(0));
    }
