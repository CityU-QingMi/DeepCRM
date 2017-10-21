    public void testCounterWithArrayAndNegativeStepNoEnd() throws JspException {
        Foo foo = new Foo();
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        foo.setList(list);

        stack.push(foo);

        tag.setValue("list");

        tag.setStep("-1");
        tag.setBegin("3");

        validateCounter(new String[]{"d", "c", "b", "a"});
    }
