	@Test
	public void testPropertiesForTransactionalEntityManager() {
		Properties props = new Properties();
		props.put("foo", "bar");
		EntityManager em = mock(EntityManager.class);
		given(mockEmf.createEntityManager(props)).willReturn(em);
		given(em.getDelegate()).willReturn(new Object());
		given(em.isOpen()).willReturn(true);

		PersistenceAnnotationBeanPostProcessor pabpp = new MockPersistenceAnnotationBeanPostProcessor();
		DefaultPrivatePersistenceContextFieldWithProperties transactionalField =
				new DefaultPrivatePersistenceContextFieldWithProperties();
		pabpp.postProcessPropertyValues(null, null, transactionalField, "bean");

		assertNotNull(transactionalField.em);
		assertNotNull(transactionalField.em.getDelegate());

		verify(em).close();
	}
