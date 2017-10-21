    public void testRangeSimpleDoubleValueInStack() throws Exception {
        MyTestProduct prod = new MyTestProduct();
        prod.setName("coca cola");
        prod.setPrice(5.99);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(prod);
        ActionContext.getContext().setValueStack(stack);

        val.setMinInclusive(0d);
        val.setMaxInclusive(10d);
        val.setFieldName("price");
        val.validate(prod);
    }
