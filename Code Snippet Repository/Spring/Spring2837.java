	@Test
	public void testProxyProtectedMethod() throws Exception {
		CountingBeforeAdvice advice = new CountingBeforeAdvice();
		ProxyFactory proxyFactory = new ProxyFactory(new MyBean());
		proxyFactory.addAdvice(advice);
		proxyFactory.setProxyTargetClass(true);

		MyBean proxy = (MyBean) proxyFactory.getProxy();
		assertEquals(4, proxy.add(1, 3));
		assertEquals(1, advice.getCalls("add"));
	}
