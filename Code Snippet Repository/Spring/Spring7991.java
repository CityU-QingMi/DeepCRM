	@Test
	public void testPublicExtendedPersistenceContextSetterWithOverriding() {
		EntityManager mockEm2 = mock(EntityManager.class);

		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		RootBeanDefinition bd = new RootBeanDefinition(DefaultPublicPersistenceContextSetter.class);
		bd.getPropertyValues().add("entityManager", mockEm2);
		gac.registerBeanDefinition(DefaultPublicPersistenceContextSetter.class.getName(), bd);
		gac.refresh();

		DefaultPublicPersistenceContextSetter bean = (DefaultPublicPersistenceContextSetter) gac.getBean(
				DefaultPublicPersistenceContextSetter.class.getName());
		assertSame(mockEm2, bean.em);
	}
