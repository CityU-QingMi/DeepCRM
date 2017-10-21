	@Test
	public void testProxyTargetClassWithConcreteClassAsTarget() {
		ProxyFactory pf = new ProxyFactory();
		pf.setTargetClass(TestBean.class);
		Object proxy = pf.getProxy();
		assertTrue("Proxy is a CGLIB proxy", AopUtils.isCglibProxy(proxy));
		assertTrue(proxy instanceof TestBean);
		assertEquals(TestBean.class, AopProxyUtils.ultimateTargetClass(proxy));

		ProxyFactory pf2 = new ProxyFactory(proxy);
		pf2.setProxyTargetClass(true);
		Object proxy2 = pf2.getProxy();
		assertTrue("Proxy is a CGLIB proxy", AopUtils.isCglibProxy(proxy2));
		assertTrue(proxy2 instanceof TestBean);
		assertEquals(TestBean.class, AopProxyUtils.ultimateTargetClass(proxy2));
	}
