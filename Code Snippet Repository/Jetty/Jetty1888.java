    @Test(expected = ReflectionException.class)
    public void testInvokeNoSuchMethodException() throws Exception
    {
        // given
        setMBeanInfoForInvoke();

        // when
        // DerivedMBean contains a managed method with the name good,we must
        // call this method without any arguments
        objectMBean.invoke("good",new Object[] {},new String[]
        { "int aone" });

        // then
        fail("An ReflectionException must have occured by now as we cannot call a methow with wrong signature");
    }
