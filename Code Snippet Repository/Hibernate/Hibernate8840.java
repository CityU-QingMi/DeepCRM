	@Test
	@TestForIssue( jiraKey = "")
	public void testPreparedStatementNClobBinding() throws SQLException {
		final WrapperOptions wrapperOptions = new MockWrapperOptions( false );
		binder.bind(
				createPreparedStatementProxy( wrapperOptions ),
				"aString",
				1,
				wrapperOptions
		);
	}
