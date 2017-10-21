	@Test
	@TestForIssue( jiraKey = "" )
	public void testAnsiSqlKeyword() {
		// END is ANSI SQL keyword

		JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		assertTrue( jdbcEnvironment.getIdentifierHelper().isReservedWord( "end" ) );
		assertTrue( jdbcEnvironment.getIdentifierHelper().isReservedWord( "END" ) );

		Identifier identifier = jdbcEnvironment.getIdentifierHelper().toIdentifier( "end" );
		assertTrue( identifier.isQuoted() );
	}
