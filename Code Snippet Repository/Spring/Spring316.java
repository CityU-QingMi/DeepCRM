	@Test
	@SuppressWarnings("")
	public void testWithInstance() throws Exception {
		MultiplyReturnValue aspect = new MultiplyReturnValue();
		int multiple = 3;
		aspect.setMultiple(multiple);

		TestBean target = new TestBean();
		target.setAge(24);

		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(target);
		proxyFactory.addAspect(aspect);

		ITestBean proxy = proxyFactory.getProxy();
		assertEquals(target.getAge() * multiple, proxy.getAge());

		ITestBean serializedProxy = (ITestBean) SerializationTestUtils.serializeAndDeserialize(proxy);
		assertEquals(target.getAge() * multiple, serializedProxy.getAge());
	}
