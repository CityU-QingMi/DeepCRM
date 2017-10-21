	@Test
	public void testAfterReturningAdvisorIsNotInvokedOnException() {
		CountingAfterReturningAdvice car = new CountingAfterReturningAdvice();
		TestBean target = new TestBean();
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new NopInterceptor());
		pf.addAdvice(car);
		assertEquals("Advice was wrapped in Advisor and added", car, pf.getAdvisors()[1].getAdvice());
		ITestBean proxied = (ITestBean) createProxy(pf);
		assertEquals(0, car.getCalls());
		int age = 10;
		proxied.setAge(age);
		assertEquals(age, proxied.getAge());
		assertEquals(2, car.getCalls());
		Exception exc = new Exception();
		// On exception it won't be invoked
		try {
			proxied.exceptional(exc);
			fail();
		}
		catch (Throwable t) {
			assertSame(exc, t);
		}
		assertEquals(2, car.getCalls());
	}
