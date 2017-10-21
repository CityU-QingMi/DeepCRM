	@Test
	public void testParticipatingTransactionWithIncompatibleIsolationLevel() throws Exception {
		tm.setValidateExistingTransaction(true);

		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(ds));
		assertTrue("Synchronization not active", !TransactionSynchronizationManager.isSynchronizationActive());

		try {
			final TransactionTemplate tt = new TransactionTemplate(tm);
			final TransactionTemplate tt2 = new TransactionTemplate(tm);
			tt2.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);

			tt.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
					assertFalse("Is not rollback-only", status.isRollbackOnly());
					tt2.execute(new TransactionCallbackWithoutResult() {
						@Override
						protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
							status.setRollbackOnly();
						}
					});
					assertTrue("Is rollback-only", status.isRollbackOnly());
				}
			});

			fail("Should have thrown IllegalTransactionStateException");
		}
		catch (IllegalTransactionStateException ex) {
			// expected
		}

		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(ds));
		verify(con).rollback();
		verify(con).close();
	}
