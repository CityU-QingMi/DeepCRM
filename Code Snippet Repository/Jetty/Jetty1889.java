    @Test(expected = ReflectionException.class)
    public void testGetAttributeReflectionException() throws Exception
    {
        // given
        setUpGetAttribute("doodle4","charu");

        // when
        objectMBean.getAttribute("doodle4");

        // then
        fail("An InvocationTargetException must have occured by now as doodle4() internally throwing exception");
    }
