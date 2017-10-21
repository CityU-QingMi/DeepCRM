    public void testRangeRealDoubleValueInStack() throws Exception {
        MyTestProduct prod = new MyTestProduct();
        prod.setName("coca cola");
        prod.setPrice(5.99);
        prod.setVolume(12.34d);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(prod);
        ActionContext.getContext().setValueStack(stack);

        val.setMinInclusive(0d);
        val.setMaxInclusive(30d);
        val.setFieldName("volume");
        val.validate(prod);
    }
