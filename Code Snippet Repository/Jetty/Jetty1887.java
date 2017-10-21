    @Test
    public void testInvoke() throws Exception
    {
        // given
        setMBeanInfoForInvoke();

        // when
        value = (String)objectMBean.invoke("good",new Object[] {},new String[] {});

        // then
        assertEquals("Method(good) invocation on objectMBean must return not bad","not bad",value);
    }
