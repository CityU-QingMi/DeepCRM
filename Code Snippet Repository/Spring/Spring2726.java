	@Test
	public void testProgrammaticProxyCreation() {
		ITestBean testBean = new TestBean();

		AspectJProxyFactory factory = new AspectJProxyFactory();
		factory.setTarget(testBean);

		CounterAspect myCounterAspect = new CounterAspect();
		factory.addAspect(myCounterAspect);

		ITestBean proxyTestBean = factory.getProxy();

		assertTrue("Expected a proxy", proxyTestBean instanceof Advised);
		proxyTestBean.setAge(20);
		assertEquals("Programmatically created proxy shouldn't match bean()", 0, myCounterAspect.count);
	}
