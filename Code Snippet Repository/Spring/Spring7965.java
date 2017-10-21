	@Test
	public void testPrivatePersistenceContextField() throws Exception {
		mockEmf = mock(EntityManagerFactory.class, withSettings().serializable());
		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		gac.registerBeanDefinition(DefaultPrivatePersistenceContextField.class.getName(),
				new RootBeanDefinition(DefaultPrivatePersistenceContextField.class));
		gac.registerBeanDefinition(FactoryBeanWithPersistenceContextField.class.getName(),
				new RootBeanDefinition(FactoryBeanWithPersistenceContextField.class));
		gac.refresh();

		DefaultPrivatePersistenceContextField bean = (DefaultPrivatePersistenceContextField) gac.getBean(
				DefaultPrivatePersistenceContextField.class.getName());
		FactoryBeanWithPersistenceContextField bean2 = (FactoryBeanWithPersistenceContextField) gac.getBean(
				"&" + FactoryBeanWithPersistenceContextField.class.getName());
		assertNotNull(bean.em);
		assertNotNull(bean2.em);

		assertNotNull(SerializationTestUtils.serializeAndDeserialize(bean.em));
		assertNotNull(SerializationTestUtils.serializeAndDeserialize(bean2.em));
	}
