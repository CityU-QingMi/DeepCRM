    @Test
    public void testAddBeanRegistersMBeanRemoveBeanUnregistersMBean() throws Exception
    {
        // Adding a bean to the container should register the MBean.
        QueuedThreadPool bean = new QueuedThreadPool();
        container.addBean(bean);

        String pkg = bean.getClass().getPackage().getName();
        Set<ObjectName> objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        Assert.assertEquals(1, objectNames.size());

        // Removing the bean should unregister the MBean.
        container.removeBean(bean);
        objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        Assert.assertEquals(0, objectNames.size());
    }
