	@Test
	public void testPrivatePersistenceUnitField() {
		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		gac.registerBeanDefinition(DefaultPrivatePersistenceUnitField.class.getName(),
				new RootBeanDefinition(DefaultPrivatePersistenceUnitField.class));
		gac.refresh();

		DefaultPrivatePersistenceUnitField bean = (DefaultPrivatePersistenceUnitField) gac.getBean(
				DefaultPrivatePersistenceUnitField.class.getName());
		assertSame(mockEmf, bean.emf);
	}
