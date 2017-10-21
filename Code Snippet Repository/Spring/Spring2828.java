	@Test
	public void testTargetCanGetProxy() {
		NopInterceptor di = new NopInterceptor();
		INeedsToSeeProxy target = new TargetChecker();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setExposeProxy(true);
		assertTrue(proxyFactory.isExposeProxy());

		proxyFactory.addAdvice(0, di);
		INeedsToSeeProxy proxied = (INeedsToSeeProxy) createProxy(proxyFactory);
		assertEquals(0, di.getCount());
		assertEquals(0, target.getCount());
		proxied.incrementViaThis();
		assertEquals("Increment happened", 1, target.getCount());

		assertEquals("Only one invocation via AOP as use of this wasn't proxied", 1, di.getCount());
		// 1 invocation
		assertEquals("Increment happened", 1, proxied.getCount());
		proxied.incrementViaProxy(); // 2 invoocations
		assertEquals("Increment happened", 2, target.getCount());
		assertEquals("3 more invocations via AOP as the first call was reentrant through the proxy", 4, di.getCount());
	}
