	@Test
	public void serializable() throws Exception {
		TestBean1 tb = new TestBean1();
		CallCountingTransactionManager ptm = new CallCountingTransactionManager();
		AnnotationTransactionAttributeSource tas = new AnnotationTransactionAttributeSource();
		TransactionInterceptor ti = new TransactionInterceptor(ptm, tas);

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(ITestBean1.class);
		proxyFactory.addAdvice(ti);
		proxyFactory.setTarget(tb);
		ITestBean1 proxy = (ITestBean1) proxyFactory.getProxy();
		proxy.getAge();
		assertEquals(1, ptm.commits);

		ITestBean1 serializedProxy = (ITestBean1) SerializationTestUtils.serializeAndDeserialize(proxy);
		serializedProxy.getAge();
		Advised advised = (Advised) serializedProxy;
		TransactionInterceptor serializedTi = (TransactionInterceptor) advised.getAdvisors()[0].getAdvice();
		CallCountingTransactionManager serializedPtm =
				(CallCountingTransactionManager) serializedTi.getTransactionManager();
		assertEquals(2, serializedPtm.commits);
	}
