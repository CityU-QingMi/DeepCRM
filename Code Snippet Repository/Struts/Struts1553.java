    public void testNoValue() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        ActionContext.getContext().setValueStack(stack);

        val.setFieldName("price");

        DelegatingValidatorContext context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        val.setValidatorContext(context);

        val.setMinInclusive(9.95d);
        val.validate(null);
        assertFalse(context.hasErrors()); // should pass as null value passed in
    }
