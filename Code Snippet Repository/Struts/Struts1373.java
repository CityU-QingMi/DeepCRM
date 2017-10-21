    public void testSetNullMap() {
        Foo foo = new Foo();
        OgnlValueStack vs = createValueStack();
        vs.getContext().put(ReflectionContextState.CREATE_NULL_OBJECTS, Boolean.TRUE);
        vs.push(foo);

        vs.setValue("catMap['One'].name", "Cat One");
        vs.setValue("catMap['Two'].name", "Cat Two");

        assertNotNull(foo.getCatMap());
        assertEquals(2, foo.getCatMap().size());
        assertEquals("Cat One", ((Cat) foo.getCatMap().get("One")).getName());
        assertEquals("Cat Two", ((Cat) foo.getCatMap().get("Two")).getName());

        vs.setValue("catMap['One'].foo.catMap['Two'].name", "Deep null cat");
        assertNotNull(((Cat) foo.getCatMap().get("One")).getFoo());
        assertNotNull(((Cat) foo.getCatMap().get("One")).getFoo().getCatMap());
        assertNotNull(((Cat) foo.getCatMap().get("One")).getFoo().getCatMap().get("Two"));
        assertEquals("Deep null cat", ((Cat) ((Cat) foo.getCatMap().get("One")).getFoo().getCatMap().get("Two")).getName());
    }
