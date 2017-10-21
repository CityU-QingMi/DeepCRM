    public void testCounterWithArray() throws JspException {
        Foo foo = new Foo();
        ArrayList list = new ArrayList();
        foo.setArray(new String[]{"a", "b", "c", "d"});

        stack.push(foo);

        tag.setValue("array");

        tag.setBegin("0");
        tag.setEnd("2");
        validateCounter(new String[]{"a", "b", "c"});
    }
