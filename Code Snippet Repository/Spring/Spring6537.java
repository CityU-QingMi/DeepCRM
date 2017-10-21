	@Test
	public void addDefaultScripts() throws Exception {
		doTwice(new Runnable() {

			@Override
			public void run() {
				EmbeddedDatabase db = new EmbeddedDatabaseBuilder()//
				.addDefaultScripts()//
				.build();
				assertDatabaseCreatedAndShutdown(db);
			}
		});
	}
