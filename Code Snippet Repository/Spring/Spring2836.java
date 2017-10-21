	@Test
	public void testAddAdviceAtRuntime() {
		TestBean bean = new TestBean();
		CountingBeforeAdvice cba = new CountingBeforeAdvice();

		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(bean);
		pf.setFrozen(false);
		pf.setOpaque(false);
		pf.setProxyTargetClass(true);

		TestBean proxy = (TestBean) pf.getProxy();
		assertTrue(AopUtils.isCglibProxy(proxy));

		proxy.getAge();
		assertEquals(0, cba.getCalls());

		((Advised) proxy).addAdvice(cba);
		proxy.getAge();
		assertEquals(1, cba.getCalls());
	}
