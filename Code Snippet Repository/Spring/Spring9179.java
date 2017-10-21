	@Test
	public void newTransactionWithRollback() {
		MockUOWManager manager = new MockUOWManager();
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		try {
			ptm.execute(definition, new TransactionCallback<String>() {
				@Override
				public String doInTransaction(TransactionStatus status) {
					assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
					assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
					assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
					throw new OptimisticLockingFailureException("");
				}
			});
			fail("Should have thrown OptimisticLockingFailureException");
		}
		catch (OptimisticLockingFailureException ex) {
			// expected
		}

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals(0, manager.getUOWTimeout());
		assertEquals(UOWManager.UOW_TYPE_GLOBAL_TRANSACTION, manager.getUOWType());
		assertFalse(manager.getJoined());
		assertTrue(manager.getRollbackOnly());
	}
