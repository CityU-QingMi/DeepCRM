	private void assertThatCreateIndexCommandIsGenerated(List<String> commands) {
		boolean createIndexCommandIsGenerated = false;
		for ( String command : commands ) {
			if ( command.toLowerCase().contains( "create index city_index" ) ) {
				createIndexCommandIsGenerated = true;
			}
		}
		assertTrue(
				"Expected create index command not found",
				createIndexCommandIsGenerated
		);
	}
