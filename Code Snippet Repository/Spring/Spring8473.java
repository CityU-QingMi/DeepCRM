	@AfterTransaction
	@Override
	public void afterTransaction() {
		assertEquals("Deleting yoda", 1, deletePerson(YODA));

		// NOTE: We would actually expect that there are now ZERO entries in the
		// person table, since the transaction is rolled back by the framework;
		// however, since our JdbcTemplate and the transaction manager used by
		// the Spring TestContext Framework use two different DataSource
		// instances, our insert statements were executed in transactions that
		// are not controlled by the test framework. Consequently, there was no
		// rollback for the two insert statements in
		// modifyTestDataWithinTransaction().
		//
		assertNumRowsInPersonTable(2, "after a transactional test method");
	}
