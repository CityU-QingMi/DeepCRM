	@Test
	@TestForIssue(jiraKey = "")
	public void testBogusQuery() {
		try {
			QueryTranslatorImpl translator = createNewQueryTranslator( "bogus" );
			fail( "This should have failed with a QueryException" );
		}
		catch ( Throwable t ) {
			assertTyping( QueryException.class, t );
		}
	}
