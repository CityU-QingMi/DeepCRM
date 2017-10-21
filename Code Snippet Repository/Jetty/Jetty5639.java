    @Test
    public void testMethodDefault ()
    throws Exception
    {
        // direct
        Method m = IntrospectionUtil.findMethod(ServletC.class, "setDefaultC", __INTEGER_ARG, true, false);
        assertEquals(m, defaultCMethod);

        //inherited
        m = IntrospectionUtil.findMethod(ServletD.class, "setDefaultC", __INTEGER_ARG, true, false);
        assertEquals(m, defaultCMethod);
    }
