    @Test
    public void testFieldPublic()
    throws Exception
    {
        //direct
        Field f = IntrospectionUtil.findField(ServletA.class, "publicA", Integer.class, true, false);
        assertEquals(f, publicAField);

        //inheritance
        f = IntrospectionUtil.findField(ServletB.class, "publicA", Integer.class, true, false);
        assertEquals(f, publicAField);
    }
