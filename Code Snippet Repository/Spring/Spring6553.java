	@Test
	public void scriptWithoutStatementSeparator() throws Exception {
		databasePopulator.setSeparator(ScriptUtils.EOF_STATEMENT_SEPARATOR);
		databasePopulator.addScript(resource("drop-users-schema.sql"));
		databasePopulator.addScript(resource("users-schema-without-separator.sql"));
		databasePopulator.addScript(resource("users-data-without-separator.sql"));
		DatabasePopulatorUtils.execute(databasePopulator, db);

		assertUsersDatabaseCreated("Brannen");
	}
