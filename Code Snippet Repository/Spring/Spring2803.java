	@Test
	public void testAdviceSupportListeners() throws Throwable {
		TestBean target = new TestBean();
		target.setAge(21);

		ProxyFactory pc = new ProxyFactory(target);
		CountingAdvisorListener l = new CountingAdvisorListener(pc);
		pc.addListener(l);
		RefreshCountingAdvisorChainFactory acf = new RefreshCountingAdvisorChainFactory();
		// Should be automatically added as a listener
		pc.addListener(acf);
		assertFalse(pc.isActive());
		assertEquals(0, l.activates);
		assertEquals(0, acf.refreshes);
		ITestBean proxied = (ITestBean) createProxy(pc);
		assertEquals(1, acf.refreshes);
		assertEquals(1, l.activates);
		assertTrue(pc.isActive());
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(0, l.adviceChanges);
		NopInterceptor di = new NopInterceptor();
		pc.addAdvice(0, di);
		assertEquals(1, l.adviceChanges);
		assertEquals(2, acf.refreshes);
		assertEquals(target.getAge(), proxied.getAge());
		pc.removeAdvice(di);
		assertEquals(2, l.adviceChanges);
		assertEquals(3, acf.refreshes);
		assertEquals(target.getAge(), proxied.getAge());
		pc.getProxy();
		assertEquals(1, l.activates);

		pc.removeListener(l);
		assertEquals(2, l.adviceChanges);
		pc.addAdvisor(new DefaultPointcutAdvisor(new NopInterceptor()));
		// No longer counting
		assertEquals(2, l.adviceChanges);
	}
