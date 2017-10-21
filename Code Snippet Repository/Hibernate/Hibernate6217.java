	@Test
	@TestForIssue( jiraKey = "" )
	public void testAnnotationsFieldAccess() {
		// this one should be ok because the AccessType is FIELD
		Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( AnotherEntity.class )
				.buildMetadata();
		assertNotNull( metadata.getEntityBinding( AnotherEntity.class.getName() ).getIdentifier() );
		assertNotNull( metadata.getEntityBinding( AnotherEntity.class.getName() ).getIdentifierProperty() );
	}
