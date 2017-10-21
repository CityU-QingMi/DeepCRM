    public void testIsProxyMember() throws Exception {
        Object simpleAction = appContext.getBean("simple-action");
        assertFalse(ProxyUtil.isProxyMember(
                simpleAction.getClass().getMethod("setName", String.class), simpleAction));

        Object proxiedAction = appContext.getBean("proxied-action");
        assertTrue(ProxyUtil.isProxyMember(
                proxiedAction.getClass().getMethod("setExposeProxy", boolean.class), proxiedAction));

        Object autoProxiedAction = appContext.getBean("auto-proxied-action");
        assertTrue(ProxyUtil.isProxyMember(
                autoProxiedAction.getClass().getMethod("getTargetClass"), autoProxiedAction));

        Object pointcuttedTestBean = appContext.getBean("pointcutted-test-bean");
        assertTrue(ProxyUtil.isProxyMember(
                pointcuttedTestBean.getClass().getMethod("getTargetSource"), pointcuttedTestBean));

        Object pointcuttedTestSubBean = appContext.getBean("pointcutted-test-sub-bean");
        assertFalse(ProxyUtil.isProxyMember(
                pointcuttedTestSubBean.getClass().getConstructor(), pointcuttedTestSubBean));

        Object testAspect = appContext.getBean("test-aspect");
        assertFalse(ProxyUtil.isProxyMember(
                testAspect.getClass().getMethod("setExposeProxy", boolean.class), testAspect));
    }
