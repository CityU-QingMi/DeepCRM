	@Test
	public void testDynamicMethodPointcutThatAlwaysAppliesStatically() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory();
		pc.addInterface(ITestBean.class);
		TestDynamicPointcutAdvice dp = new TestDynamicPointcutAdvice(new NopInterceptor(), "getAge");
		pc.addAdvisor(dp);
		pc.setTarget(tb);
		ITestBean it = (ITestBean) createProxy(pc);
		assertEquals(dp.count, 0);
		it.getAge();
		assertEquals(dp.count, 1);
		it.setAge(11);
		assertEquals(it.getAge(), 11);
		assertEquals(dp.count, 2);
	}
