    public void testNullProperties() {
        Foo foo = new Foo();
        foo.setALong(88);

        Map context = ognlUtil.createDefaultContext(foo);

        ognlUtil.setProperties(null, foo, context);
        assertEquals(88, foo.getALong());

        Map props = new HashMap();
        props.put("ALong", "99");
        ognlUtil.setProperties(props, foo, context);
        assertEquals(99, foo.getALong());
    }
