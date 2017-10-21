    public void testCounterWithListNoEnd() throws JspException {
        Foo foo = new Foo();
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        foo.setList(list);

        stack.push(foo);

        tag.setValue("list");

        tag.setBegin("0");
        validateCounter(new String[]{"a", "b", "c", "d"});
    }
