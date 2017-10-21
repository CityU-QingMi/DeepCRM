	@Test
	public void newTransactionWithCommitException() {
		final RollbackException rex = new RollbackException();
		MockUOWManager manager = new MockUOWManager() {
			@Override
			public void runUnderUOW(int type, boolean join, UOWAction action) throws UOWException {
				throw new UOWException(rex);
			}
		};
		WebSphereUowTransactionManager ptm = new WebSphereUowTransactionManager(manager);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		try {
			ptm.execute(definition, new TransactionCallback<String>() {
				@Override
				public String doInTransaction(TransactionStatus status) {
					assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
					assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
					assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
					return "result";
				}
			});
			fail("Should have thrown TransactionSystemException");
		}
		catch (TransactionSystemException ex) {
			// expected
			assertTrue(ex.getCause() instanceof UOWException);
			assertSame(rex, ex.getRootCause());
			assertSame(rex, ex.getMostSpecificCause());
		}

		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		assertEquals(0, manager.getUOWTimeout());
	}
