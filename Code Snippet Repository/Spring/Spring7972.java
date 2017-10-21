	@Test
	public void testSinglePersistenceUnitFromJndi() {
		Map<String, String> persistenceUnits = new HashMap<>();
		persistenceUnits.put("Person", "pu1");
		ExpectedLookupTemplate jt = new ExpectedLookupTemplate();
		jt.addObject("java:comp/env/pu1", mockEmf);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		PersistenceAnnotationBeanPostProcessor bpp = new PersistenceAnnotationBeanPostProcessor();
		bpp.setPersistenceUnits(persistenceUnits);
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
		assertSame(mockEmf, bean2.emf);
	}
