    public void testSetReallyDeepBarAsString() {
        Foo foo = new Foo();
        Foo foo2 = new Foo();
        foo.setChild(foo2);

        Foo foo3 = new Foo();
        foo2.setChild(foo3);

        OgnlValueStack vs = createValueStack();
        vs.push(foo);

        vs.setValue("child.child.bar", "bar:123");

        assertEquals("bar", foo.getChild().getChild().getBar().getTitle());
        assertEquals(123, foo.getChild().getChild().getBar().getSomethingElse());
    }
