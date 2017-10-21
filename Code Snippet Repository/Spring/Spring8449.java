	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void modifyTestDataWithoutTransaction() {
		assertInTransaction(false);
		assertEquals("Adding luke", 1, addPerson(jdbcTemplate, LUKE));
		assertEquals("Adding leia", 1, addPerson(jdbcTemplate, LEIA));
		assertEquals("Adding yoda", 1, addPerson(jdbcTemplate, YODA));
		assertEquals("Verifying the number of rows in the person table without a transaction.", 4,
			countRowsInPersonTable(jdbcTemplate));
	}
