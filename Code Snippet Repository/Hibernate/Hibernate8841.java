	@Test
	@TestForIssue( jiraKey = "")
	public void testCallableStatementNClobBinding() throws SQLException {
		final WrapperOptions wrapperOptions = new MockWrapperOptions( false );
		binder.bind(
				createCallableStatementProxy( wrapperOptions ),
				"aString",
				"aColumn",
				wrapperOptions
		);
	}
