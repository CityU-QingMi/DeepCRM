    public void testEdgeOfMinRange() throws Exception {
        MyTestProduct prod = new MyTestProduct();
        prod.setName("coca cola");
        prod.setPrice(9.95);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(prod);
        ActionContext.getContext().setValueStack(stack);

        val.setFieldName("price");

        DelegatingValidatorContext context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        val.setValidatorContext(context);

        val.setMinInclusive(9.95d);
        val.validate(prod); // should pass
        assertTrue(!context.hasErrors());

        val.setMinExclusive(9.95d);
        val.validate(prod); // should not pass
        assertTrue(context.hasErrors());
    }
