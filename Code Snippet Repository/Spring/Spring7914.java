	@Test
	public void testTransactionRollbackWithPreboundAndPropagationSupports() {
		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

		assertTrue(!TransactionSynchronizationManager.hasResource(factory));
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());
		TransactionSynchronizationManager.bindResource(factory, new EntityManagerHolder(manager));

		try {
			tt.execute(new TransactionCallback() {
				@Override
				public Object doInTransaction(TransactionStatus status) {
					assertTrue(TransactionSynchronizationManager.hasResource(factory));
					assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
					assertTrue(!status.isNewTransaction());
					EntityManagerFactoryUtils.getTransactionalEntityManager(factory).flush();
					status.setRollbackOnly();
					return null;
				}
			});

			assertTrue(TransactionSynchronizationManager.hasResource(factory));
			assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());
		}
		finally {
			TransactionSynchronizationManager.unbindResource(factory);
		}

		verify(manager).flush();
		verify(manager).clear();
	}
