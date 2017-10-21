	@Test
	public void withMultiMethodOverride() {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new TestWithMultiMethodOverride());
		proxyFactory.addAdvice(this.ti);

		TestWithMultiMethodOverride proxy = (TestWithMultiMethodOverride) proxyFactory.getProxy();

		proxy.doSomething();
		assertGetTransactionAndCommitCount(1);

		proxy.doSomethingElse();
		assertGetTransactionAndCommitCount(2);

		proxy.doSomethingCompletelyElse();
		assertGetTransactionAndCommitCount(3);

		proxy.doSomething();
		assertGetTransactionAndCommitCount(4);
	}
