     public void testCounterWithArrayNoEnd2() throws JspException {
        Foo foo = new Foo();
        ArrayList list = new ArrayList();
        foo.setArray(new String[]{"a", "b", "c", "d"});

        stack.push(foo);

        tag.setValue("array");

        tag.setBegin("2");
        validateCounter(new String[]{"c", "d"});
    }
