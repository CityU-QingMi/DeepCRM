    public void testConvertStringArrayToList() {
        Foo foo = new Foo();
        OgnlValueStack vs = createValueStack();
        vs.push(foo);

        vs.setValue("strings", new String[]{"one", "two"});

        assertNotNull(foo.getStrings());
        assertEquals("one", foo.getStrings().get(0));
        assertEquals("two", foo.getStrings().get(1));
    }
