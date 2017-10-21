	@Test
	public void timeManyInvocations() {
		StopWatch sw = new StopWatch();

		TestBean target = new TestBean();
		ProxyFactory pf = new ProxyFactory(target);
		pf.setProxyTargetClass(false);
		pf.addAdvice(new SimpleCounterIntroduction());
		ITestBean proxy = (ITestBean) pf.getProxy();

		Counter counter = (Counter) proxy;

		sw.start(INVOCATIONS + " invocations on proxy, not hitting introduction");
		for (int i = 0; i < INVOCATIONS; i++) {
			proxy.getAge();
		}
		sw.stop();

		sw.start(INVOCATIONS + " invocations on proxy, hitting introduction");
		for (int i = 0; i < INVOCATIONS; i++) {
			counter.getCount();
		}
		sw.stop();

		sw.start(INVOCATIONS + " invocations on target");
		for (int i = 0; i < INVOCATIONS; i++) {
			target.getAge();
		}
		sw.stop();

		System.out.println(sw.prettyPrint());
	}
