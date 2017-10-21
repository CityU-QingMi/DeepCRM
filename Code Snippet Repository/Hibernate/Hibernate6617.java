	@Test
	@TestForIssue( jiraKey = "" )
	public void testBlownPrecision() throws Exception {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DIALECT, "SQLServer" )
				.build();

		try {
			Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( Bunny.class )
					.addAnnotatedClass( PointyTooth.class )
					.addAnnotatedClass( TwinkleToes.class )
					.buildMetadata();

			boolean foundPointyToothCreate = false;
			boolean foundTwinkleToesCreate = false;

			List<String> commands = new SchemaCreatorImpl( ssr ).generateCreationCommands( metadata, false );
			for ( String command : commands ) {
				log.debug( command );

				if ( expectedSqlPointyTooth.equals( command ) ) {
					foundPointyToothCreate = true;
				}
				else if ( expectedSqlTwinkleToes.equals( command ) ) {
					foundTwinkleToesCreate = true;
				}
			}

			assertTrue( "Expected create table command for PointyTooth entity not found", foundPointyToothCreate );
			assertTrue( "Expected create table command for TwinkleToes entity not found", foundTwinkleToesCreate );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
