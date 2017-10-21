	@Test
	public void testPublicExtendedPersistenceContextSetterWithSerialization() throws Exception {
		DummyInvocationHandler ih = new DummyInvocationHandler();
		Object mockEm = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {EntityManager.class}, ih);
		given(mockEmf.createEntityManager()).willReturn((EntityManager) mockEm);

		GenericApplicationContext gac = new GenericApplicationContext();
		SimpleMapScope myScope = new SimpleMapScope();
		gac.getDefaultListableBeanFactory().registerScope("myScope", myScope);
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		RootBeanDefinition bd = new RootBeanDefinition(DefaultPublicPersistenceContextSetter.class);
		bd.setScope("myScope");
		gac.registerBeanDefinition(DefaultPublicPersistenceContextSetter.class.getName(), bd);
		gac.refresh();

		DefaultPublicPersistenceContextSetter bean = (DefaultPublicPersistenceContextSetter) gac.getBean(
				DefaultPublicPersistenceContextSetter.class.getName());
		assertNotNull(bean.em);
		assertNotNull(SerializationTestUtils.serializeAndDeserialize(bean.em));

		SimpleMapScope serialized = (SimpleMapScope) SerializationTestUtils.serializeAndDeserialize(myScope);
		serialized.close();
		assertTrue(DummyInvocationHandler.closed);
		DummyInvocationHandler.closed = false;
	}
