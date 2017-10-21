	@Test
	public void addScriptsWithCustomBlockComments() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = builder//
				.addScripts("db-schema-block-comments.sql", "db-test-data.sql")//
				.setBlockCommentStartDelimiter("{*")//
				.setBlockCommentEndDelimiter("*}")//
				.build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
