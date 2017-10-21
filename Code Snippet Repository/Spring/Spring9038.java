	@Test
	public void jtaTransactionManagerWithRollbackAndCommit() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_NO_TRANSACTION, Status.STATUS_ACTIVE);

		JtaTransactionManager ptm = newJtaTransactionManager(ut);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		TransactionStatus status = ptm.getTransaction(new DefaultTransactionDefinition());
		assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
		// first: rollback
		ptm.rollback(status);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		try {
			// second: commit attempt
			ptm.commit(status);
			fail("Should have thrown IllegalTransactionStateException");
		}
		catch (IllegalTransactionStateException ex) {
			// expected
		}

		verify(ut).begin();
		verify(ut).rollback();
	}
