	@Test
	public void testProxyConfigString() {
		TestBean target = new TestBean();
		ProxyFactory pc = new ProxyFactory(target);
		pc.setInterfaces(ITestBean.class);
		pc.addAdvice(new NopInterceptor());
		MethodBeforeAdvice mba = new CountingBeforeAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(new NameMatchMethodPointcut(), mba);
		pc.addAdvisor(advisor);
		ITestBean proxied = (ITestBean) createProxy(pc);

		String proxyConfigString = ((Advised) proxied).toProxyConfigString();
		assertTrue(proxyConfigString.contains(advisor.toString()));
		assertTrue(proxyConfigString.contains("1 interface"));
	}
