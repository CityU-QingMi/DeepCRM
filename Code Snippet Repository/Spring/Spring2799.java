	private void testManyProxies(int howMany) {
		int age1 = 33;
		TestBean target1 = new TestBean();
		target1.setAge(age1);
		ProxyFactory pf1 = new ProxyFactory(target1);
		pf1.addAdvice(new NopInterceptor());
		pf1.addAdvice(new NopInterceptor());
		ITestBean proxies[] = new ITestBean[howMany];
		for (int i = 0; i < howMany; i++) {
			proxies[i] = (ITestBean) createAopProxy(pf1).getProxy();
			assertEquals(age1, proxies[i].getAge());
		}
	}
