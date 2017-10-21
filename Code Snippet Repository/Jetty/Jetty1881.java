    @Test
    @Ignore("")
    public void testThreadPool() throws Exception
    {

        Derived derived = new Derived();
        ObjectMBean mbean = (ObjectMBean)ObjectMBean.mbeanFor(derived);

        ObjectMBean managed = (ObjectMBean)ObjectMBean.mbeanFor(derived.getManagedInstance());
        mbean.setMBeanContainer(container);
        managed.setMBeanContainer(container);

        QueuedThreadPool qtp = new QueuedThreadPool();

        ObjectMBean bqtp = (ObjectMBean)ObjectMBean.mbeanFor(qtp);

        bqtp.getMBeanInfo();

        container.beanAdded(null,derived);
        container.beanAdded(null,derived.getManagedInstance());
        container.beanAdded(null,mbean);
        container.beanAdded(null,managed);
        container.beanAdded(null,qtp);

        Thread.sleep(10000000);

    }
