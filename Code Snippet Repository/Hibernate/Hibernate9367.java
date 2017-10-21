	@Test
	@TestForIssue(jiraKey = "")
	public void testCommentOnTableStatementIsGenerated() throws IOException {
		createSchema( new Class[] {TableWithComment.class} );

		final List<String> sqlLines = Files.readAllLines( output.toPath(), Charset.defaultCharset() );
		boolean found = false;
		final String tableName = getTableName();
		for ( String sqlStatement : sqlLines ) {
			if ( sqlStatement.toLowerCase()
					.equals( "comment on table " + tableName.toLowerCase() + " is 'comment snippet'" ) ) {
				if ( getDialect().supportsCommentOn() ) {
					found = true;
				}
				else {
					fail( "Generated " + sqlStatement + "  statement, but Dialect does not support it" );
				}
			}
			if ( containsCommentInCreateTableStatement( sqlStatement ) ) {
				if ( getDialect().supportsCommentOn() && !getDialect().getTableComment( "comment snippet" ).equals( "" ) ) {
					fail( "Added comment on create table statement when Dialect support create comment on table statement" );
				}
				else {
					found = true;
				}
			}
		}

		assertThat( "Table Comment Statement not correctly generated", found, is( true ) );
	}
