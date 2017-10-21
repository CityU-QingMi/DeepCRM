    @Test
    public void testDestroy() throws Exception
    {
        // given
        setUpDestroy();

        // when
        objectName = mbeanContainer.findMBean(managed);
        mbeanContainer.destroy();

        // then
        Assert.assertFalse("Unregistered bean - managed", mbeanContainer.getMBeanServer().isRegistered(objectName));
    }
