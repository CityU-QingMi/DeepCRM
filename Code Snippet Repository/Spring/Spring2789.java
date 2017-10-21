	@Test
	public void testCanCastProxyToProxyConfig() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory(tb);
		NopInterceptor di = new NopInterceptor();
		pc.addAdvice(0, di);

		ITestBean t = (ITestBean) createProxy(pc);
		assertEquals(0, di.getCount());
		t.setAge(23);
		assertEquals(23, t.getAge());
		assertEquals(2, di.getCount());

		Advised advised = (Advised) t;
		assertEquals("Have 1 advisor", 1, advised.getAdvisors().length);
		assertEquals(di, advised.getAdvisors()[0].getAdvice());
		NopInterceptor di2 = new NopInterceptor();
		advised.addAdvice(1, di2);
		t.getName();
		assertEquals(3, di.getCount());
		assertEquals(1, di2.getCount());
		// will remove di
		advised.removeAdvisor(0);
		t.getAge();
		// Unchanged
		assertEquals(3, di.getCount());
		assertEquals(2, di2.getCount());

		CountingBeforeAdvice cba = new CountingBeforeAdvice();
		assertEquals(0, cba.getCalls());
		advised.addAdvice(cba);
		t.setAge(16);
		assertEquals(16, t.getAge());
		assertEquals(2, cba.getCalls());
	}
