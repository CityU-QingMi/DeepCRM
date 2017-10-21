	@Test
	@TestForIssue(jiraKey = "")
	public void testCurrentDateFunction() {
		Map<String, SQLFunction> functions = dialect.getFunctions();
		SQLFunction sqlFunction = functions.get( "current_date" );

		Type firstArgumentType = null;
		Mapping mapping = null;
		assertEquals( StandardBasicTypes.DATE, sqlFunction.getReturnType( firstArgumentType, mapping ) );

		firstArgumentType = null;
		List arguments = Collections.emptyList();
		SessionFactoryImplementor factory = null;
		assertEquals( "today", sqlFunction.render( firstArgumentType, arguments, factory ) );
	}
