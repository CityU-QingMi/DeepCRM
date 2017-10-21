	@Test
	public void testDoGetEntityManager() {
		// test null assertion
		try {
			EntityManagerFactoryUtils.doGetTransactionalEntityManager(null, null);
			fail("expected exception");
		}
		catch (IllegalArgumentException ex) {
			// it's okay
		}
		EntityManagerFactory factory = mock(EntityManagerFactory.class);

		// no tx active
		assertNull(EntityManagerFactoryUtils.doGetTransactionalEntityManager(factory, null));
		assertTrue(TransactionSynchronizationManager.getResourceMap().isEmpty());
	}
