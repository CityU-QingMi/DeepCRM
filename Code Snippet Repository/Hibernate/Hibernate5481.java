	@Test
	public void testDefaultedTableBackedConfiguration() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DIALECT, TableDialect.class.getName() )
				.build();

		try {
			Properties props = buildGeneratorPropertiesBase( serviceRegistry );
			SequenceStyleGenerator generator = new SequenceStyleGenerator();
			generator.configure( StandardBasicTypes.LONG, props, serviceRegistry );

			generator.registerExportables(
					new Database( new MetadataBuilderImpl.MetadataBuildingOptionsImpl( serviceRegistry ) )
			);

			assertClassAssignability( TableStructure.class, generator.getDatabaseStructure().getClass() );
			assertClassAssignability( NoopOptimizer.class, generator.getOptimizer().getClass() );
			assertEquals( SequenceStyleGenerator.DEF_SEQUENCE_NAME, generator.getDatabaseStructure().getName() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
