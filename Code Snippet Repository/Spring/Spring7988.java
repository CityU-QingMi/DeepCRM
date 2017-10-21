	@Test
	public void testInjectionIntoExistingObjects() {
		EntityManager mockEm = mock(EntityManager.class);
		given(mockEmf.createEntityManager()).willReturn(mockEm);

		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		gac.refresh();

		DefaultPrivatePersistenceContextField existingBean1 = new DefaultPrivatePersistenceContextField();
		gac.getAutowireCapableBeanFactory().autowireBean(existingBean1);
		assertNotNull(existingBean1.em);

		DefaultPublicPersistenceContextSetter existingBean2 = new DefaultPublicPersistenceContextSetter();
		gac.getAutowireCapableBeanFactory().autowireBean(existingBean2);
		assertNotNull(existingBean2.em);
	}
