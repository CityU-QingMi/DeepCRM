    public void testCounterWithListAndStepNoEnd() throws JspException {
        Foo foo = new Foo();
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        foo.setList(list);

        stack.push(foo);

        tag.setValue("list");

        tag.setStep("2");
        tag.setBegin("0");

        validateCounter(new String[]{"a", "c"});
    }
