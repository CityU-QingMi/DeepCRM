	@Test
	@TestForIssue( jiraKey = "" )
	public void testMultipleUsesOfExplicitSequenceName() {
		final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( Entity3.class )
				.addAnnotatedClass( Entity4.class )
				.buildMetadata();
		metadata.validate();

		assertEquals( 0, metadata.getDatabase().getAuxiliaryDatabaseObjects().size() );

		int count = 0;
		for ( Namespace namespace : metadata.getDatabase().getNamespaces() ) {
			for ( Sequence sequence : namespace.getSequences() ) {
				count++;
			}
		}

		assertEquals( 1, count );
	}
