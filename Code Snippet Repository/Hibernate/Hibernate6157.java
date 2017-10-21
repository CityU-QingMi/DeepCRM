	@Test
	@TestForIssue(jiraKey = "")
	public void testGenerateSchemaDoesNotProduceTheSameStatementTwice() throws Exception {

		entityManagerFactoryBuilder.generateSchema();

		final String fileContent = new String( Files.readAllBytes( createSchema.toPath() ) ).toLowerCase();

		Pattern createStatementPattern = Pattern.compile( "create( (column|row))? table test_entity" );
		Matcher createStatementMatcher = createStatementPattern.matcher( fileContent );
		assertThat( createStatementMatcher.find(), is( true ) );
		assertThat(
				"The statement 'create table test_entity' is generated twice",
				createStatementMatcher.find(),
				is( false )
		);

		final String dropFileContent = new String( Files.readAllBytes( dropSchema.toPath() ) ).toLowerCase();
		assertThat( dropFileContent.contains( "drop table " ), is( true ) );
		assertThat(
				"The statement 'drop table ' is generated twice",
				dropFileContent.replaceFirst( "drop table ", "" ).contains( "drop table " ),
				is( false )
		);
	}
