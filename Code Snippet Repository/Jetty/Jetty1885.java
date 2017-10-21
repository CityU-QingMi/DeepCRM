    @Test(expected = MBeanException.class)
    public void testInvokeMBeanException() throws Exception
    {
        // given
        setMBeanInfoForInvoke();

        // when
        objectMBean.invoke("doodle2",new Object[] {},new String[] {});

        // then
        fail("An MBeanException must have occured by now as doodle2() in Derived bean throwing exception");
    }
