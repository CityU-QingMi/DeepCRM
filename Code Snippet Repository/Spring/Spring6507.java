	@Test
	public void testPropagationSupportsAndRequiresNewWithEarlyAccess() throws Exception {
		final Connection con1 = mock(Connection.class);
		final Connection con2 = mock(Connection.class);
		given(ds.getConnection()).willReturn(con1, con2);

		final
		TransactionTemplate tt = new TransactionTemplate(tm);
		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(ds));
		assertTrue("Synchronization not active", !TransactionSynchronizationManager.isSynchronizationActive());

		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
				assertTrue("Synchronization active", TransactionSynchronizationManager.isSynchronizationActive());
				assertSame(con1, DataSourceUtils.getConnection(ds));
				assertSame(con1, DataSourceUtils.getConnection(ds));
				TransactionTemplate tt2 = new TransactionTemplate(tm);
				tt2.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				tt2.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
						assertTrue("Has thread connection", TransactionSynchronizationManager.hasResource(ds));
						assertTrue("Synchronization active", TransactionSynchronizationManager.isSynchronizationActive());
						assertTrue("Is new transaction", status.isNewTransaction());
						assertSame(con2, DataSourceUtils.getConnection(ds));
						assertSame(con2, DataSourceUtils.getConnection(ds));
					}
				});
				assertSame(con1, DataSourceUtils.getConnection(ds));
			}
		});

		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(ds));
		verify(con1).close();
		verify(con2).commit();
		verify(con2).close();
	}
