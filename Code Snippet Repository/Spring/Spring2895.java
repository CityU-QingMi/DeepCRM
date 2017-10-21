	@Test
	public void testJdkScopedProxy() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(TESTBEAN_CONTEXT);
		bf.setSerializationId("X");
		SimpleMapScope scope = new SimpleMapScope();
		bf.registerScope("request", scope);

		ITestBean bean = (ITestBean) bf.getBean("testBean");
		assertNotNull(bean);
		assertTrue(AopUtils.isJdkDynamicProxy(bean));
		assertTrue(bean instanceof ScopedObject);
		ScopedObject scoped = (ScopedObject) bean;
		assertEquals(TestBean.class, scoped.getTargetObject().getClass());
		bean.setAge(101);

		assertTrue(scope.getMap().containsKey("testBeanTarget"));
		assertEquals(TestBean.class, scope.getMap().get("testBeanTarget").getClass());

		ITestBean deserialized = (ITestBean) SerializationTestUtils.serializeAndDeserialize(bean);
		assertNotNull(deserialized);
		assertTrue(AopUtils.isJdkDynamicProxy(deserialized));
		assertEquals(101, bean.getAge());
		assertTrue(deserialized instanceof ScopedObject);
		ScopedObject scopedDeserialized = (ScopedObject) deserialized;
		assertEquals(TestBean.class, scopedDeserialized.getTargetObject().getClass());

		bf.setSerializationId(null);
	}
