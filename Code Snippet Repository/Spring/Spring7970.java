	@Test
	@Ignore
	public void testPersistenceUnitsFromJndi() {
		EntityManager mockEm = mock(EntityManager.class);
		given(mockEmf.createEntityManager()).willReturn(mockEm);

		EntityManagerFactoryWithInfo mockEmf2 = mock(EntityManagerFactoryWithInfo.class);

		Map<String, String> persistenceUnits = new HashMap<>();
		persistenceUnits.put("", "pu1");
		persistenceUnits.put("Person", "pu2");
		ExpectedLookupTemplate jt = new ExpectedLookupTemplate();
		jt.addObject("java:comp/env/pu1", mockEmf);
		jt.addObject("java:comp/env/pu2", mockEmf2);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		PersistenceAnnotationBeanPostProcessor bpp = new PersistenceAnnotationBeanPostProcessor();
		bpp.setPersistenceUnits(persistenceUnits);
		bpp.setJndiTemplate(jt);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition(DefaultPublicPersistenceUnitSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceUnitSetter.class));
		bf.registerBeanDefinition(DefaultPublicPersistenceUnitSetterNamedPerson.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceUnitSetterNamedPerson.class));
		bf.registerBeanDefinition(DefaultPrivatePersistenceContextField.class.getName(),
				new RootBeanDefinition(DefaultPrivatePersistenceContextField.class));
		bf.registerBeanDefinition(DefaultPublicPersistenceContextSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceContextSetter.class));

		DefaultPublicPersistenceUnitSetter bean = (DefaultPublicPersistenceUnitSetter)
				bf.getBean(DefaultPublicPersistenceUnitSetter.class.getName());
		DefaultPublicPersistenceUnitSetterNamedPerson bean2 = (DefaultPublicPersistenceUnitSetterNamedPerson)
				bf.getBean(DefaultPublicPersistenceUnitSetterNamedPerson.class.getName());
		DefaultPrivatePersistenceContextField bean3 = (DefaultPrivatePersistenceContextField)
				bf.getBean(DefaultPrivatePersistenceContextField.class.getName());
		DefaultPublicPersistenceContextSetter bean4 = (DefaultPublicPersistenceContextSetter)
				bf.getBean(DefaultPublicPersistenceContextSetter.class.getName());
		assertSame(mockEmf, bean.emf);
		assertSame(mockEmf2, bean2.emf);
		assertNotNull(bean3.em);
		assertNotNull(bean4.em);
	}
