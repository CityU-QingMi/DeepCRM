	@Test
	public void testSelectiveApplication() {
		TestBean target = new TestBean();
		target.setAge(27);
		NopInterceptor nop = new NopInterceptor();
		ControlFlowPointcut cflow = new ControlFlowPointcut(One.class);
		Pointcut settersUnderOne = Pointcuts.intersection(Pointcuts.SETTERS, cflow);
		ProxyFactory pf = new ProxyFactory(target);
		ITestBean proxied = (ITestBean) pf.getProxy();
		pf.addAdvisor(new DefaultPointcutAdvisor(settersUnderOne, nop));

		// Not advised, not under One
		target.setAge(16);
		assertEquals(0, nop.getCount());

		// Not advised; under One but not a setter
		assertEquals(16, new One().getAge(proxied));
		assertEquals(0, nop.getCount());

		// Won't be advised
		new One().set(proxied);
		assertEquals(1, nop.getCount());

		// We saved most evaluations
		assertEquals(1, cflow.getEvaluations());
	}
