    public void testSetPropertiesInt() {
        Foo foo = new Foo();

        Map context = ognlUtil.createDefaultContext(foo);

        Map props = new HashMap();
        props.put("number", "2");
        ognlUtil.setProperties(props, foo, context);

        assertEquals(2, foo.getNumber());
    }
