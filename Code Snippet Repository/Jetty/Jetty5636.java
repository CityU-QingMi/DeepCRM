    @Test
    public void testMethodPrivate ()
    throws Exception
    {
        //direct
        Method m = IntrospectionUtil.findMethod(ServletC.class, "setPrivateC", __INTEGER_ARG, true, false);
        assertEquals(m, privateCMethod);

        //inheritance
        try
        {
            IntrospectionUtil.findMethod(ServletD.class, "setPrivateC", __INTEGER_ARG, true, false);
            fail();
        }
        catch (NoSuchMethodException e)
        {
            //expected
        }
    }
