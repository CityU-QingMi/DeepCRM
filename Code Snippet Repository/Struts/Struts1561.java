    public void testExpressionValidationOfStringLength() throws ValidationException {
        TestBean bean = new TestBean();
        bean.setName("abc");
        ActionContext.getContext().getValueStack().push(bean);

        DelegatingValidatorContext context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        container.getInstance(ActionValidatorManager.class).validate(bean, "expressionValidation", context);
        assertTrue(context.hasFieldErrors());

        final Map fieldErrors = context.getFieldErrors();
        assertTrue(fieldErrors.containsKey("name"));

        List nameErrors = (List) fieldErrors.get("name");
        assertEquals(1, nameErrors.size());
        assertEquals("Name must be greater than 5 characters, it is currently 'abc'", nameErrors.get(0));

        bean.setName("abcdefg");
        context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        container.getInstance(ActionValidatorManager.class).validate(bean, "expressionValidation", context);
        assertFalse(context.hasFieldErrors());
    }
