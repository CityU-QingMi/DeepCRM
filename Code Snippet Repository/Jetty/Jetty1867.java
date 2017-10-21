    @Test
    public void testStoppingContainerDoesNotUnregistersMBeans() throws Exception
    {
        QueuedThreadPool bean = new QueuedThreadPool();
        container.addBean(bean, true);

        String pkg = bean.getClass().getPackage().getName();
        Set<ObjectName> objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        Assert.assertEquals(1, objectNames.size());

        container.stop();

        objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        Assert.assertEquals(1, objectNames.size());

        // Remove the MBeans to start clean on the next test.
        objectNames.forEach(objectName ->
        {
            try
            {
                mbeanServer.unregisterMBean(objectName);
            }
            catch (Throwable ignored)
            {
            }
        });
    }
