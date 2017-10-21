	@Test
	@TestForIssue(jiraKey = "")
	public void testCurrentTimestampFunction() {
		Map<String, SQLFunction> functions = dialect.getFunctions();
		SQLFunction sqlFunction = functions.get( "current_timestamp" );

		Type firstArgumentType = null;
		Mapping mapping = null;
		assertEquals( StandardBasicTypes.TIMESTAMP, sqlFunction.getReturnType( firstArgumentType, mapping ) );

		firstArgumentType = null;
		List arguments = Collections.emptyList();
		SessionFactoryImplementor factory = null;
		assertEquals( "current", sqlFunction.render( firstArgumentType, arguments, factory ) );
	}
