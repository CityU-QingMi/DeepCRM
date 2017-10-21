    public void testCounterWithList2() throws JspException {
        Foo foo = new Foo();
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        foo.setList(list);

        stack.push(foo);

        tag.setValue("list");

        tag.setBegin("1");
        tag.setEnd("2");
        validateCounter(new String[]{"b", "c"});
    }
