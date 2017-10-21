	@Test
	public void testContainerEntityManagerProxyRejectsJoinTransactionWithoutTransaction() {
		endTransaction();

		try {
			createContainerManagedEntityManager().joinTransaction();
			fail("Should have thrown a TransactionRequiredException");
		}
		catch (TransactionRequiredException ex) {
			// expected
		}
	}
