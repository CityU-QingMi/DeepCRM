	@Test
	public void testPersistenceUnitsFromJndiWithDefaultUnit() {
		EntityManagerFactoryWithInfo mockEmf2 = mock(EntityManagerFactoryWithInfo.class);

		Map<String, String> persistenceUnits = new HashMap<>();
		persistenceUnits.put("System", "pu1");
		persistenceUnits.put("Person", "pu2");
		ExpectedLookupTemplate jt = new ExpectedLookupTemplate();
		jt.addObject("java:comp/env/pu1", mockEmf);
		jt.addObject("java:comp/env/pu2", mockEmf2);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		PersistenceAnnotationBeanPostProcessor bpp = new PersistenceAnnotationBeanPostProcessor();
		bpp.setPersistenceUnits(persistenceUnits);
		bpp.setDefaultPersistenceUnitName("System");
		bpp.setJndiTemplate(jt);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition(DefaultPublicPersistenceUnitSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceUnitSetter.class));
		bf.registerBeanDefinition(DefaultPublicPersistenceUnitSetterNamedPerson.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceUnitSetterNamedPerson.class));

		DefaultPublicPersistenceUnitSetter bean = (DefaultPublicPersistenceUnitSetter)
				bf.getBean(DefaultPublicPersistenceUnitSetter.class.getName());
		DefaultPublicPersistenceUnitSetterNamedPerson bean2 = (DefaultPublicPersistenceUnitSetterNamedPerson)
				bf.getBean(DefaultPublicPersistenceUnitSetterNamedPerson.class.getName());
		assertSame(mockEmf, bean.emf);
		assertSame(mockEmf2, bean2.emf);
	}
