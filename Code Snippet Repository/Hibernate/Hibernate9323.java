	@Test
	public void testSchemaCreationSQLCommandIsGeneratedWithTheCorrectColumnSizeValues() throws Exception {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		try {
			final org.hibernate.boot.Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( Employee.class )
					.buildMetadata();

			boolean createTableEmployeeFound = false;

			final List<String> commands = new SchemaCreatorImpl( ssr ).generateCreationCommands(
					metadata,
					false
			);

			for ( String command : commands ) {
				LOGGER.info( command );
				if ( command.toLowerCase().matches( "^create( (column|row))? table employee.+" ) ) {
					final String[] columnsDefinition = getColumnsDefinition( command );

					for ( int i = 0; i < columnsDefinition.length; i++ ) {
						checkColumnSize( columnsDefinition[i] );
					}
					createTableEmployeeFound = true;
				}
			}
			assertTrue(
					"Expected create table command for Employee entity not found",
					createTableEmployeeFound
			);
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
