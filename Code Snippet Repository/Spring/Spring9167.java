	@Test
	public void existingNonSpringTransaction() {
		MockUOWManager manager = new MockUOWManager();
		manager.setUOWStatus(UOWManager.UOW_STATUS_ACTIVE);
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals("result", ptm.execute(definition, new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus status) {
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
				assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
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
