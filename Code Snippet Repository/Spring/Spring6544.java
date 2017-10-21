	@Test
	public void setTypeToH2() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = builder//
				.setType(H2)//
				.addScripts("db-schema.sql", "db-test-data.sql")//
				.build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
