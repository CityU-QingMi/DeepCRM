	@Test
	public void testStaticMethodPointcut() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory();
		pc.addInterface(ITestBean.class);
		NopInterceptor di = new NopInterceptor();
		TestStaticPointcutAdvice sp = new TestStaticPointcutAdvice(di, "getAge");
		pc.addAdvisor(sp);
		pc.setTarget(tb);
		ITestBean it = (ITestBean) createProxy(pc);
		assertEquals(di.getCount(), 0);
		it.getAge();
		assertEquals(di.getCount(), 1);
		it.setAge(11);
		assertEquals(it.getAge(), 11);
		assertEquals(di.getCount(), 2);
	}
