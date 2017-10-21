    public void testCounterWithArrayAndStepNoEnd() throws JspException {
        Foo foo = new Foo();
        ArrayList list = new ArrayList();
        foo.setArray(new String[]{"a", "b", "c", "d"});

        stack.push(foo);

        tag.setValue("array");

        tag.setStep("2");
        tag.setBegin("0");

        validateCounter(new String[]{"a", "c"});
    }
