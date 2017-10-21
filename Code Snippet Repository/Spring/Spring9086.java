	@Test
	public void withRollback() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithRollback());
		proxyFactory.addAdvice(this.ti);

		TestWithRollback proxy = (TestWithRollback) proxyFactory.getProxy();

		try {
			proxy.doSomethingErroneous();
			fail("Should throw IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertGetTransactionAndRollbackCount(1);
		}

		try {
			proxy.doSomethingElseErroneous();
			fail("Should throw IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			assertGetTransactionAndRollbackCount(2);
		}
	}
