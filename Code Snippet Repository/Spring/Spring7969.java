	@Test
	public void testPublicPersistenceUnitSetterWithMultipleUnitsIdentifiedThroughUnitName() {
		EntityManagerFactoryWithInfo mockEmf2 = mock(EntityManagerFactoryWithInfo.class);
		given(mockEmf2.getPersistenceUnitName()).willReturn("Person");

		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory2", mockEmf2);
		RootBeanDefinition processorDef = new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class);
		processorDef.getPropertyValues().add("defaultPersistenceUnitName", "entityManagerFactory");
		gac.registerBeanDefinition("annotationProcessor", processorDef);
		gac.registerBeanDefinition(DefaultPublicPersistenceUnitSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceUnitSetter.class));
		gac.registerBeanDefinition(DefaultPublicPersistenceUnitSetterNamedPerson.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceUnitSetterNamedPerson.class));
		gac.refresh();

		DefaultPublicPersistenceUnitSetter bean = (DefaultPublicPersistenceUnitSetter)
				gac.getBean(DefaultPublicPersistenceUnitSetter.class.getName());
		DefaultPublicPersistenceUnitSetterNamedPerson bean2 = (DefaultPublicPersistenceUnitSetterNamedPerson)
				gac.getBean(DefaultPublicPersistenceUnitSetterNamedPerson.class.getName());
		assertSame(mockEmf, bean.emf);
		assertSame(mockEmf2, bean2.emf);
	}
