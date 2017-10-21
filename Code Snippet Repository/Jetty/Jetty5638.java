    @Test
    public void testMethodPublic ()
    throws Exception
    {
        // direct
        Method m = IntrospectionUtil.findMethod(ServletC.class, "setPublicC",  __INTEGER_ARG, true, false);
        assertEquals(m, publicCMethod);

        //inherited
       m = IntrospectionUtil.findMethod(ServletD.class, "setPublicC",  __INTEGER_ARG, true, false);
       assertEquals(m, publicCMethod);
    }
