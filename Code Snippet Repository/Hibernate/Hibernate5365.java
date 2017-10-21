	@Test
	@TestForIssue(jiraKey = "")
	public void testMessageException() throws SQLException {
		PostgreSQL81Dialect dialect = new PostgreSQL81Dialect();
		try {
			dialect.getResultSet( Mockito.mock( CallableStatement.class), "abc" );
			fail( "Expected UnsupportedOperationException" );
		}
		catch (Exception e) {
			assertTrue( e instanceof UnsupportedOperationException );
			assertEquals( "PostgreSQL only supports accessing REF_CURSOR parameters by position", e.getMessage() );
		}
	}
