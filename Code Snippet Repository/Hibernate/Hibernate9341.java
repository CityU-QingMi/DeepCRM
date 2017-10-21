	@Test
	@TestForIssue(jiraKey = "")
	@RequiresDialectFeature( value = DialectChecks.SupportSchemaCreation.class)
	public void testHibernateMappingSchemaPropertyIsNotIgnored() throws Exception {
		File output = File.createTempFile( "update_script", ".sql" );
		output.deleteOnExit();

		final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
				.addResource( "org/hibernate/test/schemaupdate/mapping2.hbm.xml" )
				.buildMetadata();
		metadata.validate();

		final SchemaExport schemaExport = new SchemaExport();
		schemaExport.setOutputFile( output.getAbsolutePath() );
		schemaExport.execute( EnumSet.of( TargetType.SCRIPT ), SchemaExport.Action.CREATE, metadata );

		String fileContent = new String( Files.readAllBytes( output.toPath() ) );
		Pattern fileContentPattern = Pattern.compile( "create( (column|row))? table schema1.version" );
		Matcher fileContentMatcher = fileContentPattern.matcher( fileContent.toLowerCase() );
		assertThat( fileContent, fileContentMatcher.find(), is( true ) );
	}
