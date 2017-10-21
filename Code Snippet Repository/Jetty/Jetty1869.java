    @Test
    public void testMakeName()
    {
        // given
        beanName = "mngd:bean";

        // when
        beanName = mbeanContainer.makeName(beanName);

        // then
        Assert.assertEquals("Bean name should be mngd_bean", "mngd_bean", beanName);
    }
