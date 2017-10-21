	@Test
	public void testSerializable() throws Exception {
		DerivedTestBean tb = new DerivedTestBean();
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[] {ITestBean.class});
		ConcurrencyThrottleInterceptor cti = new ConcurrencyThrottleInterceptor();
		proxyFactory.addAdvice(cti);
		proxyFactory.setTarget(tb);
		ITestBean proxy = (ITestBean) proxyFactory.getProxy();
		proxy.getAge();

		ITestBean serializedProxy = (ITestBean) SerializationTestUtils.serializeAndDeserialize(proxy);
		Advised advised = (Advised) serializedProxy;
		ConcurrencyThrottleInterceptor serializedCti =
				(ConcurrencyThrottleInterceptor) advised.getAdvisors()[0].getAdvice();
		assertEquals(cti.getConcurrencyLimit(), serializedCti.getConcurrencyLimit());
		serializedProxy.getAge();
	}
