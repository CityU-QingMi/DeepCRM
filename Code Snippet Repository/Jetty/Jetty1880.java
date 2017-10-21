    @Test
    public void testDerivedObjectAttributes() throws Exception
    {
        Derived derived = new Derived();
        ObjectMBean mbean = (ObjectMBean)ObjectMBean.mbeanFor(derived);

        ObjectMBean managed = (ObjectMBean)ObjectMBean.mbeanFor(derived.getManagedInstance());
        mbean.setMBeanContainer(container);
        managed.setMBeanContainer(container);

        Assert.assertNotNull(mbean.getMBeanInfo());

        container.beanAdded(null,derived);
        container.beanAdded(null,derived.getManagedInstance());
        container.beanAdded(null,mbean);
        container.beanAdded(null,managed);

        // Managed managedInstance = (Managed)mbean.getAttribute("managedInstance");
        // Assert.assertNotNull(managedInstance);
        // Assert.assertEquals("managed instance returning nonsense", "foo", managedInstance.getManaged());

    }
