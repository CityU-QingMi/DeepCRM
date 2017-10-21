    public void testEdgeOfMaxRange() throws Exception {
        MyTestProduct prod = new MyTestProduct();
        prod.setName("coca cola");
        prod.setPrice(9.95);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(prod);
        ActionContext.getContext().setValueStack(stack);

        val.setFieldName("price");

        DelegatingValidatorContext context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        val.setValidatorContext(context);

        val.setMaxInclusive(9.95d);
        val.validate(prod); // should pass
        assertTrue(!context.hasErrors());
        assertEquals(9.95d, val.getMaxInclusive());

        val.setMaxExclusive(9.95d);
        val.validate(prod); // should not pass
        assertTrue(context.hasErrors());
        assertEquals(9.95d, val.getMaxExclusive());
    }
