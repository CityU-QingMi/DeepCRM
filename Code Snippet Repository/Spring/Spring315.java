	@Test
	@SuppressWarnings("")
	public void testSerializable() throws Exception {
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(new TestBean());
		proxyFactory.addAspect(LoggingAspectOnVarargs.class);
		ITestBean proxy = proxyFactory.getProxy();
		assertTrue(proxy.doWithVarargs(MyEnum.A, MyOtherEnum.C));
		ITestBean tb = (ITestBean) SerializationTestUtils.serializeAndDeserialize(proxy);
		assertTrue(tb.doWithVarargs(MyEnum.A, MyOtherEnum.C));
	}
