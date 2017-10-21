    public void testCallMethod() throws Exception {
        Foo foo = new Foo();

        Exception expected = null;
        try {
            ognlUtil.callMethod("#booScope=@myclass@DEFAULT_SCOPE,#bootScope.init()", ognlUtil.createDefaultContext(foo), foo);
            fail();
        } catch (OgnlException e) {
            expected = e;
        }
        assertNotNull(expected);
        assertSame(OgnlException.class, expected.getClass());
        assertEquals(expected.getMessage(), "It isn't a simple method which can be called!");
    }
