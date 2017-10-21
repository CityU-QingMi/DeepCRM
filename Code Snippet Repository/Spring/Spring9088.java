	@Test
	public void crossClassInterfaceMethodLevelOnJdkProxy() throws Exception {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new SomeServiceImpl());
		proxyFactory.addInterface(SomeService.class);
		proxyFactory.addAdvice(this.ti);

		SomeService someService = (SomeService) proxyFactory.getProxy();

		someService.bar();
		assertGetTransactionAndCommitCount(1);

		someService.foo();
		assertGetTransactionAndCommitCount(2);

		someService.fooBar();
		assertGetTransactionAndCommitCount(3);
	}
