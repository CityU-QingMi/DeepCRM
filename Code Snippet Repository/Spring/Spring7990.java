	@Test
	@SuppressWarnings({ "", "" })
	public void testPublicExtendedPersistenceContextSetterWithEntityManagerInfoAndSerialization() throws Exception {
		EntityManager mockEm = mock(EntityManager.class, withSettings().serializable());
		given(mockEm.isOpen()).willReturn(true);
		EntityManagerFactoryWithInfo mockEmf = mock(EntityManagerFactoryWithInfo.class);
		given(mockEmf.getNativeEntityManagerFactory()).willReturn(mockEmf);
		given(mockEmf.getJpaDialect()).willReturn(new DefaultJpaDialect());
		given(mockEmf.getEntityManagerInterface()).willReturn((Class)EntityManager.class);
		given(mockEmf.getBeanClassLoader()).willReturn(getClass().getClassLoader());
		given(mockEmf.createEntityManager()).willReturn(mockEm);

		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		gac.registerBeanDefinition(DefaultPublicPersistenceContextSetter.class.getName(),
				new RootBeanDefinition(DefaultPublicPersistenceContextSetter.class));
		gac.refresh();

		DefaultPublicPersistenceContextSetter bean = (DefaultPublicPersistenceContextSetter) gac.getBean(
				DefaultPublicPersistenceContextSetter.class.getName());
		assertNotNull(bean.em);
		assertNotNull(SerializationTestUtils.serializeAndDeserialize(bean.em));
	}
