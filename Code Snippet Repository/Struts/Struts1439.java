    public void testIsProxy() throws Exception {
        Object simpleAction = appContext.getBean("simple-action");
        assertFalse(ProxyUtil.isProxy(simpleAction));

        Object proxiedAction = appContext.getBean("proxied-action");
        assertTrue(ProxyUtil.isProxy(proxiedAction));

        Object autoProxiedAction = appContext.getBean("auto-proxied-action");
        assertTrue(ProxyUtil.isProxy(autoProxiedAction));

        Object pointcuttedTestBean = appContext.getBean("pointcutted-test-bean");
        assertTrue(ProxyUtil.isProxy(pointcuttedTestBean));

        Object pointcuttedTestSubBean = appContext.getBean("pointcutted-test-sub-bean");
        assertTrue(ProxyUtil.isProxy(pointcuttedTestSubBean));

        Object testAspect = appContext.getBean("test-aspect");
        assertFalse(ProxyUtil.isProxy(testAspect));
    }
