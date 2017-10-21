    public void testNullCollection() {
        Foo foo = new Foo();
        foo.setList(null);

        stack.push(foo);

        tag.setValue("list");

        validateSkipBody();
    }
