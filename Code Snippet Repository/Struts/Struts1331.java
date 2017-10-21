    public void testAvoidCallingToString() throws Exception {
        Foo foo = new Foo();

        Exception expected = null;
        try {
            ognlUtil.setValue("toString", ognlUtil.createDefaultContext(foo), foo, null);
            fail();
        } catch (OgnlException e) {
            expected = e;
        }
        assertNotNull(expected);
        assertSame(OgnlException.class, expected.getClass());
        assertEquals("toString", expected.getMessage());
    }
