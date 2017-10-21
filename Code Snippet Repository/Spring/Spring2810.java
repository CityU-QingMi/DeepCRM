	@Test
	public void testSerializationAdviceAndTargetNotSerializable() throws Exception {
		TestBean tb = new TestBean();
		assertFalse(SerializationTestUtils.isSerializable(tb));

		ProxyFactory pf = new ProxyFactory(tb);

		pf.addAdvice(new NopInterceptor());
		ITestBean proxy = (ITestBean) createAopProxy(pf).getProxy();

		assertFalse(SerializationTestUtils.isSerializable(proxy));
	}
