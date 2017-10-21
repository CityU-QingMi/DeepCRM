	@Test
	public void testAddThrowsAdviceWithoutAdvisor() throws Throwable {
		// Reacts to ServletException and RemoteException
		MyThrowsHandler th = new MyThrowsHandler();

		Echo target = new Echo();
		target.setA(16);
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvice(new NopInterceptor());
		pf.addAdvice(th);
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

		// Subclass of RemoteException
		ex = new MarshalException("");
		try {
			proxied.echoException(1, ex);
			fail();
		}
		catch (MarshalException caught) {
			assertEquals(ex, caught);
		}
		assertEquals(1, th.getCalls("remoteException"));
	}
