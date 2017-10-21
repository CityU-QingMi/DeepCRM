	@Test
	public void testDefaultTransactionDefinition() throws Exception {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);

		try {
			dialect.beginTransaction(null, definition);
			fail("expected exception");
		}
		catch (TransactionException e) {
			// ok
		}
	}
