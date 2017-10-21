	@After
	public void tearDown() throws Exception {
		if (this.transactionStatus != null && !this.transactionStatus.isCompleted()) {
			endTransaction();
		}

		assertTrue(TransactionSynchronizationManager.getResourceMap().isEmpty());
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
	}
