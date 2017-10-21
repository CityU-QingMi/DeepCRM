	@Test
	public void addScript() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = builder//
				.addScript("db-schema.sql")//
				.addScript("db-test-data.sql")//
				.build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
