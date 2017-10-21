	@Test
	public void testPropertiesPassedIn() {
		Properties props = new Properties();
		props.put("foo", "bar");
		EntityManager mockEm = mock(EntityManager.class);
		given(mockEmf.createEntityManager(props)).willReturn(mockEm);

		PersistenceAnnotationBeanPostProcessor pabpp = new MockPersistenceAnnotationBeanPostProcessor();
		DefaultPrivatePersistenceContextFieldExtendedWithProps dppcf =
				new DefaultPrivatePersistenceContextFieldExtendedWithProps();
		pabpp.postProcessPropertyValues(null, null, dppcf, "bean");
		assertNotNull(dppcf.em);
	}
