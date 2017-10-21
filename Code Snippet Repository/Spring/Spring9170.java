	private void doTestExistingTransactionWithParticipation(int propagationBehavior) {
		MockUOWManager manager = new MockUOWManager();
		final WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		final DefaultTransactionDefinition definition2 = new DefaultTransactionDefinition();
		definition2.setPropagationBehavior(propagationBehavior);
		definition2.setReadOnly(true);

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals("result", ptm.execute(definition, new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
				assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
				assertEquals("result2", ptm.execute(definition2, new TransactionCallback<String>() {
					@Override
					public String doInTransaction(TransactionStatus status) {
						assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
						assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
						assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
						return "result2";
					}
				}));
				return "result";
			}
		}));

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals(0, manager.getUOWTimeout());
		assertEquals(UOWManager.UOW_TYPE_GLOBAL_TRANSACTION, manager.getUOWType());
		assertTrue(manager.getJoined());
		assertFalse(manager.getRollbackOnly());
	}
