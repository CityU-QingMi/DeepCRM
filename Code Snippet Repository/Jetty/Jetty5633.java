    @Test
    public void testFieldProtected()
    throws Exception
    {
        //direct
        Field f = IntrospectionUtil.findField(ServletA.class, "protectedA", Integer.class, true, false);
        assertEquals(f, protectedAField);

        //inheritance
        f = IntrospectionUtil.findField(ServletB.class, "protectedA", Integer.class, true, false);
        assertEquals(f, protectedAField);
    }
