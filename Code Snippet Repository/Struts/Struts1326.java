    public void testAvoidCallingMethodsOnObjectClass() throws Exception {
        Foo foo = new Foo();

        Exception expected = null;
        try {
            ognlUtil.setExcludedClasses(Object.class.getName());
            ognlUtil.setValue("class.classLoader.defaultAssertionStatus", ognlUtil.createDefaultContext(foo), foo, true);
            fail();
        } catch (OgnlException e) {
            expected = e;
        }
        assertNotNull(expected);
        assertSame(NoSuchPropertyException.class, expected.getClass());
        assertEquals("com.opensymphony.xwork2.util.Foo.class", expected.getMessage());
    }
