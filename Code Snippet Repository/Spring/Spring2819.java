	@Test
	public void testThrowsAdvisorIsInvoked() throws Throwable {
		// Reacts to ServletException and RemoteException
		MyThrowsHandler th = new MyThrowsHandler();
		@SuppressWarnings("serial")
		Advisor matchesEchoInvocations = new StaticMethodMatcherPointcutAdvisor(th) {
			@Override
			public boolean matches(Method m, @Nullable Class<?> targetClass) {
				return m.getName().startsWith("echo");
			}
		};

		Echo target = new Echo();
		target.setA(16);
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new NopInterceptor());
		pf.addAdvisor(matchesEchoInvocations);
		assertEquals("Advisor was added", matchesEchoInvocations, pf.getAdvisors()[1]);
		IEcho proxied = (IEcho) createProxy(pf);
		assertEquals(0, th.getCalls());
		assertEquals(target.getA(), proxied.getA());
		assertEquals(0, th.getCalls());
		Exception ex = new Exception();
		// Will be advised but doesn't match
		try {
			proxied.echoException(1, ex);
			fail();
		}
		catch (Exception caught) {
			assertEquals(ex, caught);
		}

		ex = new FileNotFoundException();
		try {
			proxied.echoException(1, ex);
			fail();
		}
		catch (FileNotFoundException caught) {
			assertEquals(ex, caught);
		}
		assertEquals(1, th.getCalls("ioException"));
	}
