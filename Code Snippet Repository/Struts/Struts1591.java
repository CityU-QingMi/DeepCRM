    public void testRequiredString() throws Exception {
        Equidae equidae = new Equidae();

        // everything should fail
        equidae.setHorse("");
        ActionContext.getContext().getValueStack().push(equidae);

        DelegatingValidatorContext context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        container.getInstance(ActionValidatorManager.class).validate(equidae, null, context);

        assertTrue(context.hasFieldErrors());

        Map fieldErrors = context.getFieldErrors();
        assertTrue(fieldErrors.containsKey("horse"));
        assertEquals(2, ((List) fieldErrors.get("horse")).size());

        // trim = false should fail
        equidae.setHorse("  ");
        ActionContext.getContext().getValueStack().push(equidae);
        context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        container.getInstance(ActionValidatorManager.class).validate(equidae, null, context);

        assertTrue(context.hasFieldErrors());
        fieldErrors = context.getFieldErrors();
        assertTrue(fieldErrors.containsKey("horse"));

        List errors = (List) fieldErrors.get("horse");
        assertEquals(1, errors.size());
        assertEquals("trim", (String) errors.get(0));
    }
