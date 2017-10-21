	@Test
	@TestForIssue( jiraKey = "" )
	public void testIndexCreationViaSchemaUpdate() {
		MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
				.addAnnotatedClass( EntityWithIndex.class )
				.buildMetadata();

		// drop and then create the schema
		new SchemaExport().execute( EnumSet.of( TargetType.DATABASE ), SchemaExport.Action.BOTH, metadata );

		try {
			// update the schema
			new SchemaUpdate().execute( EnumSet.of( TargetType.DATABASE ), metadata );
		}
		finally {
			// drop the schema
			new SchemaExport().drop( EnumSet.of( TargetType.DATABASE ), metadata );
		}
	}
