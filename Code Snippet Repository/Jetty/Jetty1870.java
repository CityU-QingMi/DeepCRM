    private void testNonManagedObjectNotUnregistered(Object lifeCycle) throws Exception
    {
        ContainerLifeCycle parent = new ContainerLifeCycle();
        parent.addBean(mbeanContainer);

        ContainerLifeCycle child = new ContainerLifeCycle();
        parent.addBean(child);

        parent.addBean(lifeCycle, true);
        child.addBean(lifeCycle, false);

        parent.start();

        parent.removeBean(child);

        Assert.assertNotNull(mbeanContainer.findMBean(lifeCycle));
    }
