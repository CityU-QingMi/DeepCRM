	@Test
	public void crossClassInterfaceOnJdkProxy() throws Exception {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new OtherServiceImpl());
		proxyFactory.addInterface(OtherService.class);
		proxyFactory.addAdvice(this.ti);

		OtherService otherService = (OtherService) proxyFactory.getProxy();

		otherService.foo();
		assertGetTransactionAndCommitCount(1);
	}
