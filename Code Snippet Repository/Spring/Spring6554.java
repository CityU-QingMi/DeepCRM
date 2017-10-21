	@Test
	public void scriptWithSelectStatements() throws Exception {
		databasePopulator.addScript(defaultSchema());
		databasePopulator.addScript(resource("db-test-data-select.sql"));
		DatabasePopulatorUtils.execute(databasePopulator, db);
		assertThat(jdbcTemplate.queryForObject("select COUNT(NAME) from T_TEST where NAME='Keith'", Integer.class),
			equalTo(1));
		assertThat(jdbcTemplate.queryForObject("select COUNT(NAME) from T_TEST where NAME='Dave'", Integer.class),
			equalTo(1));
	}
