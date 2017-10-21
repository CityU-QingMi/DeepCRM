    public void testEmptyCollection() {
        Foo foo = new Foo();
        foo.setList(new ArrayList());

        stack.push(foo);

        tag.setValue("list");

        validateSkipBody();
    }
