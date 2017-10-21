	@Test
	public void testSchemaUpdateAndValidation() throws Exception {
		if(!SQLServerDialect.class.isAssignableFrom( Dialect.getDialect().getClass() )) {
			return;
		}

		new SchemaUpdate().setHaltOnError( true )
				.execute( EnumSet.of( TargetType.DATABASE ), metadata );

		new SchemaValidator().validate( metadata );

		new SchemaUpdate().setHaltOnError( true )
				.setOutputFile( output.getAbsolutePath() )
				.setFormat( false )
				.execute( EnumSet.of( TargetType.DATABASE, TargetType.SCRIPT ), metadata );

		final String fileContent = new String( Files.readAllBytes( output.toPath() ) );
		assertThat( "The update output file should be empty", fileContent, is( "" ) );
	}
