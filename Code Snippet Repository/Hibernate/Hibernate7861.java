	@Test
	@TestForIssue( jiraKey = "" )
	public void basicTests() {
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry standardRegistry = registryBuilder.build();
		try {
			final MetadataSources sources = new MetadataSources( standardRegistry );

			sources.addAnnotatedClass( ManyToManyOwner.class );
			sources.addAnnotatedClass( ManyToManyTarget.class );

			final MetadataImplementor metadata = (MetadataImplementor) sources.buildMetadata();
			metadata.validate();

			new SchemaExport().execute(
					EnumSet.of( TargetType.STDOUT ),
					SchemaExport.Action.CREATE,
					metadata
			);

			int fkCount = 0;
			for ( Table table : metadata.collectTableMappings() ) {
				for ( Map.Entry<ForeignKeyKey, ForeignKey> entry : table.getForeignKeys().entrySet() ) {
					assertFalse(
							"Creation for ForeignKey [" + entry.getKey() + "] was not disabled",
							entry.getValue().isCreationEnabled()
					);
					fkCount++;
				}
			}

			// ultimately I want to actually create the ForeignKet reference, but simply disable its creation
			// via ForeignKet#disableCreation()
			assertEquals( "Was expecting 4 FKs", 0, fkCount );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( standardRegistry );
		}
	}
