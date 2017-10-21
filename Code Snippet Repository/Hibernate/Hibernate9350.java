	@Test
	public void testSetFormat() throws Exception {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( Environment.HBM2DDL_AUTO, "none" )
				.build();
		try {
			File output = File.createTempFile( "update_script", ".sql" );
			output.deleteOnExit();

			final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( TestEntity.class )
					.buildMetadata();
			metadata.validate();

			new SchemaUpdate()
					.setHaltOnError( true )
					.setOutputFile( output.getAbsolutePath() )
					.setDelimiter( DELIMITER )
					.setFormat( true )
					.execute( EnumSet.of( TargetType.SCRIPT ), metadata );

			String outputContent = new String(Files.readAllBytes(output.toPath()));
			//Old Macs use \r as a new line delimiter
			outputContent = outputContent.replaceAll( "\r", "\n");
			//On Windows, \r\n would become \n\n, so we eliminate duplicates
			outputContent = outputContent.replaceAll( "\n\n", "\n");

			Assert.assertTrue( Pattern.compile( AFTER_FORMAT ).matcher( outputContent ).matches()  );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
