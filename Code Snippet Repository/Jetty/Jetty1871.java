    @Test
    public void testFindBean()
    {
        // given
        managed = getManaged();

        // when
        objectName = mbeanContainer.findMBean(managed);
        Assert.assertNotNull(objectName);

        // then
        Assert.assertEquals("Bean must be added", managed, mbeanContainer.findBean(objectName));
        Assert.assertNull("It must return null as there is no bean with the name null", mbeanContainer.findBean(null));
    }
