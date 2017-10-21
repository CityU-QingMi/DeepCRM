    @Test
    public void testDerivedAttributes() throws Exception
    {
        Derived derived = new Derived();
        ObjectMBean mbean = (ObjectMBean)ObjectMBean.mbeanFor(derived);

        ObjectMBean managed = (ObjectMBean)ObjectMBean.mbeanFor(derived.getManagedInstance());
        mbean.setMBeanContainer(container);
        managed.setMBeanContainer(container);

        container.beanAdded(null,derived);
        container.beanAdded(null,derived.getManagedInstance());

        MBeanInfo toss = managed.getMBeanInfo();

        Assert.assertNotNull(mbean.getMBeanInfo());

        MBeanInfo info = mbean.getMBeanInfo();

        Assert.assertEquals("name does not match","com.acme.Derived",info.getClassName());
        Assert.assertEquals("description does not match","Test the mbean stuff",info.getDescription());

        // for ( MBeanAttributeInfo i : info.getAttributes())
        // {
        // LOG.debug(i.toString());
        // }

/**/
/**/
/**/
        Assert.assertEquals("attribute count does not match",6,info.getAttributes().length);

        Assert.assertEquals("attribute values does not match","Full Name",mbean.getAttribute("fname"));

        mbean.setAttribute(new Attribute("fname","Fuller Name"));

        Assert.assertEquals("set attribute value does not match","Fuller Name",mbean.getAttribute("fname"));

        Assert.assertEquals("proxy attribute values do not match","goop",mbean.getAttribute("goop"));

        // Thread.sleep(100000);
    }
