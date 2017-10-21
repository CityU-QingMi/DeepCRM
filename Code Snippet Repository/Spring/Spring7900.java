	@Test
	public void testDoGetEntityManagerWithTx() throws Exception {
		try {
			EntityManagerFactory factory = mock(EntityManagerFactory.class);
			EntityManager manager = mock(EntityManager.class);

			TransactionSynchronizationManager.initSynchronization();
			given(factory.createEntityManager()).willReturn(manager);

			// no tx active
			assertSame(manager, EntityManagerFactoryUtils.doGetTransactionalEntityManager(factory, null));
			assertSame(manager, ((EntityManagerHolder)TransactionSynchronizationManager.unbindResource(factory)).getEntityManager());
		}
		finally {
			TransactionSynchronizationManager.clearSynchronization();
		}

		assertTrue(TransactionSynchronizationManager.getResourceMap().isEmpty());
	}
