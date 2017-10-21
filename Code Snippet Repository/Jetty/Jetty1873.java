    @Test
    public void testBeanAdded() throws Exception
    {
        // given
        setBeanAdded();

        // when
        objectName = mbeanContainer.findMBean(managed);

        // then
        Assert.assertTrue("Bean must have been registered", mbeanServer.isRegistered(objectName));
    }
