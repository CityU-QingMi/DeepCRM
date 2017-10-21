	@Test
	public void testSchemaUpdateApplyDelimiterToGeneratedSQL() throws Exception {
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
					.setDelimiter( EXPECTED_DELIMITER )
					.setFormat( false )
					.execute( EnumSet.of( TargetType.SCRIPT ), metadata );

			List<String> sqlLines = Files.readAllLines( output.toPath(), Charset.defaultCharset() );
			for ( String line : sqlLines ) {
				assertThat(
						"The expected delimiter is not applied " + line,
						line.endsWith( EXPECTED_DELIMITER ),
						is( true )
				);
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
