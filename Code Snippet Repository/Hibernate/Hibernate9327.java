	@Test
	@TestForIssue( jiraKey = "" )
	public void testSameTableNameDifferentExplicitSchemas() {
		MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
				.addAnnotatedClass( CustomerInfo.class )
				.addAnnotatedClass( PersonInfo.class )
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
