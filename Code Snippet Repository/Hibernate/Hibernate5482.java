	@Test
	public void testDefaultOptimizerBasedOnIncrementBackedBySequence() {
		// for dialects which do not support pooled sequences, we default to pooled+table
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DIALECT, SequenceDialect.class.getName() )
				.build();

		try {
			Properties props = buildGeneratorPropertiesBase( serviceRegistry );
			props.setProperty( SequenceStyleGenerator.INCREMENT_PARAM, "10" );

			SequenceStyleGenerator generator = new SequenceStyleGenerator();
			generator.configure( StandardBasicTypes.LONG, props, serviceRegistry );

			generator.registerExportables(
					new Database( new MetadataBuilderImpl.MetadataBuildingOptionsImpl( serviceRegistry ) )
			);

			assertClassAssignability( TableStructure.class, generator.getDatabaseStructure().getClass() );
			assertClassAssignability( PooledOptimizer.class, generator.getOptimizer().getClass() );
			assertEquals( SequenceStyleGenerator.DEF_SEQUENCE_NAME, generator.getDatabaseStructure().getName() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}

		// for dialects which do support pooled sequences, we default to pooled+sequence
		serviceRegistry = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DIALECT, PooledSequenceDialect.class.getName() )
				.build();

		try {
			Properties props = buildGeneratorPropertiesBase( serviceRegistry );
			props.setProperty( SequenceStyleGenerator.INCREMENT_PARAM, "10" );

			SequenceStyleGenerator generator = new SequenceStyleGenerator();
			generator.configure( StandardBasicTypes.LONG, props, serviceRegistry );
			generator.registerExportables(
					new Database( new MetadataBuilderImpl.MetadataBuildingOptionsImpl( serviceRegistry ) )
			);

			assertClassAssignability( SequenceStructure.class, generator.getDatabaseStructure().getClass() );
			assertClassAssignability( PooledOptimizer.class, generator.getOptimizer().getClass() );
			assertEquals( SequenceStyleGenerator.DEF_SEQUENCE_NAME, generator.getDatabaseStructure().getName() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
