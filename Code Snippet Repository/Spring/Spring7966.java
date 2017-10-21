	@Test
	public void testPublicPersistenceUnitSetter() {
		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		gac.registerBeanDefinition(DefaultPublicPersistenceUnitSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceUnitSetter.class));
		gac.refresh();

		DefaultPublicPersistenceUnitSetter bean = (DefaultPublicPersistenceUnitSetter) gac.getBean(
				DefaultPublicPersistenceUnitSetter.class.getName());
		assertSame(mockEmf, bean.emf);
	}
