	@Test
	public void testPublicPersistenceUnitSetterWithOverriding() {
		EntityManagerFactory mockEmf2 = mock(EntityManagerFactory.class);

		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		RootBeanDefinition bd = new RootBeanDefinition(DefaultPublicPersistenceUnitSetter.class);
		bd.getPropertyValues().add("emf", mockEmf2);
		gac.registerBeanDefinition(DefaultPublicPersistenceUnitSetter.class.getName(), bd);
		gac.refresh();

		DefaultPublicPersistenceUnitSetter bean = (DefaultPublicPersistenceUnitSetter) gac.getBean(
				DefaultPublicPersistenceUnitSetter.class.getName());
		assertSame(mockEmf2, bean.emf);
	}
