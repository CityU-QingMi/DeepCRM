    public void testRangeNotADoubleObjectValueInStack() throws Exception {
        MyTestProduct prod = new MyTestProduct();
        prod.setName("coca cola");

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(prod);
        ActionContext.getContext().setValueStack(stack);

        val.setMinInclusive(0d);
        val.setMaxInclusive(10d);
        val.setFieldName("name");

        DelegatingValidatorContext context = new DelegatingValidatorContext(new ValidationAwareSupport(), tpf);
        val.setValidatorContext(context);

        val.validate(prod);

        assertEquals(0d, val.getMinInclusive());
        assertEquals(10d, val.getMaxInclusive());
    }
