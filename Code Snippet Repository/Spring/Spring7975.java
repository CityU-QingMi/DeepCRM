	@Test
	public void testSinglePersistenceContextFromJndi() {
		EntityManager mockEm = mock(EntityManager.class);
		EntityManager mockEm2 = mock(EntityManager.class);

		Map<String, String> persistenceContexts = new HashMap<>();
		persistenceContexts.put("System", "pc1");
		Map<String, String> extendedPersistenceContexts = new HashMap<>();
		extendedPersistenceContexts .put("System", "pc2");
		ExpectedLookupTemplate jt = new ExpectedLookupTemplate();
		jt.addObject("java:comp/env/pc1", mockEm);
		jt.addObject("java:comp/env/pc2", mockEm2);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		PersistenceAnnotationBeanPostProcessor bpp = new PersistenceAnnotationBeanPostProcessor();
		bpp.setPersistenceContexts(persistenceContexts);
		bpp.setExtendedPersistenceContexts(extendedPersistenceContexts);
		bpp.setJndiTemplate(jt);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition(DefaultPrivatePersistenceContextField.class.getName(),
				new RootBeanDefinition(DefaultPrivatePersistenceContextField.class));
		bf.registerBeanDefinition(DefaultPublicPersistenceContextSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceContextSetter.class));

		DefaultPrivatePersistenceContextField bean1 = (DefaultPrivatePersistenceContextField)
				bf.getBean(DefaultPrivatePersistenceContextField.class.getName());
		DefaultPublicPersistenceContextSetter bean2 = (DefaultPublicPersistenceContextSetter)
				bf.getBean(DefaultPublicPersistenceContextSetter.class.getName());
		assertSame(mockEm, bean1.em);
		assertSame(mockEm2, bean2.em);
	}
