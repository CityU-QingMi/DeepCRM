    public void testSetPropertiesBoolean() {
        Foo foo = new Foo();

        Map context = ognlUtil.createDefaultContext(foo);

        Map props = new HashMap();
        props.put("useful", "true");
        ognlUtil.setProperties(props, foo, context);

        assertEquals(true, foo.isUseful());

        props = new HashMap();
        props.put("useful", "false");
        ognlUtil.setProperties(props, foo, context);

        assertEquals(false, foo.isUseful());
    }
