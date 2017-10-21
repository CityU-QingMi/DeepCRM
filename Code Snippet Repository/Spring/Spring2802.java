	@Test
	public void testCanPreventCastToAdvisedUsingOpaque() {
		TestBean target = new TestBean();
		ProxyFactory pc = new ProxyFactory(target);
		pc.setInterfaces(ITestBean.class);
		pc.addAdvice(new NopInterceptor());
		CountingBeforeAdvice mba = new CountingBeforeAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(new NameMatchMethodPointcut().addMethodName("setAge"), mba);
		pc.addAdvisor(advisor);
		assertFalse("Opaque defaults to false", pc.isOpaque());
		pc.setOpaque(true);
		assertTrue("Opaque now true for this config", pc.isOpaque());
		ITestBean proxied = (ITestBean) createProxy(pc);
		proxied.setAge(10);
		assertEquals(10, proxied.getAge());
		assertEquals(1, mba.getCalls());

		assertFalse("Cannot be cast to Advised", proxied instanceof Advised);
	}
