    public void testSetBarAsString() {
        Foo foo = new Foo();

        OgnlValueStack vs = createValueStack();
        vs.push(foo);

        vs.setValue("bar", "bar:123");

        assertEquals("bar", foo.getBar().getTitle());
        assertEquals(123, foo.getBar().getSomethingElse());
    }
