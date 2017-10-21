	@Test
	public void addScriptsWithCustomCommentPrefix() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = builder//
				.addScripts("db-schema-custom-comments.sql", "db-test-data.sql")//
				.setCommentPrefix("~")//
				.build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
