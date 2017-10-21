    @Test
    public void testFieldPrivate ()
    throws Exception
    {
        //direct
        Field f = IntrospectionUtil.findField(ServletA.class, "privateA", Integer.class, true, false);
        assertEquals(privateAField,f);

        //inheritance
        try
        {
            IntrospectionUtil.findField(ServletB.class, "privateA", Integer.class, true, false);
            fail("Private fields should not be inherited");
        }
        catch (NoSuchFieldException e)
        {
            //expected
        }
    }
