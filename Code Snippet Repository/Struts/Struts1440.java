    public void testUltimateTargetClass() throws Exception {
        Object simpleAction = appContext.getBean("simple-action");
        Class<?> simpleActionUltimateTargetClass = ProxyUtil.ultimateTargetClass(simpleAction);
        assertEquals(SimpleAction.class, simpleActionUltimateTargetClass);

        Object proxiedAction = appContext.getBean("proxied-action");
        Class<?> proxiedActionUltimateTargetClass = ProxyUtil.ultimateTargetClass(proxiedAction);
        assertEquals(SimpleAction.class, proxiedActionUltimateTargetClass);

        Object autoProxiedAction = appContext.getBean("auto-proxied-action");
        Class<?> autoProxiedActionUltimateTargetClass = ProxyUtil.ultimateTargetClass(autoProxiedAction);
        assertEquals(SimpleAction.class, autoProxiedActionUltimateTargetClass);

        Object pointcuttedTestBean = appContext.getBean("pointcutted-test-bean");
        Class<?> pointcuttedTestBeanUltimateTargetClass = ProxyUtil.ultimateTargetClass(pointcuttedTestBean);
        assertEquals(TestBean.class, pointcuttedTestBeanUltimateTargetClass);

        Object pointcuttedTestSubBean = appContext.getBean("pointcutted-test-sub-bean");
        Class<?> pointcuttedTestSubBeanUltimateTargetClass = ProxyUtil.ultimateTargetClass(pointcuttedTestSubBean);
        assertEquals(TestSubBean.class, pointcuttedTestSubBeanUltimateTargetClass);

        Object testAspect = appContext.getBean("test-aspect");
        Class<?> testAspectUltimateTargetClass = ProxyUtil.ultimateTargetClass(testAspect);
        assertEquals(TestAspect.class, testAspectUltimateTargetClass);
    }
