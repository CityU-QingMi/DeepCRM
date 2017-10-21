	@Test
	public void testPropertiesForSharedEntityManager2() {
		Properties props = new Properties();
		props.put("foo", "bar");
		EntityManager em = mock(EntityManager.class);
		// only one call made  - the first EM definition wins (in this case the one w/o the properties)
		given(mockEmf.createEntityManager()).willReturn(em);
		given(em.getDelegate()).willReturn(new Object(), 2);
		given(em.isOpen()).willReturn(true);

		PersistenceAnnotationBeanPostProcessor pabpp = new MockPersistenceAnnotationBeanPostProcessor();
		DefaultPrivatePersistenceContextFieldWithProperties transactionalFieldWithProperties =
				new DefaultPrivatePersistenceContextFieldWithProperties();
		DefaultPrivatePersistenceContextField transactionalField = new DefaultPrivatePersistenceContextField();

		pabpp.postProcessPropertyValues(null, null, transactionalFieldWithProperties, "bean1");
		pabpp.postProcessPropertyValues(null, null, transactionalField, "bean2");

		assertNotNull(transactionalFieldWithProperties.em);
		assertNotNull(transactionalField.em);
		// the EM w/o properties will be created
		assertNotNull(transactionalField.em.getDelegate());
		// bind em to the thread now since it's created
		try {
			TransactionSynchronizationManager.bindResource(mockEmf, new EntityManagerHolder(em));
			assertNotNull(transactionalFieldWithProperties.em.getDelegate());
			verify(em).close();
		}
		finally {
			TransactionSynchronizationManager.unbindResource(mockEmf);
		}
	}
