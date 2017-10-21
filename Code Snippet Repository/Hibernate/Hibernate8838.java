	@Test
	@TestForIssue( jiraKey = "" )
	public void testPreparedStatementStreamBinding() throws SQLException {
		final WrapperOptions wrapperOptions = new MockWrapperOptions( true );
		binder.bind(
				createPreparedStatementProxy( wrapperOptions ),
				"aString",
				1,
				wrapperOptions
		);
	}
