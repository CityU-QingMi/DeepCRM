	@Test
	public void testJtaTransactionWithIsolationLevelDataSourceAdapter() throws Exception {
		given(userTransaction.getStatus()).willReturn(
				Status.STATUS_NO_TRANSACTION,
				Status.STATUS_ACTIVE,
				Status.STATUS_ACTIVE,
				Status.STATUS_NO_TRANSACTION,
				Status.STATUS_ACTIVE,
				Status.STATUS_ACTIVE);

		final IsolationLevelDataSourceAdapter dsToUse = new IsolationLevelDataSourceAdapter();
		dsToUse.setTargetDataSource(dataSource);
		dsToUse.afterPropertiesSet();

		JtaTransactionManager ptm = new JtaTransactionManager(userTransaction);
		ptm.setAllowCustomIsolationLevels(true);

		TransactionTemplate tt = new TransactionTemplate(ptm);
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
				Connection c = DataSourceUtils.getConnection(dsToUse);
				assertTrue("Has thread connection", TransactionSynchronizationManager.hasResource(dsToUse));
				assertSame(connection, c);
				DataSourceUtils.releaseConnection(c, dsToUse);
			}
		});

		tt.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		tt.setReadOnly(true);
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
				Connection c = DataSourceUtils.getConnection(dsToUse);
				assertTrue("Has thread connection", TransactionSynchronizationManager.hasResource(dsToUse));
				assertSame(connection, c);
				DataSourceUtils.releaseConnection(c, dsToUse);
			}
		});

		verify(userTransaction, times(2)).begin();
		verify(userTransaction, times(2)).commit();
		verify(connection).setReadOnly(true);
		verify(connection).setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		verify(connection, times(2)).close();
	}
