    @Test
    public void testDestroyInstanceNotFoundException() throws Exception
    {
        // given
        setUpDestroy();

        // when
        objectName = mbeanContainer.findMBean(managed);
        mbeanContainer.getMBeanServer().unregisterMBean(objectName);

        // then
        Assert.assertFalse("Unregistered bean - managed", mbeanContainer.getMBeanServer().isRegistered(objectName));
        // this flow covers InstanceNotFoundException. Actual code just eating
        // the exception. i.e Actual code just printing the stacktrace, whenever
        // an exception of type InstanceNotFoundException occurs.
        mbeanContainer.destroy();
    }
