    public void testAvoidCallingSomeClasses() throws Exception {
        Foo foo = new Foo();

        Exception expected = null;
        try {
            ognlUtil.setExcludedClasses(Runtime.class.getName());
            ognlUtil.setValue("@java.lang.Runtime@getRuntime().exec('mate')", ognlUtil.createDefaultContext(foo), foo, true);
            fail();
        } catch (OgnlException e) {
            expected = e;
        }
        assertNotNull(expected);
        assertSame(MethodFailedException.class, expected.getClass());
        assertEquals(expected.getMessage(), "Method \"getRuntime\" failed for object class java.lang.Runtime");
    }
