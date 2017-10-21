	@Test
	public void testNoPropertiesPassedIn() {
		EntityManager mockEm = mock(EntityManager.class);
		given(mockEmf.createEntityManager()).willReturn(mockEm);

		PersistenceAnnotationBeanPostProcessor pabpp = new MockPersistenceAnnotationBeanPostProcessor();
		DefaultPrivatePersistenceContextFieldExtended dppcf = new DefaultPrivatePersistenceContextFieldExtended();
		pabpp.postProcessPropertyValues(null, null, dppcf, "bean");
		assertNotNull(dppcf.em);
	}
