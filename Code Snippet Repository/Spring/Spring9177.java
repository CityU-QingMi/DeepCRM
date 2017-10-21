	@Test
	public void newTransactionWithCommitAndTimeout() {
		MockUOWManager manager = new MockUOWManager();
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setTimeout(10);
		definition.setReadOnly(true);

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals("result", ptm.execute(definition, new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
				assertTrue(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
				return "result";
			}
		}));

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals(10, manager.getUOWTimeout());
		assertEquals(UOWManager.UOW_TYPE_GLOBAL_TRANSACTION, manager.getUOWType());
		assertFalse(manager.getJoined());
		assertFalse(manager.getRollbackOnly());
	}
