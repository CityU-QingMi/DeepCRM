	@Test
	public void testProxyTargetClassWithInterfaceAsTarget() {
		ProxyFactory pf = new ProxyFactory();
		pf.setTargetClass(ITestBean.class);
		Object proxy = pf.getProxy();
		assertTrue("Proxy is a JDK proxy", AopUtils.isJdkDynamicProxy(proxy));
		assertTrue(proxy instanceof ITestBean);
		assertEquals(ITestBean.class, AopProxyUtils.ultimateTargetClass(proxy));

		ProxyFactory pf2 = new ProxyFactory(proxy);
		Object proxy2 = pf2.getProxy();
		assertTrue("Proxy is a JDK proxy", AopUtils.isJdkDynamicProxy(proxy2));
		assertTrue(proxy2 instanceof ITestBean);
		assertEquals(ITestBean.class, AopProxyUtils.ultimateTargetClass(proxy2));
	}
