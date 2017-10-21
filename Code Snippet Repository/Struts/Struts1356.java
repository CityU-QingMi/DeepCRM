    public void testGetBarAsString() {
        Foo foo = new Foo();
        Bar bar = new Bar();
        bar.setTitle("bar");
        bar.setSomethingElse(123);
        foo.setBar(bar);

        OgnlValueStack vs = createValueStack();
        vs.push(foo);

        String output = (String) vs.findValue("bar", String.class);
        assertEquals("bar:123", output);
    }
