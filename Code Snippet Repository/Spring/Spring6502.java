	private void doTestJtaTransaction(final boolean rollback) throws Exception {
		if (rollback) {
			given(userTransaction.getStatus()).willReturn(
					Status.STATUS_NO_TRANSACTION,Status.STATUS_ACTIVE);
		}
		else {
			given(userTransaction.getStatus()).willReturn(
					Status.STATUS_NO_TRANSACTION, Status.STATUS_ACTIVE, Status.STATUS_ACTIVE);
		}

		JtaTransactionManager ptm = new JtaTransactionManager(userTransaction);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(dataSource));
		assertTrue("JTA synchronizations not active", !TransactionSynchronizationManager.isSynchronizationActive());

		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
				assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(dataSource));
				assertTrue("JTA synchronizations active", TransactionSynchronizationManager.isSynchronizationActive());
				assertTrue("Is new transaction", status.isNewTransaction());

				Connection c = DataSourceUtils.getConnection(dataSource);
				assertTrue("Has thread connection", TransactionSynchronizationManager.hasResource(dataSource));
				DataSourceUtils.releaseConnection(c, dataSource);

				c = DataSourceUtils.getConnection(dataSource);
				assertTrue("Has thread connection", TransactionSynchronizationManager.hasResource(dataSource));
				DataSourceUtils.releaseConnection(c, dataSource);

				if (rollback) {
					status.setRollbackOnly();
				}
			}
		});

		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(dataSource));
		assertTrue("JTA synchronizations not active", !TransactionSynchronizationManager.isSynchronizationActive());
		verify(userTransaction).begin();
		if (rollback) {
			verify(userTransaction).rollback();
		}
		verify(connection).close();
	}
