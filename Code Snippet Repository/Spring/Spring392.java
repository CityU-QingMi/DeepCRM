	@Test
	public void testMatches() {
		TestBean target = new TestBean();
		target.setAge(27);
		NopInterceptor nop = new NopInterceptor();
		ControlFlowPointcut cflow = new ControlFlowPointcut(One.class, "getAge");
		ProxyFactory pf = new ProxyFactory(target);
		ITestBean proxied = (ITestBean) pf.getProxy();
		pf.addAdvisor(new DefaultPointcutAdvisor(cflow, nop));

		// Not advised, not under One
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(0, nop.getCount());

		// Will be advised
		assertEquals(target.getAge(), new One().getAge(proxied));
		assertEquals(1, nop.getCount());

		// Won't be advised
		assertEquals(target.getAge(), new One().nomatch(proxied));
		assertEquals(1, nop.getCount());
		assertEquals(3, cflow.getEvaluations());
	}
