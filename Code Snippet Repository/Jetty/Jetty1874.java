    @Test
    public void testBeanAddedNullCheck() throws Exception
    {
        // given
        setBeanAdded();
        Integer mbeanCount = mbeanServer.getMBeanCount();

        // when
        mbeanContainer.beanAdded(null, null);

        // then
        Assert.assertEquals("MBean count must not change after beanAdded(null, null) call", mbeanCount, mbeanServer.getMBeanCount());
    }
