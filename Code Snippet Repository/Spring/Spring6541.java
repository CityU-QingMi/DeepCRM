	@Test
	public void addScriptsWithDefaultCommentPrefix() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = builder//
				.addScripts("db-schema-comments.sql", "db-test-data.sql")//
				.build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
