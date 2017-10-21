	@Test
	public void createSameSchemaTwiceWithGeneratedUniqueDbNames() throws Exception {
		EmbeddedDatabase db1 = new EmbeddedDatabaseBuilder(new ClassRelativeResourceLoader(getClass()))//
		.addScripts("db-schema-without-dropping.sql", "db-test-data.sql")//
		.generateUniqueName(true)//
		.build();

		JdbcTemplate template1 = new JdbcTemplate(db1);
		assertNumRowsInTestTable(template1, 1);
		template1.update("insert into T_TEST (NAME) values ('Sam')");
		assertNumRowsInTestTable(template1, 2);

		EmbeddedDatabase db2 = new EmbeddedDatabaseBuilder(new ClassRelativeResourceLoader(getClass()))//
		.addScripts("db-schema-without-dropping.sql", "db-test-data.sql")//
		.generateUniqueName(true)//
		.build();
		assertDatabaseCreated(db2);

		db1.shutdown();
		db2.shutdown();
	}
