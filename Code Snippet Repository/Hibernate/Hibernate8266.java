	@Test
	@TestForIssue( jiraKey = "" )
	public void testLegacyGeneratorTableCreationOnDb2() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DIALECT, DB2Dialect.class.getName() )
				.build();

		try {
			Metadata metadata = new MetadataSources( ssr )
					.buildMetadata();

			assertEquals( 0, metadata.getDatabase().getDefaultNamespace().getTables().size() );

			MultipleHiLoPerTableGenerator generator = new MultipleHiLoPerTableGenerator();

			Properties properties = new Properties();
			generator.configure( IntegerType.INSTANCE, properties, ssr );

			generator.registerExportables( metadata.getDatabase() );

			assertEquals( 1, metadata.getDatabase().getDefaultNamespace().getTables().size() );

			final Table table = metadata.getDatabase().getDefaultNamespace().getTables().iterator().next();
			final String[] createCommands = new DB2Dialect().getTableExporter().getSqlCreateStrings( table, metadata );
			assertContains( "sequence_name varchar(255) not null", createCommands[0] );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
