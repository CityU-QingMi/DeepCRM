    public void testSetNullList() {
        Foo foo = new Foo();
        OgnlValueStack vs = createValueStack();
        vs.getContext().put(ReflectionContextState.CREATE_NULL_OBJECTS, Boolean.TRUE);
        vs.push(foo);

        vs.setValue("cats[0].name", "Cat One");
        vs.setValue("cats[1].name", "Cat Two");

        assertNotNull(foo.getCats());
        assertEquals(2, foo.getCats().size());
        assertEquals("Cat One", ((Cat) foo.getCats().get(0)).getName());
        assertEquals("Cat Two", ((Cat) foo.getCats().get(1)).getName());

        vs.setValue("cats[0].foo.cats[1].name", "Deep null cat");
        assertNotNull(((Cat) foo.getCats().get(0)).getFoo());
        assertNotNull(((Cat) foo.getCats().get(0)).getFoo().getCats());
        assertNotNull(((Cat) foo.getCats().get(0)).getFoo().getCats().get(1));
        assertEquals("Deep null cat", ((Cat) ((Cat) foo.getCats().get(0)).getFoo().getCats().get(1)).getName());
    }
