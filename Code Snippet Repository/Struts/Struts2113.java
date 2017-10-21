    public void testEmptyArray() {
        Foo foo = new Foo();
        foo.setArray(new String[]{});

        stack.push(foo);

        tag.setValue("array");

        validateSkipBody();
    }
