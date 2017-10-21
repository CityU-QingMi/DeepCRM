	@Test
	public void createSameSchemaTwiceWithoutUniqueDbNames() throws Exception {
		EmbeddedDatabase db1 = new EmbeddedDatabaseBuilder(new ClassRelativeResourceLoader(getClass()))//
		.addScripts("db-schema-without-dropping.sql").build();

		try {
			new EmbeddedDatabaseBuilder(new ClassRelativeResourceLoader(getClass()))//
			.addScripts("db-schema-without-dropping.sql").build();

			fail("Should have thrown a ScriptStatementFailedException");
		}
		catch (ScriptStatementFailedException e) {
			// expected
		}
		finally {
			db1.shutdown();
		}
	}
