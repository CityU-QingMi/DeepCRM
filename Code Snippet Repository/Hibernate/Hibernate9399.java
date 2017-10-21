	@Test
	@TestForIssue(jiraKey = "")
	@RequiresDialect(SQLServerDialect.class)
	public void testGetTableDataForJoinTableWithDefaultSchema() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DEFAULT_CATALOG, "hibernate_orm_test" )
				.applySetting( AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "false" )
				.build();
		try {
			final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( Task.class )
					.addAnnotatedClass( Project.class )
					.buildMetadata();
			metadata.validate();

			// first create the schema...
			new SchemaExport().create( EnumSet.of( TargetType.DATABASE ), metadata );
			try {
				// validate the schema
				new SchemaValidator().validate( metadata );
			}
			finally {
				// cleanup
				new SchemaExport().drop( EnumSet.of( TargetType.DATABASE ), metadata );
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
