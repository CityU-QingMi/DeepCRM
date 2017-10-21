	@Test
	public void testPublicSpecificExtendedPersistenceContextSetter() throws Exception {
		EntityManagerFactory mockEmf2 = mock(EntityManagerFactory.class);
		EntityManager mockEm2 = mock(EntityManager.class);
		given(mockEmf2.createEntityManager()).willReturn(mockEm2);

		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.getDefaultListableBeanFactory().registerSingleton("unit2", mockEmf2);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		gac.registerBeanDefinition(SpecificPublicPersistenceContextSetter.class.getName(),
				new RootBeanDefinition(SpecificPublicPersistenceContextSetter.class));
		gac.refresh();

		SpecificPublicPersistenceContextSetter bean = (SpecificPublicPersistenceContextSetter) gac.getBean(
				SpecificPublicPersistenceContextSetter.class.getName());
		assertNotNull(bean.getEntityManager());
		bean.getEntityManager().flush();
		verify(mockEm2).getTransaction();
		verify(mockEm2).flush();
	}
