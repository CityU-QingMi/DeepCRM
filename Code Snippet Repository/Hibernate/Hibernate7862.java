	@Test
	@TestForIssue( jiraKey = "" )
	public void expandedTests() {
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry standardRegistry = registryBuilder.build();
		try {
			final MetadataSources sources = new MetadataSources( standardRegistry );

			sources.addAnnotatedClass( ManyToManyOwner.class );
			sources.addAnnotatedClass( ManyToManyTarget.class );

			final MetadataImplementor metadata = (MetadataImplementor) sources.buildMetadata();
			metadata.validate();

			// export the schema
			new SchemaExport().execute(
					EnumSet.of( TargetType.DATABASE ),
					SchemaExport.Action.BOTH,
					metadata
			);

			try {
				// update the schema
				new SchemaUpdate().execute( EnumSet.of( TargetType.DATABASE ), metadata );
			}
			finally {
				// drop the schema
				new SchemaExport().execute(
						EnumSet.of( TargetType.DATABASE ),
						SchemaExport.Action.DROP,
						metadata
				);
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( standardRegistry );
		}
	}
