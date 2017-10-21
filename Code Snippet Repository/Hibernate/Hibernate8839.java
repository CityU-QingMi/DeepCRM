	@Test
	@TestForIssue( jiraKey = "" )
	public void testCallableStatementStreamBinding() throws SQLException {
		final WrapperOptions wrapperOptions = new MockWrapperOptions( true );
		binder.bind(
				createCallableStatementProxy( wrapperOptions ),
				"aString",
				"aColumn",
				wrapperOptions
		);
	}
