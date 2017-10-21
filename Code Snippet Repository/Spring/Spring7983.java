	@Test
	public void testPropertiesForSharedEntityManager1() {
		Properties props = new Properties();
		props.put("foo", "bar");
		EntityManager em = mock(EntityManager.class);
		// only one call made  - the first EM definition wins (in this case the one w/ the properties)
		given(mockEmf.createEntityManager(props)).willReturn(em);
		given(em.getDelegate()).willReturn(new Object());
		given(em.isOpen()).willReturn(true);

		PersistenceAnnotationBeanPostProcessor pabpp = new MockPersistenceAnnotationBeanPostProcessor();
		DefaultPrivatePersistenceContextFieldWithProperties transactionalFieldWithProperties =
				new DefaultPrivatePersistenceContextFieldWithProperties();
		DefaultPrivatePersistenceContextField transactionalField = new DefaultPrivatePersistenceContextField();

		pabpp.postProcessPropertyValues(null, null, transactionalFieldWithProperties, "bean1");
		pabpp.postProcessPropertyValues(null, null, transactionalField, "bean2");

		assertNotNull(transactionalFieldWithProperties.em);
		assertNotNull(transactionalField.em);
		// the EM w/ properties will be created
		assertNotNull(transactionalFieldWithProperties.em.getDelegate());
		// bind em to the thread now since it's created
		try {
			TransactionSynchronizationManager.bindResource(mockEmf, new EntityManagerHolder(em));
			assertNotNull(transactionalField.em.getDelegate());
			verify(em).close();
		}
		finally {
			TransactionSynchronizationManager.unbindResource(mockEmf);
		}
	}
