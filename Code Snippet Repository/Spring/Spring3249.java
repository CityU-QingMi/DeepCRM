	@Test
	public void testInterfacesScopedProxy() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"org/springframework/context/annotation/scopedProxyInterfacesTests.xml");
		context.getBeanFactory().registerScope("myScope", new SimpleMapScope());

		// should cast to the interface
		FooService bean = (FooService) context.getBean("scopedProxyTestBean");
		// should be dynamic proxy
		assertTrue(AopUtils.isJdkDynamicProxy(bean));
		// test serializability
		assertEquals("bar", bean.foo(1));
		FooService deserialized = (FooService) SerializationTestUtils.serializeAndDeserialize(bean);
		assertNotNull(deserialized);
		assertEquals("bar", deserialized.foo(1));
		context.close();
	}
