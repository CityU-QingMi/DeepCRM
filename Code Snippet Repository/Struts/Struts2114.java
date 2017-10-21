    public void testNullArray() {
        Foo foo = new Foo();
        foo.setArray(null);

        stack.push(foo);

        tag.setValue("array");

        validateSkipBody();
    }
