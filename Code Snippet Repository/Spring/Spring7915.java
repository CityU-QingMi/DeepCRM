	@Test
	public void testTransactionCommitWithDataSource() throws SQLException {
		DataSource ds = mock(DataSource.class);
		tm.setDataSource(ds);

		given(manager.getTransaction()).willReturn(tx);

		final List<String> l = new ArrayList<>();
		l.add("test");

		assertTrue(!TransactionSynchronizationManager.hasResource(factory));
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());

		Object result = tt.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				assertTrue(TransactionSynchronizationManager.hasResource(factory));
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				EntityManagerFactoryUtils.getTransactionalEntityManager(factory).flush();
				return l;
			}
		});

		assertTrue(result == l);

		assertTrue(!TransactionSynchronizationManager.hasResource(factory));
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());

		verify(tx).commit();
		verify(manager).flush();
		verify(manager).close();
	}
