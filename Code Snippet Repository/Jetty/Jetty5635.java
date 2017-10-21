    @Test
    public void testFieldDefault()
    throws Exception
    {
        //direct
        Field f = IntrospectionUtil.findField(ServletA.class, "defaultA", Integer.class, true, false);
        assertEquals(f, defaultAField);

        //inheritance
        f = IntrospectionUtil.findField(ServletB.class, "defaultA", Integer.class, true, false);
        assertEquals(f, defaultAField);
    }
