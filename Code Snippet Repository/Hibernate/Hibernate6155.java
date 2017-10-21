	@Test
	@TestForIssue(jiraKey = "")
	public void testEncoding() throws Exception {

		entityManagerFactoryBuilder.generateSchema();

		final String fileContent = new String( Files.readAllBytes( createSchema.toPath() ) )
				.toLowerCase();
		assertTrue( fileContent.contains( expectedTableName() ) );
		assertTrue( fileContent.contains( expectedFieldName() ) );

		final String dropFileContent = new String( Files.readAllBytes(
				dropSchema.toPath() ) ).toLowerCase();
		assertTrue( dropFileContent.contains( expectedTableName() ) );
	}
