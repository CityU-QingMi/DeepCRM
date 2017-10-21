    public void testSetPropertiesString() {
        Foo foo = new Foo();

        Map context = ognlUtil.createDefaultContext(foo);

        Map props = new HashMap();
        props.put("title", "this is a title");
        ognlUtil.setProperties(props, foo, context);

        assertEquals(foo.getTitle(), "this is a title");
    }
