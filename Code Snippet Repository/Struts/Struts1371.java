    public void testSetDeepBarAsString() {
        Foo foo = new Foo();
        Foo foo2 = new Foo();
        foo.setChild(foo2);

        OgnlValueStack vs = createValueStack();
        vs.push(foo);

        vs.setValue("child.bar", "bar:123");

        assertEquals("bar", foo.getChild().getBar().getTitle());
        assertEquals(123, foo.getChild().getBar().getSomethingElse());
    }
