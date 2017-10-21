	@Test
	public void addScripts() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = builder//
				.addScripts("db-schema.sql", "db-test-data.sql")//
				.build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
