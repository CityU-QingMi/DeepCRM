    public void testBlockSequenceOfExpressions() throws Exception {
        Foo foo = new Foo();

        Exception expected = null;
        try {
            ognlUtil.setValue("#booScope=@myclass@DEFAULT_SCOPE,#bootScope.init()", ognlUtil.createDefaultContext(foo), foo, true);
            fail();
        } catch (OgnlException e) {
            expected = e;
        }
        assertNotNull(expected);
        assertSame(OgnlException.class, expected.getClass());
        assertEquals(expected.getMessage(), "Eval expressions/chained expressions have been disabled!");
    }
