    @Test
    public void testMethodProtected ()
    throws Exception
    {
        // direct
        Method m = IntrospectionUtil.findMethod(ServletC.class, "setProtectedC", __INTEGER_ARG, true, false);
        assertEquals(m, protectedCMethod);

        //inherited
        m = IntrospectionUtil.findMethod(ServletD.class, "setProtectedC", __INTEGER_ARG, true, false);
        assertEquals(m, protectedCMethod);
    }
