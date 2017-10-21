    public void doGetCollectionPropertiesTest(Collection c) {
        ValueStack vs = ActionContext.getContext().getValueStack();
        Foo foo = new Foo();
        foo.setBarCollection(c);
        vs.push(foo);
        assertEquals(Boolean.TRUE, vs.findValue("barCollection.isEmpty"));
        assertEquals(Boolean.TRUE, vs.findValue("barCollection.empty"));
        assertEquals(new Integer(0), vs.findValue("barCollection.size"));
        assertTrue(vs.findValue("barCollection.iterator") instanceof java.util.Iterator);
    }
