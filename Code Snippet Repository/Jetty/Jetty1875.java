    @Test
    public void testBeanRemoved() throws Exception
    {
        // given
        setUpBeanRemoved();

        // when
        mbeanContainer.beanRemoved(null, managed);

        // then
        Assert.assertNull("Bean shouldn't be registered with container as we removed the bean", mbeanContainer.findMBean(managed));
    }
