	private void doTestNewTransactionWithCommit(int propagationBehavior, final int synchMode) {
		MockUOWManager manager = new MockUOWManager();
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		ptm.setTransactionSynchronization(synchMode);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setPropagationBehavior(propagationBehavior);
		definition.setReadOnly(true);

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals("result", ptm.execute(definition, new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				if (synchMode != WebSphereUowTransactionManager.SYNCHRONIZATION_NEVER) {
					assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
					assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
					assertTrue(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
				}
				else {
					assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
					assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
					assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
				}
				return "result";
			}
		}));

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals(0, manager.getUOWTimeout());
		assertEquals(UOWManager.UOW_TYPE_GLOBAL_TRANSACTION, manager.getUOWType());
		assertFalse(manager.getJoined());
		assertFalse(manager.getRollbackOnly());
	}
