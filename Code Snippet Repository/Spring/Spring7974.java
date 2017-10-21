	@Test
	public void testPersistenceContextsFromJndiWithDefaultUnit() {
		EntityManager mockEm = mock(EntityManager.class);
		EntityManager mockEm2 = mock(EntityManager.class);
		EntityManager mockEm3 = mock(EntityManager.class);

		Map<String, String> persistenceContexts = new HashMap<>();
		persistenceContexts.put("System", "pc1");
		persistenceContexts.put("Person", "pc2");
		Map<String, String> extendedPersistenceContexts = new HashMap<>();
		extendedPersistenceContexts .put("System", "pc3");
		ExpectedLookupTemplate jt = new ExpectedLookupTemplate();
		jt.addObject("java:comp/env/pc1", mockEm);
		jt.addObject("java:comp/env/pc2", mockEm2);
		jt.addObject("java:comp/env/pc3", mockEm3);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		PersistenceAnnotationBeanPostProcessor bpp = new PersistenceAnnotationBeanPostProcessor();
		bpp.setPersistenceContexts(persistenceContexts);
		bpp.setExtendedPersistenceContexts(extendedPersistenceContexts);
		bpp.setDefaultPersistenceUnitName("System");
		bpp.setJndiTemplate(jt);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition(DefaultPrivatePersistenceContextField.class.getName(),
				new RootBeanDefinition(DefaultPrivatePersistenceContextField.class));
		bf.registerBeanDefinition(DefaultPrivatePersistenceContextFieldNamedPerson.class.getName(),
				new RootBeanDefinition(DefaultPrivatePersistenceContextFieldNamedPerson.class));
		bf.registerBeanDefinition(DefaultPublicPersistenceContextSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceContextSetter.class));

		DefaultPrivatePersistenceContextField bean1 = (DefaultPrivatePersistenceContextField)
				bf.getBean(DefaultPrivatePersistenceContextField.class.getName());
		DefaultPrivatePersistenceContextFieldNamedPerson bean2 = (DefaultPrivatePersistenceContextFieldNamedPerson)
				bf.getBean(DefaultPrivatePersistenceContextFieldNamedPerson.class.getName());
		DefaultPublicPersistenceContextSetter bean3 = (DefaultPublicPersistenceContextSetter)
				bf.getBean(DefaultPublicPersistenceContextSetter.class.getName());
		assertSame(mockEm, bean1.em);
		assertSame(mockEm2, bean2.em);
		assertSame(mockEm3, bean3.em);
	}
