    public void testPetSoarBug() {
        Cat cat = new Cat();
        cat.setFoo(new Foo());

        Bar bar = new Bar();
        bar.setTitle("bar");
        bar.setSomethingElse(123);
        cat.getFoo().setBar(bar);

        OgnlValueStack vs = createValueStack();
        vs.push(cat);

        assertEquals("bar:123", vs.findValue("foo.bar", String.class));
    }
