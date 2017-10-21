	@Test
	public void setTypeToDerbyAndIgnoreFailedDrops() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = builder//
				.setType(DERBY)//
				.ignoreFailedDrops(true)//
				.addScripts("db-schema-derby-with-drop.sql", "db-test-data.sql").build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
