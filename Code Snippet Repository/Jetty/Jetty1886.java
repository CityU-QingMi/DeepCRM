    @Test(expected = ReflectionException.class)
    public void testInvokeReflectionException() throws Exception
    {
        // given
        setMBeanInfoForInvoke();

        // when
        objectMBean.invoke("doodle1",new Object[] {},new String[] {});

        // then
        fail("An ReflectionException must have occured by now as doodle1() has private access in Derived bean");
    }
