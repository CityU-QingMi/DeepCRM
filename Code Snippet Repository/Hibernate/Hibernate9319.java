	@Test
	public void testSchemaUpdateWithQuotedColumnNames() throws Exception {
		new SchemaUpdate()
				.setOutputFile( output.getAbsolutePath() )
				.execute(
						EnumSet.of( TargetType.SCRIPT ),
						metadata
				);

		final String fileContent = new String( Files.readAllBytes( output.toPath() ) );
		assertThat( "The update output file should be empty", fileContent, is( "" ) );
	}
