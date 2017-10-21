    @Test
    public void testDestroyingContainerUnregistersMBeans() throws Exception
    {
        QueuedThreadPool bean = new QueuedThreadPool();
        container.addBean(bean, true);

        String pkg = bean.getClass().getPackage().getName();
        Set<ObjectName> objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        Assert.assertEquals(1, objectNames.size());

        container.stop();
        container.destroy();

        objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        Assert.assertEquals(0, objectNames.size());
    }
