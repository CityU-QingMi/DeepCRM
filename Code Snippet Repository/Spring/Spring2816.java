	@Test
	public void testBeforeAdviceThrowsException() {
		final RuntimeException rex = new RuntimeException();
		@SuppressWarnings("serial")
		CountingBeforeAdvice ba = new CountingBeforeAdvice() {
			@Override
			public void before(Method m, Object[] args, Object target) throws Throwable {
				super.before(m, args, target);
				if (m.getName().startsWith("set"))
					throw rex;
			}
		};

		TestBean target = new TestBean();
		target.setAge(80);
		NopInterceptor nop1 = new NopInterceptor();
		NopInterceptor nop2 = new NopInterceptor();
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(nop1);
		pf.addAdvice(ba);
		pf.addAdvice(nop2);
		ITestBean proxied = (ITestBean) createProxy(pf);
		// Won't throw an exception
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(1, ba.getCalls());
		assertEquals(1, ba.getCalls("getAge"));
		assertEquals(1, nop1.getCount());
		assertEquals(1, nop2.getCount());
		// Will fail, after invoking Nop1
		try {
			proxied.setAge(26);
			fail("before advice should have ended chain");
		}
		catch (RuntimeException ex) {
			assertEquals(rex, ex);
		}
		assertEquals(2, ba.getCalls());
		assertEquals(2, nop1.getCount());
		// Nop2 didn't get invoked when the exception was thrown
		assertEquals(1, nop2.getCount());
		// Shouldn't have changed value in joinpoint
		assertEquals(target.getAge(), proxied.getAge());
	}
